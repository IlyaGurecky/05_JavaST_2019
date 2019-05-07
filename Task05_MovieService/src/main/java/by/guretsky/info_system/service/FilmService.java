package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface FilmService extends Service {
    List<Film> readAll() throws CustomException;

    Film findByName(final String name) throws CustomException;

    List<Film> findByCategory(final String category) throws CustomException;

    Film findById(final Integer id) throws CustomException;

    Integer create(final Film film) throws CustomException;

    boolean update(final Film film) throws CustomException;

    boolean delete(final Integer id) throws CustomException;
}
