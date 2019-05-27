package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.SeeLater;

import java.util.List;

public interface SeeLaterDao extends Dao<SeeLater> {
    List<SeeLater> readAllByUserId(Integer id,
                                   int pageNum,
                                   int amountPerPage);

    List<SeeLater> readAllByUserId(Integer userId);

    boolean deleteByUserAndFilmId(Integer userId, Integer filmId);

    Integer countFilms(int userId);
}
