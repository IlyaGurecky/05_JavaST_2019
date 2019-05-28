package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface UserService extends Service {
    List<User> readAll(int page, int amountPerPage)
            throws CustomException;

    Integer countUsers() throws CustomException;

    User findByLoginAndPassword(String login, String password)
            throws CustomException;

    User findById(Integer id) throws CustomException;

    boolean delete(Integer id) throws CustomException;

    boolean deleteByLogin(String login) throws CustomException;

    Integer create(User user) throws CustomException;

    boolean update(User user) throws CustomException;

    boolean changePassword(String pass, int userId)
            throws CustomException;

    User findByLogin(String login) throws CustomException;

    User findByEmail(String email) throws CustomException;

    String findPassByLogin(String login) throws CustomException;
}
