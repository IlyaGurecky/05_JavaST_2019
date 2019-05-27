package by.guretsky.info_system.command.factory;

import by.guretsky.info_system.command.CommandManager;
import by.guretsky.info_system.command.CommandManagerImpl;
import by.guretsky.info_system.dao.impl.DaoManagerFactoryImpl;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.impl.ServiceFactoryImpl;

public final class CommandManagerFactory {
    private CommandManagerFactory() {
    }

    public static CommandManager getManager() throws CustomException {
        return new CommandManagerImpl(new ServiceFactoryImpl(
                new DaoManagerFactoryImpl()));
    }
}
