package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface SeeLaterService extends Service {
    List<SeeLater> readAllByUserId(final Integer id,
                                   final int pageNum,
                                   final int amountPerPage)
            throws CustomException;

    List<SeeLater> readAllByUserId(final Integer userId)
            throws CustomException;

    boolean deleteByUserAndFilmId(final Integer userId, final Integer filmId)
            throws CustomException;

    Integer create(final SeeLater entity) throws CustomException;

    Integer countFilms(final int userId) throws CustomException;
}
