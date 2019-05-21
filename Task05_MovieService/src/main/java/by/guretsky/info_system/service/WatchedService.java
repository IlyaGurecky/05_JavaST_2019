package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.Watched;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface WatchedService extends Service {

    List<Watched> readAllByUserId(final Integer id,
                                  final int pageNum,
                                  final int amountPerPage)
            throws CustomException;

    void updateViewingDate(final Integer userId, final Integer filmId)
            throws CustomException;

    Integer findIdByUserAndFilmId(final Integer userId, final Integer filmId)
            throws CustomException;

    Integer create(final Watched entity) throws CustomException;

    Integer countFilms(final int userId) throws CustomException;
}
