package by.guretsky.info_system.dao;

public interface DaoManagerFactory {
    DaoManager createAndGetManager();

    void close();
}
