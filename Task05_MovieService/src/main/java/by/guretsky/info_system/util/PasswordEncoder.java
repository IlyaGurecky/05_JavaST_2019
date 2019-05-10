package by.guretsky.info_system.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public final class PasswordEncoder {

    private PasswordEncoder() {
    }

    public static boolean checkPassword(final String password, final String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public static String hashPassword(final String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
