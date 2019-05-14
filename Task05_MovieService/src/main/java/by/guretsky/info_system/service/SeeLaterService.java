package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface SeeLaterService extends Service {
    List<SeeLater> readAllByUserId(final Integer id) throws CustomException;

    boolean deleteByUserAndFilmId(final Integer userId, final Integer filmId)
            throws CustomException;

    Integer create(final SeeLater entity) throws CustomException;
}
