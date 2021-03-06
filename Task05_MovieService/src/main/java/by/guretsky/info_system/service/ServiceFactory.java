package by.guretsky.info_system.service;

import by.guretsky.info_system.exception.CustomException;

public interface ServiceFactory {
    <T extends Service> T createService(Class<T> key) throws
            CustomException;

    void close();
}
