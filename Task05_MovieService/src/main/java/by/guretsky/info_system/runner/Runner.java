package by.guretsky.info_system.runner;

import by.guretsky.info_system.dao.DaoManager;
import by.guretsky.info_system.dao.FilmDao;
import by.guretsky.info_system.dao.SeeLaterDao;
import by.guretsky.info_system.dao.impl.FilmDaoImpl;
import by.guretsky.info_system.dao.impl.SeeLaterDaoImpl;
import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.entity.role.Role;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_service_db", "movie_service_user", "password");
        SeeLaterDao dao = new SeeLaterDaoImpl();
        ((SeeLaterDaoImpl) dao).setConnection(connection);
        List<SeeLater> seeLater = dao.readAllByUserId(3);
        List<Film> seeLaterFilms = new LinkedList<>();
        seeLater.stream().map(SeeLater::getFilm).forEach(seeLaterFilms::add);
        seeLaterFilms.stream().map(Film::getName).forEach(System.out::println);
    }
}


