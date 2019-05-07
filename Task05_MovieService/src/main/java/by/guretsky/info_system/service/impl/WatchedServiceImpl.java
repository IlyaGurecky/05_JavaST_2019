package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.WatchedDao;
import by.guretsky.info_system.entity.Watched;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.WatchedService;

import java.util.List;

public class WatchedServiceImpl extends ServiceImpl implements WatchedService {
    @Override
    public List<Watched> readAllByUserId(Integer id) throws
            CustomException {
        WatchedDao dao = daoManager.createAndGetDao(WatchedDao.class);
        if (id != null) {
            return dao.readAllByUserId(id);
        } else {
            throw new CustomException("User id is null");
        }
    }

    @Override
    public boolean deleteByUserAndFilmId(Integer userId, Integer filmId) throws
            CustomException {
        WatchedDao dao = daoManager.createAndGetDao(WatchedDao.class);
        if (userId != null && filmId != null) {
            daoManager.setAutoCommit(false);
            boolean isCorrect = dao.deleteByUserAndFilmId(userId, filmId);
            if (isCorrect) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return isCorrect;
        } else {
            throw new CustomException("Incorrect arguments");
        }
    }

    @Override
    public Integer create(Watched entity) throws CustomException {
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
}