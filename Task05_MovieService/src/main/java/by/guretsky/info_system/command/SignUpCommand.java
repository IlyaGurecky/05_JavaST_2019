package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.role.Role;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.UserService;
import by.guretsky.info_system.util.PasswordEncoder;
import by.guretsky.info_system.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand extends Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String EMAIL = "email";

    @Override
    public JspPage execute(final HttpServletRequest request)
            throws CustomException {
        JspPage page = PageManager.createPage(PageEnum.SIGN_UP);
        String pass = request.getParameter(PASSWORD_PARAM).trim();
        String login = request.getParameter(LOGIN_PARAM).trim();
        String email = request.getParameter(EMAIL).trim();
        UserService service = factory.createService(UserService.class);
        if (service.findByLogin(login) == null) {
            if (service.findByEmail(email) == null) {
                User user = new User();
                user.setLogin(login);
                user.setPassword(pass);
                user.setEmail(email);
                user.setRole(Role.USER);
                UserValidator validator = new UserValidator();
                Integer userId = service.create(user);
                if (validator.validate(user) &&  userId != 0) {
                    page = PageManager.createPage(PageEnum.SIGN_IN);
                    page.addParameter("msg", "success");
                    return page;
                } else {
                    page.addParameter("msg", "cr_err");
                }
            } else {
                page.addParameter("msg", "em_err");
            }
        } else {
            page.addParameter("msg", "log_err");
        }
        return page;
    }
}
