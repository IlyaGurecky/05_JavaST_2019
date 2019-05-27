package by.guretsky.info_system.dao.impl;

import by.guretsky.info_system.dao.BaseDao;
import by.guretsky.info_system.dao.Dao;
import by.guretsky.info_system.dao.DaoManager;
import by.guretsky.info_system.dao.FilmDao;
import by.guretsky.info_system.dao.SeeLaterDao;
import by.guretsky.info_system.dao.UserDao;
import by.guretsky.info_system.dao.WatchedDao;
import by.guretsky.info_system.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DaoManagerImpl implements DaoManager {
    private static final Logger LOGGER =
            LogManager.getLogger(DaoManagerImpl.class);
    private Connection connection;

    private static final Map<Class<? extends Dao<?>>,
            Class<? extends BaseDao>> DAO_MAP = new ConcurrentHashMap<>();

    static {
        DAO_MAP.put(FilmDao.class, FilmDaoImpl.class);
        DAO_MAP.put(UserDao.class, UserDaoImpl.class);
        DAO_MAP.put(SeeLaterDao.class, SeeLaterDaoImpl.class);
        DAO_MAP.put(WatchedDao.class, WatchedDaoImpl.class);
    }

    DaoManagerImpl(final Connection dbConnection) {
        connection = dbConnection;
    }

    @Override
    public <T extends Dao<?>> T createAndGetDao(final Class<T> key)
            throws CustomException {
        Class<? extends BaseDao> daoClass = DAO_MAP.get(key);
        if (daoClass != null) {
            try {
                BaseDao dao = daoClass.getDeclaredConstructor().newInstance();
                dao.setConnection(connection);
                return (T) dao;
            } catch (InstantiationException | IllegalAccessException
                    | InvocationTargetException | NoSuchMethodException e) {
                LOGGER.error("Class initialize error", e);
                throw new CustomException(e);
            }
        }
        return null;
    }

    @Override
    public void commit() throws CustomException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Connection commit error", e);
            throw new CustomException(e);
        }
    }

    @Override
    public void rollback() throws CustomException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("Connection rollback error", e);
            throw new CustomException(e);
        }
    }

    @Override
    public void setAutoCommit(final boolean isAutoCommit)
            throws CustomException {
        try {
            connection.setAutoCommit(isAutoCommit);
        } catch (SQLException e) {
            LOGGER.error("Set auto commit error", e);
            throw new CustomException(e);
        }
    }
}
