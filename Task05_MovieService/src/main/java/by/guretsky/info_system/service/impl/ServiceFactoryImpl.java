package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.DaoManager;
import by.guretsky.info_system.dao.DaoManagerFactory;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.FilmService;
import by.guretsky.info_system.service.SeeLaterService;
import by.guretsky.info_system.service.Service;
import by.guretsky.info_system.service.ServiceFactory;
import by.guretsky.info_system.service.UserService;
import by.guretsky.info_system.service.WatchedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryImpl implements ServiceFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(ServiceFactoryImpl.class);
    private static final Map<Class<? extends Service>,
            Class<? extends ServiceImpl>> SERVICE_MAP =
            new ConcurrentHashMap<>();

    static {
        SERVICE_MAP.put(FilmService.class, FilmServiceImpl.class);
        SERVICE_MAP.put(SeeLaterService.class, SeeLaterServiceImpl.class);
        SERVICE_MAP.put(WatchedService.class, WatchedServiceImpl.class);
        SERVICE_MAP.put(UserService.class, UserServiceImpl.class);
    }

    private DaoManagerFactory daoManagerFactory;

    public ServiceFactoryImpl(final DaoManagerFactory managerFactory) {
        daoManagerFactory = managerFactory;
    }

    @Override
    public <T extends Service> T createService(final Class<T> key) throws
            CustomException {
        Class<? extends ServiceImpl> serviceClass = SERVICE_MAP.get(key);
        if (serviceClass != null) {
            try {
                DaoManager daoManager = daoManagerFactory.createAndGetManager();
                ServiceImpl service =
                        serviceClass.getDeclaredConstructor().newInstance();
                service.setDaoManager(daoManager);
                return (T) service;
            } catch (IllegalAccessException | InstantiationException
                    | NoSuchMethodException | InvocationTargetException e) {
                LOGGER.error("Class initialize error", e);
                throw new CustomException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        daoManagerFactory.close();
    }
}
