package by.guretsky.info_system.command;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements ActionCommand {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String EMAIL = "email";

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
