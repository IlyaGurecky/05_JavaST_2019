package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.Watched;

import java.util.List;

public interface WatchedDao extends Dao<Watched> {
    List<Watched> readAllByUserId(final Integer id,
                                  final int pageNum,
                                  final int amountPerPage);

    boolean updateViewingDate(final Integer userId, final Integer filmId);

    Integer findIdByUserAndFilmId(final Integer userId, final Integer filmId);

    Integer countFilms(final int userId);
}
