package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements ActionCommand {
    private static final String FORWARD_PAGE = "/index.jsp";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        if (login != null && password != null) {
            User user = null;
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return FORWARD_PAGE;
            }
        }
        return null;
    }
}
