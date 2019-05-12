package by.guretsky.info_system.command;

import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public abstract class Command {
    protected ServiceFactory factory;

    public abstract JspPage execute(final HttpServletRequest request) throws
            CustomException;

    public void setFactory(final ServiceFactory serviceFactory) {
        this.factory = serviceFactory;
    }
}
