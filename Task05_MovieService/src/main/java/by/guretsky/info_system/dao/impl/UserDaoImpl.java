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
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String SELECT_ALL = "SELECT `users`.id, `users`.login, `users`.password, `users`.role, "
            + "`user_info`.birth_date, `user_info`.email, `user_info`.sex, "
            + "`countries_catalog`.name AS `country` FROM `users` LEFT OUTER JOIN user_info ON users.id"
            + " = user_info.user_id LEFT OUTER JOIN countries_catalog ON user_info.country_id = "
            + "countries_catalog.id ORDER BY `users`.id LIMIT ? OFFSET ?";
    private static final String SELECT_BY_LOGIN = "SELECT `users`.id, `users`.role, `users`.password,"
            + "`user_info`.birth_date, `user_info`.email, `user_info`.sex, "
            + "`countries_catalog`.name AS `country` FROM `users` LEFT OUTER JOIN user_info ON users.id"
            + " = user_info.user_id LEFT OUTER JOIN countries_catalog ON user_info.country_id = "
            + "countries_catalog.id WHERE `users`.login = ?";
    private static final String SELECT_BY_EMAIL = "SELECT `users`.id, `users`.login, `users`.role, "
            + "`user_info`.birth_date, `user_info`.sex, "
            + "`countries_catalog`.name AS `country` FROM `users` LEFT OUTER JOIN user_info ON users.id"
            + " = user_info.user_id LEFT OUTER JOIN countries_catalog ON user_info.country_id = "
            + "countries_catalog.id WHERE `user_info`.email = ?";
    private static final String SELECT_BY_ID = "SELECT `users`.id, `users`.role, `users`.login, `users`.password,"
            + "`user_info`.birth_date, `user_info`.email, `user_info`.sex, "
            + "`countries_catalog`.name AS `country` FROM `users` LEFT OUTER JOIN user_info ON users.id"
            + " = user_info.user_id LEFT OUTER JOIN countries_catalog ON user_info.country_id = "
            + "countries_catalog.id WHERE `users`.id = ?";
    private static final String DELETE = "DELETE FROM `users` WHERE id = ?";
    private static final String DELETE_BY_LOGIN = "DELETE FROM `users` WHERE login = ?";
    private static final String CREATE = "INSERT INTO `users` (login, password, role) VALUES (?, ?, ?)";

    private static final String CREATE_USER_INFO = "INSERT INTO `user_info` (email, user_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE `users` SET login = ?, role = ? WHERE id = ?";
    private static final String UPDATE_USER_INFO = " UPDATE `user_info` SET email = ?, sex = ?, "
            + "birth_date = ?, country_id = ? WHERE user_id = ?";
    private static final String SELECT_COUNTRY_BY_NAME = "SELECT `countries_catalog`.id AS `country_id` "
            + "FROM `countries_catalog` WHERE `countries_catalog`.name = ?";
    private static final String COUNT_USERS = "SELECT COUNT(id) AS `users_amount` FROM `users`";
    private static final String CHANGE_PASSWORD = "UPDATE `users` SET password = ? WHERE id = ?";

    @Override
    public List<User> readAll(final int page, final int amountPerPage) {
        List<User> users = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL);
            statement.setInt(1, amountPerPage);
            statement.setInt(2, (page - 1) * amountPerPage);
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
    public Integer countUsers() {
        ResultSet result = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(COUNT_USERS);
            result = st.executeQuery();
            if (result.next()) {
                return result.getInt("users_amount");
            }
        } catch (SQLException e) {
            LOGGER.error("Count users errors", e);
        } finally {
            try {
                closeResources(st, result);
            } catch (SQLException e) {
                LOGGER.error("Close error");
            }
        }
        return null;
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
    public User findByEmail(final String email) {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(email);
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setCountry(resultSet.getString("country"));
                user.setLogin(resultSet.getString("login"));
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
                LOGGER.error("Resource close error", e);
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
                LOGGER.debug(resultSet.getDate("birth_date").toLocalDate());
                user.setBirthDate(resultSet.getDate("birth_date"));
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
                LOGGER.error("Resource close error", e);
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
            LOGGER.error("Delete error", e);
        }
        return false;
    }

    @Override
    public boolean deleteByLogin(final String login) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_BY_LOGIN)) {
            statement.setString(1, login);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Delete by login error", e);
        }
        return false;
    }

    @Override
    public Integer create(final User entity) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole().ordinal());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There isn't generated key "
                        + "after add into table");
            }
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Resource close error", e);
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
    public boolean changePassword(final String pass, final int userId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(CHANGE_PASSWORD)) {
            statement.setString(1, pass);
            statement.setInt(2, userId);
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
            statement.setInt(2, entity.getRole().ordinal());
            statement.setInt(3, entity.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        }
        return false;
    }

    @Override
    public boolean updateUserInfo(final User user) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Integer countryId = null;
            if (user.getCountry() != null && !user.getCountry().isEmpty()) {
                statement = connection.prepareStatement(SELECT_COUNTRY_BY_NAME);
                statement.setString(1, user.getCountry());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    countryId = resultSet.getInt("country_id");
                } else {
                    return false;
                }
                closeResources(statement, resultSet);
            }

            statement = connection.prepareStatement(UPDATE_USER_INFO);
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
            if (countryId != null) {
                statement.setInt(4, countryId);
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            statement.setInt(5, user.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Pr statement error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Prepared statement close error", e);
            }
        }
        return false;
    }
}
