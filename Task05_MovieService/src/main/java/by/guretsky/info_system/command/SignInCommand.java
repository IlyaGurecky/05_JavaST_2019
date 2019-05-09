package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand extends ActionCommand {
    private static final String FORWARD_PAGE = "/home";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public String execute(HttpServletRequest request) throws CustomException {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        if (login != null && password != null) {
            UserService service = factory.createService(UserService.class);
            User user = service.findByLoginAndPassword(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return FORWARD_PAGE;
            } else {
                request.setAttribute("error",
                        "Login or Password is incorrect");
            }
        }
        return null;
    }
}
