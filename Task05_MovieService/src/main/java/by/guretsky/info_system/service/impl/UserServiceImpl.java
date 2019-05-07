package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.DaoManager;
import by.guretsky.info_system.dao.UserDao;
import by.guretsky.info_system.dao.impl.UserDaoImpl;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
                return checkPassword(password, user.getPassword()) ? user : null;
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
    public Integer create(final User user) throws CustomException {
        if (user != null) {
            UserDao dao = daoManager.createAndGetDao(UserDao.class);
            String hashPassword = bCrypt(user.getPassword());
            user.setPassword(hashPassword);
            daoManager.setAutoCommit(false);
            Integer id = dao.create(user);
            if (id != 0 && dao.createUserInfo(user)) {
                daoManager.commit();
            } else {
                daoManager.rollback();
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

    private boolean checkPassword(final String password, final String hash) {
        return BCrypt.checkpw(password, hash);
    }

    private String bCrypt(final String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
