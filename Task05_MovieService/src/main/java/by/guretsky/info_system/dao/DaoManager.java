package by.guretsky.info_system.dao;

import by.guretsky.info_system.exception.CustomException;

public interface DaoManager {
    <T extends Dao<?>> T createAndGetDao(final Class<T> key) throws CustomException;

    void commit() throws CustomException;

    void rollback() throws CustomException;

    void setAutoCommit(boolean isAutoCommit) throws CustomException;
}
