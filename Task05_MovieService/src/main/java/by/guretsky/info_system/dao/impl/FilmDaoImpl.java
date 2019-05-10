package by.guretsky.info_system.dao.impl;

import by.guretsky.info_system.dao.BaseDao;
import by.guretsky.info_system.dao.FilmDao;
import by.guretsky.info_system.entity.Film;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class FilmDaoImpl extends BaseDao implements FilmDao {
    private static final Logger LOGGER = LogManager.getLogger(FilmDaoImpl.class);
    private static final String SELECT_ALL = "SELECT `f`.id, `f`.name, `f`.premier_date, "
            + "`f`.description, `f`.image_path, `countries_catalog`.name  AS `country`, "
            + "`categories_catalog`.name AS `category` FROM `films` AS `f` LEFT OUTER JOIN "
            + "categories_catalog ON `f`.category_id = `categories_catalog`.id LEFT OUTER JOIN "
            + "countries_catalog ON f.country_id = countries_catalog.id ORDER BY `f`.id DESC";
    private static final String SELECT_BY_NAME = "SELECT `f`.id, `f`.premier_date, "
            + "`f`.description, `f`.image_path, `countries_catalog`.name  AS `country`, "
            + "`categories_catalog`.name AS `category` FROM `films` AS `f` LEFT OUTER JOIN "
            + "categories_catalog ON `f`.category_id = `categories_catalog`.id LEFT OUTER JOIN "
            + "countries_catalog ON f.country_id = countries_catalog.id WHERE `f`.name = ?";
    private static final String SELECT_BY_CATEGORY = "SELECT `f`.id, `f`.name, `f`.premier_date, "
            + "`f`.description, `f`.image_path, `countries_catalog`.name  AS `country`, "
            + "`categories_catalog`.name AS `category` FROM `films` AS `f` LEFT OUTER JOIN "
            + "categories_catalog ON `f`.category_id = `categories_catalog`.id LEFT OUTER JOIN "
            + "countries_catalog ON f.country_id = countries_catalog.id WHERE `countries_catalog`.name = ?"
            + "ORDER BY `f`.id DESC";
    private static final String SELECT_BY_ID = "SELECT `f`.name, `f`.premier_date, "
            + "`f`.description, `f`.image_path, `countries_catalog`.name  AS `country`, "
            + "`categories_catalog`.name AS `category` FROM `films` AS `f` LEFT OUTER JOIN "
            + "categories_catalog ON `f`.category_id = `categories_catalog`.id LEFT OUTER JOIN "
            + "countries_catalog ON f.country_id = countries_catalog.id WHERE `f`.id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM `films` WHERE id = ?";
    private static final String CREATE_FILM = "INSERT INTO `films` (name, premier_date,"
            + " country_id, category_id, image_path, description) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE `films` SET name = ?, premier_date = ?, "
            + "description = ?, image_path = ?, country_id = ?, category_id = ? WHERE id = ?;";
    private static final String SELECT_CATEGORY_BY_NAME = "SELECT `categories_catalog`.id AS `category_id` "
            + "FROM `categories_catalog` WHERE `categories_catalog`.name = ?";
    private static final String SELECT_COUNTRY_BY_NAME = "SELECT `countries_catalog`.id AS `country_id` "
            + "FROM `countries_catalog` WHERE `countries_catalog`.name = ?";

    @Override
    public List<Film> readAll() {
        List<Film> films = new LinkedList<>();
        ResultSet result = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SELECT_ALL);
            result = st.executeQuery();
            while (result.next()) {
                Film film = new Film();
                film.setName(result.getString("name"));
                film.setCategory(result.getString("category"));
                film.setId(result.getInt("id"));
                film.setCountry(result.getString("country"));
                film.setDescription(result.getString("description"));
                film.setImageName(result.getString("image_path"));
                film.setPremierDate(result.getDate("premier_date"));
                films.add(film);
            }
        } catch (SQLException e) {
            LOGGER.error("Prepare statement exception", e);
        } finally {
            try {
                closeResources(st, result);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error", e);
            }
        }
        return films;
    }

    @Override
    public Film findByName(final String name) {
        Film film = null;
        ResultSet result = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SELECT_BY_NAME);
            st.setString(1, name);
            result = st.executeQuery();
            if (result.next()) {
                film = new Film();
                film.setName(name);
                film.setCategory(result.getString("category"));
                film.setId(result.getInt("id"));
                film.setCountry(result.getString("country"));
                film.setDescription(result.getString("description"));
                film.setImageName(result.getString("image_path"));
                film.setPremierDate(result.getDate("premier_date"));
            }
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        } finally {
            try {
                closeResources(st, result);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error");
            }
        }
        return film;
    }

    @Override
    public List<Film> findByCategory(final String category) {
        List<Film> films = new LinkedList<>();
        ResultSet result = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SELECT_BY_CATEGORY);
            st.setString(1, category);
            result = st.executeQuery();
            while (result.next()) {
                Film film = new Film();
                film.setName(result.getString("name"));
                film.setId(result.getInt("id"));
                film.setCategory(category);
                film.setCountry(result.getString("country"));
                film.setDescription(result.getString("description"));
                film.setImageName(result.getString("image_path"));
                film.setPremierDate(result.getDate("premier_date"));
                films.add(film);
            }
        } catch (SQLException e) {
            LOGGER.error("Prepare statement", e);
        } finally {
            try {
                closeResources(st, result);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error");
            }
        }
        return films;
    }

    @Override
    public Film findById(final Integer id) {
        Film film = null;
        ResultSet result = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SELECT_BY_ID);
            st.setInt(1, id);
            result = st.executeQuery();
            if (result.next()) {
                film = new Film();
                film.setName(result.getString("name"));
                film.setId(id);
                film.setCategory(result.getString("category"));
                film.setCountry(result.getString("country"));
                film.setDescription(result.getString("description"));
                film.setImageName(result.getString("image_path"));
                film.setPremierDate(result.getDate("premier_date"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                closeResources(st, result);
            } catch (SQLException e) {
                LOGGER.error("Resource closeResources error", e);
            }
        }
        return film;
    }

    @Override
    public boolean delete(final Integer id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Sql exception", e);
        }
        return false;
    }

    @Override
    public Integer create(final Film entity) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Integer categoryId = null;
            if(entity.getCategory() != null) {
                statement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME);
                statement.setString(1, entity.getCategory());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    categoryId = resultSet.getInt("category_id");
                } else {
                    return 0;
                }
                closeResources(statement, resultSet);
            }

            Integer countryId = null;
            if(entity.getCountry() != null) {
                statement = connection.prepareStatement(SELECT_COUNTRY_BY_NAME);
                statement.setString(1, entity.getCountry());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    countryId = resultSet.getInt("country_id");
                } else {
                    return 0;
                }
                closeResources(statement, resultSet);
            }

            statement = connection.prepareStatement(CREATE_FILM);
            statement.setString(1, entity.getName());
            statement.setDate(2,
                    new Date(entity.getPremierDate().getTime()));
            if (categoryId != null) {
                statement.setInt(4, categoryId);
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            if (entity.getDescription() != null) {
                statement.setString(6, entity.getDescription());
            } else {
                statement.setNull(6, Types.LONGNVARCHAR);
            }
            if (entity.getImageName() != null) {
                statement.setString(5, entity.getImageName());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }
            if (countryId != null) {
                statement.setInt(3, countryId);
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("Key generated error");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
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
    public boolean update(final Film entity) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Integer categoryId = null;
            if (entity.getCategory() != null) {
                statement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME);
                statement.setString(1, entity.getCategory());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    categoryId = resultSet.getInt("category_id");
                } else {
                    return false;
                }
                closeResources(statement, resultSet);
            }

            Integer countryId = null;
            if (entity.getCountry() != null) {
                statement = connection.prepareStatement(SELECT_COUNTRY_BY_NAME);
                statement.setString(1, entity.getCountry());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    countryId = resultSet.getInt("country_id");
                } else {
                    return false;
                }
                closeResources(statement, resultSet);
            }

            statement = connection.prepareStatement(UPDATE);
            if (entity.getDescription() != null) {
                statement.setString(3, entity.getDescription());
            } else {
                statement.setNull(3, Types.LONGNVARCHAR);
            }
            statement.setString(1, entity.getName());
            statement.setDate(2, new Date(entity.getPremierDate().getTime()));
            if (entity.getImageName() != null) {
                statement.setString(4, entity.getImageName());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }
            if (categoryId != null) {
                statement.setInt(6, categoryId);
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            if (countryId != null) {
                statement.setInt(5, countryId);
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setInt(7, entity.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Prepare statement error", e);
        } finally {
            try {
                closeResources(statement, resultSet);
            } catch (SQLException e) {
                LOGGER.error("Resources close error");
            }
        }
        return false;
    }
}
