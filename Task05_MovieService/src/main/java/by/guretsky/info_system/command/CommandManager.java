package by.guretsky.info_system.command;

import by.guretsky.info_system.exception.CustomException;

import javax.servlet.http.HttpServletRequest;

public interface CommandManager {
    String execute(ActionCommand action, HttpServletRequest request) throws
            CustomException;

    void close();
}
