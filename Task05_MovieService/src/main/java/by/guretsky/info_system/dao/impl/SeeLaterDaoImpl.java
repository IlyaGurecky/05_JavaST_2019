package by.guretsky.info_system.dao.impl;

import by.guretsky.info_system.dao.BaseDao;
import by.guretsky.info_system.dao.SeeLaterDao;
import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SeeLaterDaoImpl extends BaseDao implements SeeLaterDao {
    private static final Logger LOGGER =
            LogManager.getLogger(SeeLaterDaoImpl.class);

    private static final String SELECT_ALL_BY_USER_ID = "SELECT `s_l`.added_date,"
            + " `films`.id AS `film_id`, `films`.name, `films`.image_path, "
            + "`films`.premier_date, `films`.description, `cc`.name AS `category`,"
            + " `c`.name  AS `country` FROM `see_later` AS `s_l` INNER JOIN films"
            + " ON s_l.film_id = films.id LEFT OUTER JOIN categories_catalog cc "
            + "ON films.category_id = cc.id LEFT OUTER JOIN countries_catalog c "
            + "ON films.country_id = c.id WHERE `s_l`.user_id = ? ORDER BY `s_l`.id DESC";
    private static final String CREATE = "INSERT INTO `see_later` (user_id, " +
            "film_id, added_date)  VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM `see_later`" +
            " WHERE user_id = ? AND film_id = ?";


    @Override
    public List<SeeLater> readAllByUserId(final Integer id) {
        List<SeeLater> films = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_BY_USER_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SeeLater seeLater = new SeeLater();
                User user = new User();
                user.setId(id);
                seeLater.setUser(user);
                seeLater.setAddedDate(resultSet.getDate("added_date"));
                Film film = new Film();
                film.setCountry(resultSet.getString("country"));
                film.setCategory(resultSet.getString("category"));
                film.setPremierDate(resultSet.getDate("premier_date"));
                film.setImageName(resultSet.getString("image_path"));
                film.setDescription(resultSet.getString("description"));
                film.setName(resultSet.getString("name"));
                film.setId(resultSet.getInt("film_id"));
                seeLater.setFilm(film);
                films.add(seeLater);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Resources closeResources error");
            }
        }
        return films;
    }

    @Override
    public boolean deleteByUserAndFilmId(final Integer userId,
                                      final Integer filmId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE)) {
            statement.setInt(1, userId);
            statement.setInt(2, filmId);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("SQL error", e);
        }
        return false;
    }

    @Override
    public Integer create(final SeeLater entity) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getUser().getId());
            statement.setInt(2, entity.getFilm().getId());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Closing error", e);
            }
        }
        return 0;
    }

    @Override
    public boolean delete(final Integer id) {
        return false;
    }

    @Override
    public SeeLater findById(final Integer id) {
        return null;
    }

    @Override
    public boolean update(final SeeLater entity) {
        return false;
    }
}
