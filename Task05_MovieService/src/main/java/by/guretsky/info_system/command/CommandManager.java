package by.guretsky.info_system.command;

import javax.servlet.http.HttpServletRequest;

public interface CommandManager {
    String execute(ActionCommand action, HttpServletRequest request);

    void close();
}
