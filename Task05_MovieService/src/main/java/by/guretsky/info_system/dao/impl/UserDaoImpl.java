package by.guretsky.info_system.dao.impl;

import by.guretsky.info_system.dao.BaseDao;
import by.guretsky.info_system.dao.UserDao;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.role.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String SELECT_ALL = "SELECT `users`.id, `users`.login, `users`.password, `users`.role, "
            + "`user_info`.birth_date, `user_info`.email, `user_info`.sex, "
            + "`countries_catalog`.name AS `country` FROM `users` LEFT OUTER JOIN user_info ON users.id"
            + " = user_info.user_id LEFT OUTER JOIN countries_catalog ON user_info.country_id = "
            + "countries_catalog.id ORDER BY `users`.id";
    private static final String SELECT_BY_LOGIN = "SELECT `users`.id, `users`.role, `users`.password,"
            + "`user_info`.birth_date, `user_info`.email, `user_info`.sex, "
            + "`countries_catalog`.name AS `country` FROM `users` LEFT OUTER JOIN user_info ON users.id"
            + " = user_info.user_id LEFT OUTER JOIN countries_catalog ON user_info.country_id = "
            + "countries_catalog.id WHERE `users`.login = ?";
    private static final String SELECT_BY_ID = "SELECT `users`.id, `users`.role, `users`.login, `users`.password,"
            + "`user_info`.birth_date, `user_info`.email, `user_info`.sex, "
            + "`countries_catalog`.name AS `country` FROM `users` LEFT OUTER JOIN user_info ON users.id"
            + " = user_info.user_id LEFT OUTER JOIN countries_catalog ON user_info.country_id = "
            + "countries_catalog.id WHERE `users`.id = ?";
    private static final String DELETE = "DELETE FROM `users` WHERE id = ?";
    private static final String CREATE = "INSERT INTO `users` (login, password, role) VALUES (?, ?, ?)";

    private static final String CREATE_USER_INFO = "INSERT INTO `user_info` (email, user_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE `users` SET login = ?, password = ?, role = ? WHERE id = ?";
    private static final String UPDATE_USER_INFO = " UPDATE `user_info` SET email = ?, sex = ?, "
            + "birth_date = ?, country_id = ? WHERE user_id = ?";

    @Override
    public List<User> readAll() {
        List<User> users = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setCountry(resultSet.getString("country"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.findById(resultSet.getInt("role")));
                user.setSex(resultSet.getString("sex"));
                user.setId(resultSet.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error", e);
            }
        }
        return users;
    }

    @Override
    public User findByLogin(final String login) {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setCountry(resultSet.getString("country"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.findById(resultSet.getInt("role")));
                user.setSex(resultSet.getString("sex"));
                user.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error", e);
            }
        }
        return user;
    }

    @Override
    public User findById(final Integer id) {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setCountry(resultSet.getString("country"));
                user.setCountry(resultSet.getString("country"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.findById(resultSet.getInt("role")));
                user.setSex(resultSet.getString("sex"));
                user.setId(id);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error", e);
            }
        }
        return user;
    }

    @Override
    public boolean delete(final Integer id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        }
        return false;
    }

    @Override
    public Integer create(final User entity) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole().ordinal());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error", e);
            }
        }
        return 0;
    }

    @Override
    public boolean createUserInfo(final User user) {
        try (PreparedStatement statement =
                     connection.prepareStatement(CREATE_USER_INFO)) {
            statement.setString(1, user.getEmail());
            statement.setInt(2, user.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        }
        return false;
    }

    @Override
    public boolean update(final User entity) {
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole().ordinal());
            statement.setInt(4, entity.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        }
        return false;
    }

    @Override
    public boolean updateUserInfo(final User user) {
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE_USER_INFO)) {
            statement.setString(1, user.getEmail());
            if (user.getSex() != null) {
                statement.setString(2, user.getSex());
            } else {
                statement.setNull(2, Types.CHAR);
            }
            if (user.getBirthDate() != null) {
                statement.setDate(3, new Date(user.getBirthDate().getTime()));
            } else {
                statement.setNull(3, Types.DATE);
            }
//            if (entity.getCountry() != null
//                    && entity.getCountry().getId() != null) {
//                statement.setInt(8, entity.getCountry().getId());
//            } else {
//                statement.setNull(8, Types.INTEGER);
//            }
            statement.setNull(4, Types.INTEGER);
            statement.setInt(5, user.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Pr statement error", e);
        }
        return false;
    }
}
