package by.guretsky.info_system.command.user;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.UserService;
import by.guretsky.info_system.util.PasswordEncoder;
import by.guretsky.info_system.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditProfileCommand extends Command {
    private static final Logger LOGGER =
            LogManager.getLogger(EditProfileCommand.class);

    @Override
    public JspPage execute(final HttpServletRequest request)
            throws CustomException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String birthDateStr = request.getParameter("date");
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyyy-mm-dd")
                    .parse(birthDateStr);
        } catch (ParseException e) {
            LOGGER.error("Date parse error");
        }
        String country = request.getParameter("country");
        String sex = request.getParameter("sex");
        String pass = request.getParameter("password");
        User user = (User) request.getSession(false).getAttribute("user");
        if (isCorrectData(login, email, pass)) {
            UserService service = factory.createService(UserService.class);
            if (isAvailableLogin(login, service, user)
                    && isAvailableEmail(email, service, user)
                    && isAvailablePass(pass, user)) {
                User editedUser = new User();
                editedUser.setId(user.getId());
                editedUser.setRole(user.getRole());
                editedUser.setLogin(login);
                editedUser.setEmail(email);
                editedUser.setBirthDate(birthDate);
                editedUser.setCountry(country);
                editedUser.setSex(sex);
                if (service.update(editedUser)) {
                    HttpSession session = request.getSession(false);
                    session.removeAttribute("user");
                    session.setAttribute("user",
                            service.findById(editedUser.getId()));
                }
            }
        } else {
            throw new CustomException("Incorrect data");
        }
        return PageManager.createPage(PageEnum.PROFILE);
    }

    private boolean isCorrectData(final String login,
                                  final String email,
                                  final String pass) {
        return login != null && !login.isEmpty()
                && email != null && !email.isEmpty()
                && pass != null && !pass.isEmpty();
    }

    private boolean isAvailableLogin(final String login,
                                     final UserService service,
                                     final User user) throws CustomException {
        User u = service.findByLogin(login);
        if (u != null) {
            return user.getId().equals(u.getId());
        }
        return true;
    }

    private boolean isAvailableEmail(final String email,
                                     final UserService service,
                                     final User user) throws CustomException {
        User u = service.findByLogin(email);
        if (u != null) {
            UserValidator validator = new UserValidator();
            return user.getId().equals(u.getId())
                    && validator.isCorrectEmail(email);
        }
        return true;
    }

    private boolean isAvailablePass(final String pass, final User user) {
        return PasswordEncoder.checkPassword(pass, user.getPassword());
    }
}
