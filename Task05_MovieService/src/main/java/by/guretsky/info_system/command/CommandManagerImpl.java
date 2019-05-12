package by.guretsky.info_system.command;

import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class CommandManagerImpl implements CommandManager {
    private ServiceFactory factory;

    public CommandManagerImpl(ServiceFactory serviceFactory) {
        factory = serviceFactory;
    }

    @Override
    public JspPage execute(Command action, HttpServletRequest request)
            throws CustomException {
        action.setFactory(factory);
        return action.execute(request);
    }

    @Override
    public void close() {
        factory.close();
    }
}
