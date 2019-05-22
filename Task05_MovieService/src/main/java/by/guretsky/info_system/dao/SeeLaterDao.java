package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.SeeLater;

import java.util.List;

public interface SeeLaterDao extends Dao<SeeLater> {
    List<SeeLater> readAllByUserId(final Integer id,
                                   final int pageNum,
                                   final int amountPerPage);

    List<SeeLater> readAllByUserId(final Integer userId);

    boolean deleteByUserAndFilmId(final Integer userId, final Integer filmId);

    Integer countFilms(final int userId);
}
