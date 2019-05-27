package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.Watched;

import java.util.List;

public interface WatchedDao extends Dao<Watched> {
    List<Watched> readAllByUserId(Integer id,
                                  int pageNum,
                                  int amountPerPage);

    boolean updateViewingDate(Integer userId, Integer filmId);

    Integer findIdByUserAndFilmId(Integer userId, Integer filmId);

    Integer countFilms(int userId);
}
