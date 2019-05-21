package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.WatchedDao;
import by.guretsky.info_system.entity.Watched;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.WatchedService;

import java.util.List;

public class WatchedServiceImpl extends ServiceImpl implements WatchedService {
    @Override
    public List<Watched> readAllByUserId(final Integer id,
                                         final int pageNum,
                                         final int amountPerPage) throws
            CustomException {
        WatchedDao dao = daoManager.createAndGetDao(WatchedDao.class);
        if (id != null) {
            return dao.readAllByUserId(id, pageNum, amountPerPage);
        } else {
            throw new CustomException("User id is null");
        }
    }

    @Override
    public Integer findIdByUserAndFilmId(Integer userId, Integer filmId) throws
            CustomException {
        WatchedDao dao = daoManager.createAndGetDao(WatchedDao.class);
        if (userId != null && filmId != null) {
            return dao.findIdByUserAndFilmId(userId, filmId);
        } else {
            throw new CustomException("Id is null");
        }
    }

    @Override
    public void updateViewingDate(final Integer userId,
                                  final Integer filmId) throws
            CustomException {
        WatchedDao dao = daoManager.createAndGetDao(WatchedDao.class);
        if (userId != null && filmId != null) {
            daoManager.setAutoCommit(false);
            boolean isCorrect = dao.updateViewingDate(userId, filmId);
            if (isCorrect) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
        } else {
            throw new CustomException("Incorrect arguments");
        }
    }

    @Override
    public Integer create(final Watched entity) throws CustomException {
        WatchedDao dao = daoManager.createAndGetDao(WatchedDao.class);
        if (entity != null) {
            daoManager.setAutoCommit(false);
            Integer id = dao.create(entity);
            if (id != 0) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return id;
        } else {
            throw new CustomException("Watched obj is null");
        }
    }

    @Override
    public Integer countFilms(final int userId) throws CustomException {
        WatchedDao dao = daoManager.createAndGetDao(WatchedDao.class);
        return dao.countFilms(userId);
    }
}
