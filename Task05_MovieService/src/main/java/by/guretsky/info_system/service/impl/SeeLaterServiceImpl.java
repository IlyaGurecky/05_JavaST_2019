package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.SeeLaterDao;
import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.SeeLaterService;

import java.util.List;

public class SeeLaterServiceImpl extends ServiceImpl implements SeeLaterService {
    @Override
    public List<SeeLater> readAllByUserId(Integer id) throws CustomException {
        SeeLaterDao dao = daoManager.createAndGetDao(SeeLaterDao.class);
        if (id != null) {
            return dao.readAllByUserId(id);
        } else {
            throw new CustomException("User id is null");
        }
    }

    @Override
    public boolean deleteByUserAndFilmId(Integer userId, Integer filmId) throws
            CustomException {
        SeeLaterDao dao = daoManager.createAndGetDao(SeeLaterDao.class);
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
    public Integer create(SeeLater entity) throws CustomException {
        SeeLaterDao dao = daoManager.createAndGetDao(SeeLaterDao.class);
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
            throw new CustomException("SeeLater obj is null");
        }
    }
}
