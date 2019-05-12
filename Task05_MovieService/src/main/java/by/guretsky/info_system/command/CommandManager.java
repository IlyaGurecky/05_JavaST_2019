package by.guretsky.info_system.command;

import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;

import javax.servlet.http.HttpServletRequest;

public interface CommandManager {
    JspPage execute(Command action, HttpServletRequest request) throws
            CustomException;

    void close();
}
