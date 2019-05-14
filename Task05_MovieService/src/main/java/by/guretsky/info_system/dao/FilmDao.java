package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.Film;

import java.util.List;

public interface FilmDao extends Dao<Film> {
    List<Film> readAll(final int page, final int amountPerPage);

    List<Film> findByName(final String name);

    List<Film> findByCategory(final String category);

    Integer countFilms();
}
