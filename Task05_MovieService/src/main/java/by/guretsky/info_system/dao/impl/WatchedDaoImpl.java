package by.guretsky.info_system.dao.impl;

import by.guretsky.info_system.dao.BaseDao;
import by.guretsky.info_system.dao.WatchedDao;
import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.Watched;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class WatchedDaoImpl extends BaseDao implements WatchedDao {
    private static final Logger LOGGER =
            LogManager.getLogger(WatchedDaoImpl.class);
    private static final String SELECT_ALL_BY_USER_ID = "SELECT `w_f`.viewing_date,"
            + " `films`.id AS `film_id`, `films`.name, `films`.image_path, "
            + "`films`.premier_date, `films`.description, `cc`.name AS `category`,"
            + " `c`.name  AS `country` FROM `watched_films` AS `w_f` INNER JOIN films"
            + " ON `w_f`.film_id = films.id LEFT OUTER JOIN categories_catalog cc "
            + "ON films.category_id = cc.id LEFT OUTER JOIN countries_catalog c "
            + "ON films.country_id = c.id WHERE `w_f`.user_id = ? ORDER BY `w_f`.id DESC";
    private static final String CREATE = "INSERT INTO `watched_films` (user_id, " +
            "film_id, viewing_date)  VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM `watched_films`" +
            " WHERE user_id = ? AND film_id = ?";

    @Override
    public List<Watched> readAllByUserId(final Integer id) {
        List<Watched> films = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_BY_USER_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Watched watched = new Watched();
                User user = new User();
                user.setId(id);
                watched.setUser(user);
                watched.setViewingDate(resultSet.getDate("viewing_date"));
                Film film = new Film();
                film.setCountry(resultSet.getString("country"));
                film.setCategory(resultSet.getString("category"));
                film.setPremierDate(resultSet.getDate("premier_date"));
                film.setImageName(resultSet.getString("image_path"));
                film.setDescription(resultSet.getString("description"));
                film.setName(resultSet.getString("name"));
                film.setId(resultSet.getInt("film_id"));
                watched.setFilm(film);
                films.add(watched);
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
    public Integer create(final Watched entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE)) {
            statement.setInt(1, entity.getUser().getId());
            statement.setInt(2, entity.getFilm().getId());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        }
        return 0;
    }

    @Override
    public Watched findById(final Integer id) {
        return null;
    }

    @Override
    public boolean delete(final Integer id) {
        return false;
    }

    @Override
    public boolean update(final Watched entity) {
        return false;
    }
}
