package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.DaoManager;

public abstract class ServiceImpl {
    protected DaoManager daoManager;

    public void setDaoManager(final DaoManager manager) {
        this.daoManager = manager;
    }
}
