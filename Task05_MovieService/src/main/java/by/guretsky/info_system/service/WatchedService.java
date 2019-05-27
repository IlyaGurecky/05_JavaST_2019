package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.Watched;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface WatchedService extends Service {

    List<Watched> readAllByUserId(Integer id,
                                  int pageNum,
                                  int amountPerPage)
            throws CustomException;

    void updateViewingDate(Integer userId, Integer filmId)
            throws CustomException;

    Integer findIdByUserAndFilmId(Integer userId, Integer filmId)
            throws CustomException;

    Integer create(Watched entity) throws CustomException;

    Integer countFilms(int userId) throws CustomException;
}
