package by.guretsky.info_system.command.admin;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.role.Role;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserAddCommand extends Command {
    private static final String LOGIN_FIELD = "login";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String ROLE_FIELD = "role";

    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        String login = request.getParameter(LOGIN_FIELD);
        String email = request.getParameter(EMAIL_FIELD);
        String password = request.getParameter(PASSWORD_FIELD);
        Role role = Role.findById(Integer
                .parseInt(request.getParameter(ROLE_FIELD)));
        UserService service = factory.createService(UserService.class);
        if (service.findByLogin(login)
                == null && service.findByEmail(email) == null) {
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            return PageManager.createPage(PageEnum.USERS);
        }
        return PageManager.createPage(PageEnum.USER_ADD);
    }
}