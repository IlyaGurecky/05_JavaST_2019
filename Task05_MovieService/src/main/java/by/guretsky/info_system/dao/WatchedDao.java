package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.Watched;

import java.util.List;

public interface WatchedDao extends Dao<Watched> {
    List<Watched> readAllByUserId(final Integer id);

    boolean deleteByUserAndFilmId(final Integer userId, final Integer filmId);
}
