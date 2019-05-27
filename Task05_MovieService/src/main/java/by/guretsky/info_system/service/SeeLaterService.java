package by.guretsky.info_system.service;

import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.exception.CustomException;

import java.util.List;

public interface SeeLaterService extends Service {
    List<SeeLater> readAllByUserId(Integer id,
                                   int pageNum,
                                   int amountPerPage)
            throws CustomException;

    List<SeeLater> readAllByUserId(Integer userId)
            throws CustomException;

    boolean deleteByUserAndFilmId(Integer userId, Integer filmId)
            throws CustomException;

    Integer create(SeeLater entity) throws CustomException;

    Integer countFilms(int userId) throws CustomException;
}
