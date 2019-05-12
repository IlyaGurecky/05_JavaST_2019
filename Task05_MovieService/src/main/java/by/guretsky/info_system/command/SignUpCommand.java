package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.role.Role;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand extends Command {
    private static final String ERROR_ATTRIBUTE = "signUpError";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String EMAIL = "email";

    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        String pass = request.getParameter(PASSWORD_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String email = request.getParameter(EMAIL);
        UserService service = factory.createService(UserService.class);
        if (service.findByLogin(login) == null) {
            if (service.findByEmail(email) == null) {
                User user = new User();
                user.setLogin(login);
                user.setPassword(pass);
                user.setEmail(email);
                user.setRole(Role.USER);
                if (service.create(user) != 0) {
                    request.setAttribute("completeMessage",
                            "User created successfully!");
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    return PageManager.createPage(PageEnum.HOME);
                } else {
                    request.setAttribute(ERROR_ATTRIBUTE,
                            "User create error");
                }
            } else {
                request.setAttribute(ERROR_ATTRIBUTE,
                        "User with this email already exists");
            }
        } else {
            request.setAttribute(ERROR_ATTRIBUTE,
                    "User with this login already exists");
        }
        return PageManager.createPage(PageEnum.SIGN_UP);
    }
}
