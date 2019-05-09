package by.guretsky.info_system.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand extends ActionCommand {
    private static final String FORWARD = "/home";

    @Override
    String execute(HttpServletRequest request) {
        request.getSession(false).invalidate();
        return FORWARD;
    }
}
