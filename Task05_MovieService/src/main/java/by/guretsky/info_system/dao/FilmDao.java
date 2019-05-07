package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.Film;

import java.util.List;

public interface FilmDao extends Dao<Film> {
    List<Film> readAll();

    Film findByName(String name);

    List<Film> findByCategory(String category);
}
