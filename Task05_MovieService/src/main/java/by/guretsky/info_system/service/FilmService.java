package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface FilmService extends Service {
    List<Film> readAll(int page, int amountPerPage)
            throws CustomException;

    List<Film> findByName(String name) throws CustomException;

    List<Film> findByCategory(String category) throws CustomException;

    Film findById(Integer id) throws CustomException;

    Integer create(Film film) throws CustomException;

    boolean update(Film film) throws CustomException;

    boolean delete(Integer id) throws CustomException;

    Integer countFilms() throws CustomException;
}
