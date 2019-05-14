package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> readAll();

    boolean createUserInfo(final User user);

    User findByLogin(final String login);

    User findByEmail(final String email);

    boolean deleteByLogin(final String login);

    boolean updateUserInfo(final User user);
}
