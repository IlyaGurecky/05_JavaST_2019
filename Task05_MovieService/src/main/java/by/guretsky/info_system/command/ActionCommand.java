package by.guretsky.info_system.command;

import by.guretsky.info_system.exception.CustomException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    String execute(HttpServletRequest request) throws CustomException;
}
