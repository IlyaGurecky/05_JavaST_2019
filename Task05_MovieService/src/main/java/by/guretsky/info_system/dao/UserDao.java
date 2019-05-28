package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> readAll(int page, int amountPerPage);

    boolean createUserInfo(User user);

    User findByLogin(String login);

    User findByEmail(String email);

    boolean deleteByLogin(String login);

    boolean updateUserInfo(User user);

    boolean changePassword(String pass, int userId);

    Integer countUsers();

    String findPassByLogin(String login);
}
