package by.guretsky.info_system.validator;

import by.guretsky.info_system.entity.User;

public class UserValidator implements Validator<User> {
    private static final String EMAIL_REGEX
            = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
    private static final int MAX_LOGIN_LENGTH = 32;

    public boolean validate(final User user) {
        return user.getEmail().matches(EMAIL_REGEX)
                && user.getLogin().length() <= MAX_LOGIN_LENGTH;
    }

    public boolean isCorrectEmail(final String email) {
        return email.matches(EMAIL_REGEX);
    }
}
