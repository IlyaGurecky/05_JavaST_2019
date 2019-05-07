package by.guretsky.info_system.command;

import by.guretsky.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class CommandManagerImpl implements CommandManager {
    private ServiceFactory factory;

    public CommandManagerImpl(ServiceFactory serviceFactory) {
        factory = serviceFactory;
    }

    @Override
    public String execute(ActionCommand action, HttpServletRequest request) {
        return null;
    }

    @Override
    public void close() {
        factory.close();
    }
}
