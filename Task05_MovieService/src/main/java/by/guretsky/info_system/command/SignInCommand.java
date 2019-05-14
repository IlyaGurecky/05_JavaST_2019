package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand extends Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        String login = request.getParameter(LOGIN_PARAM).trim();
        String password = request.getParameter(PASSWORD_PARAM).trim();
        UserService service = factory.createService(UserService.class);
        User user = service.findByLoginAndPassword(login, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return PageManager.createPage(PageEnum.HOME);
        } else {
            request.setAttribute("signinError",
                    "Login or Password is incorrect");
        }
        return PageManager.createPage(PageEnum.SIGN_IN);
    }
}
