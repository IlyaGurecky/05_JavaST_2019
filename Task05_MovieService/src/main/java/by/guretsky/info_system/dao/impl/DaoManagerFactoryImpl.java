package by.guretsky.info_system.dao.impl;

import by.guretsky.info_system.dao.DaoManager;
import by.guretsky.info_system.dao.DaoManagerFactory;
import by.guretsky.info_system.dao.connection.ConnectionPool;
import by.guretsky.info_system.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManagerFactoryImpl implements DaoManagerFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(DaoManagerFactoryImpl.class);
    private Connection connection;

    public DaoManagerFactoryImpl() throws CustomException {
        connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public DaoManager createAndGetManager() {
        return new DaoManagerImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Connection close error", e);
        }
    }
}
