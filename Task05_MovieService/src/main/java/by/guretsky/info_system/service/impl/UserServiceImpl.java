package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.UserDao;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.UserService;
import by.guretsky.info_system.util.PasswordEncoder;

import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {

    @Override
    public List<User> readAll() throws CustomException {
        UserDao dao = daoManager.createAndGetDao(UserDao.class);
        return dao.readAll();
    }

    @Override
    public User findByLoginAndPassword(final String login,
                                       final String password) throws
            CustomException {
        if (!login.isEmpty() && !password.isEmpty()) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            User user = dao.findByLogin(login);
            if (user != null) {
                return PasswordEncoder.checkPassword(password,
                        user.getPassword()) ? user : null;
            } else {
                return null;
            }
        } else {
            throw new CustomException("Login or password is incorrect");
        }
    }

    @Override
    public User findById(final Integer id) throws CustomException {
        if (id != null) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            return dao.findById(id);
        } else {
            throw new CustomException("ID is null");
        }
    }

    @Override
    public boolean delete(final Integer id) throws CustomException {
        if (id != null) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            daoManager.setAutoCommit(false);
            boolean isCorrect = dao.delete(id);
            if (isCorrect) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return isCorrect;
        } else {
            throw new CustomException("ID is null");
        }
    }

    @Override
    public boolean deleteByLogin(String login) throws CustomException {
        if (login != null) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            daoManager.setAutoCommit(false);
            boolean isCorrect = dao.deleteByLogin(login);
            if (isCorrect) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return isCorrect;
        } else {
            throw new CustomException("login is null");
        }
    }

    @Override
    public Integer create(final User user) throws CustomException {
        if (user != null) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            String hashPassword = PasswordEncoder
                    .hashPassword(user.getPassword());
            user.setPassword(hashPassword);
            daoManager.setAutoCommit(false);
            Integer id = dao.create(user);
            user.setId(id);
            if (id != 0 && dao.createUserInfo(user)) {
                daoManager.commit();
            } else {
                daoManager.rollback();
                id = 0;
            }
            daoManager.setAutoCommit(true);
            return id;
        } else {
            throw new CustomException("User obj is null");
        }
    }

    @Override
    public boolean update(final User user) throws CustomException {
        if (user != null) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            daoManager.setAutoCommit(false);
            boolean isCorrect = dao.update(user);
            if (isCorrect && dao.updateUserInfo(user)) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return isCorrect;
        } else {
            throw new CustomException("User obj is null");
        }
    }

    @Override
    public User findByLogin(String login) throws CustomException {
        if (login != null && !login.isEmpty()) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            return dao.findByLogin(login);
        } else {
            throw new CustomException("Login is null");
        }
    }

    @Override
    public User findByEmail(String email) throws CustomException {
        if (email != null && !email.isEmpty()) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            return dao.findByEmail(email);
        } else {
            throw new CustomException("Email is null");
        }
    }
}
