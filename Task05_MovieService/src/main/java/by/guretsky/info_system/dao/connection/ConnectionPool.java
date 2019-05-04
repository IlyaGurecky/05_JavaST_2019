package by.guretsky.info_system.dao.connection;

import by.guretsky.info_system.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER =
            LogManager.getLogger(ConnectionPool.class);
    private BlockingQueue<PooledConnection> freeConnections =
            new LinkedBlockingQueue<>();
    private BlockingQueue<PooledConnection> usedConnections =
            new LinkedBlockingQueue<>();
    private String url;
    private String user;
    private String password;
    private int poolSize;
    private int timeout;
    private final ReentrantLock locker1 = new ReentrantLock();
    private final ReentrantLock locker2 = new ReentrantLock();
    private final ReentrantLock locker3 = new ReentrantLock();
    private final ReentrantLock locker4 = new ReentrantLock();

    private static class SingletonHolder {
        private static final ConnectionPool POOL = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return SingletonHolder.POOL;
    }

    public void initialize(String dbUser, String dbPassword, String dbUrl,
                           int startPoolSize, int maxPoolSize,
                           int connectionTimeout, String driver) throws
            CustomException {
        locker1.lock();
        invalidate();
        try {
            Class.forName(driver);
            url = dbUrl;
            user = dbUser;
            password = dbPassword;
            poolSize = maxPoolSize;
            timeout = connectionTimeout;
            for (int counter = 0; counter < startPoolSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("Driver name is incorrect", e);
            throw new CustomException(e);
        } catch (SQLException e) {
            LOGGER.fatal("Create connection error", e);
            throw new CustomException(e);
        } catch (InterruptedException e) {
            LOGGER.fatal("Interrupted while waiting", e);
            throw new CustomException(e);
        } finally {
            locker1.unlock();
        }
    }

    private PooledConnection createConnection() throws SQLException {
        Connection connection =
                DriverManager.getConnection(url, user, password);
        return new PooledConnection(connection);
    }

    public Connection getConnection() throws CustomException {
        locker3.lock();
        PooledConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(timeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                            LOGGER.error("Connection close err", e);
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < poolSize) {
                    connection = createConnection();
                } else {
                    LOGGER.error("Max number of connections");
                    throw new CustomException();
                }
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted while waiting", e);
                throw new CustomException(e);

            } catch (SQLException e) {
                LOGGER.error("TimeOut value is less than 0", e);
                throw new CustomException(e);
            }
        }
        usedConnections.add(connection);
        locker3.unlock();
        return connection;
    }

    public void releaseConnection(PooledConnection connection) {
        locker2.lock();
        try {
            if (connection.isValid(timeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("It is impossible to return" +
                    " database connection into pool", e);
            try {
                connection.getConnection().close();
            } catch (SQLException e1) {
                LOGGER.error("Connection close error", e1);
            }
        } finally {
            locker2.unlock();
        }
    }

    public void invalidate() {
        locker4.lock();
        try {
            usedConnections.addAll(freeConnections);
            usedConnections.stream()
                    .map(PooledConnection::getConnection)
                    .forEach(connection -> {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            LOGGER.error("Connection close error", e);
                        }
                    });
            usedConnections.clear();
            freeConnections.clear();
        } finally {
            locker4.unlock();
        }
    }
}
