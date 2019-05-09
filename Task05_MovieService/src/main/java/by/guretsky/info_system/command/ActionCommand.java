package by.guretsky.info_system.command;

import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public abstract class ActionCommand {
    protected ServiceFactory factory;
    private String name;

    abstract String execute(final HttpServletRequest request) throws
            CustomException;

    public String getName() {
        return name;
    }

    public void setName(final String actionName) {
        this.name = actionName;
    }

    public void setFactory(final ServiceFactory serviceFactory) {
        this.factory = serviceFactory;
    }
}
