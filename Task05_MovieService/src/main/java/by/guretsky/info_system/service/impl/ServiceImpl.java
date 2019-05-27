package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.DaoManager;

abstract class ServiceImpl {
    DaoManager daoManager;

    void setDaoManager(final DaoManager manager) {
        this.daoManager = manager;
    }
}
