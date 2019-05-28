package by.guretsky.info_system.command.user;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.UserService;
import by.guretsky.info_system.util.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request)
            throws CustomException {
        String oldPass = request.getParameter("old_password");
        String newPass1 = request.getParameter("new_password1");
        String newPass2 = request.getParameter("new_password2");
        User user =
                (User) request.getSession(false).getAttribute("user");
        UserService service = factory.createService(UserService.class);
        if (!newPass1.equals(newPass2) || !isOldPassIsCorrect(service,
                oldPass, user.getLogin())) {
            return PageManager.createPage(PageEnum.PROFILE);
        }
        String hashPass = PasswordEncoder.hashPassword(newPass1);
        service.changePassword(hashPass, user.getId());

        return PageManager.createPage(PageEnum.PROFILE);
    }

    private boolean isOldPassIsCorrect(final UserService service,
                                       final String pass,
                                       final String userLogin)
            throws CustomException {
        String userPass = service.findPassByLogin(userLogin);
        return PasswordEncoder.checkPassword(pass,userPass);
    }
}
