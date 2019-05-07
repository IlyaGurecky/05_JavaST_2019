package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.Watched;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface WatchedService extends Service {
    List<Watched> readAllByUserId(final Integer id) throws CustomException;

    boolean deleteByUserAndFilmId(final Integer userId, final Integer filmId)
            throws CustomException;

    Integer create(final Watched entity) throws CustomException;
}
