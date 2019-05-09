package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface UserService extends Service {
    List<User> readAll() throws CustomException;

    User findByLoginAndPassword(final String login, final String password)
            throws CustomException;

    User findById(final Integer id) throws CustomException;

    boolean delete(final Integer id) throws CustomException;

    Integer create(final User user) throws CustomException;

    boolean update(final User user) throws CustomException;

    User findByLogin(final String login) throws CustomException;

    User findByEmail(final String email) throws CustomException;
}
