package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.FilmDao;
import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.FilmService;

import java.util.List;

public class FilmServiceImpl extends ServiceImpl implements FilmService {
    @Override
    public List<Film> readAll(final int page, final int amountPerPage)
            throws CustomException {
        FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
        return dao.readAll(page, amountPerPage);
    }

    @Override
    public List<Film> findByName(final String name) throws CustomException {
        if (name != null && !name.isEmpty()) {
            FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
            return dao.findByName(name);
        } else {
            throw new CustomException("Name is incorrect");
        }
    }

    @Override
    public List<Film> findByCategory(final String category)
            throws CustomException {
        if (category != null && !category.isEmpty()) {
            FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
            return dao.findByCategory(category);
        } else {
            throw new CustomException("Category is incorrect");
        }
    }

    @Override
    public Film findById(final Integer id) throws CustomException {
        if (id != null) {
            FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
            return dao.findById(id);
        } else {
            throw new CustomException("ID is null");
        }
    }

    @Override
    public Integer create(final Film film) throws CustomException {
        if (film != null) {
            FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
            daoManager.setAutoCommit(false);
            Integer id = dao.create(film);
            if (id != 0) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return id;
        } else {
            throw new CustomException("Film obj is null");
        }
    }

    @Override
    public boolean update(final Film film) throws CustomException {
        if (film != null) {
            FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
            daoManager.setAutoCommit(false);
            boolean isCorrect = dao.update(film);
            if (isCorrect) {
                daoManager.commit();
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return isCorrect;
        } else {
            throw new CustomException("Film obj is null");
        }
    }

    @Override
    public boolean delete(final Integer id) throws CustomException {
        if (id != null) {
            FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
            boolean isCorrect = false;
            daoManager.setAutoCommit(false);
            if (dao.delete(id)) {
                daoManager.commit();
                isCorrect = true;
            } else {
                daoManager.rollback();
            }
            daoManager.setAutoCommit(true);
            return isCorrect;
        } else {
            throw new CustomException("ID is null");
        }
    }

    @Override
    public Integer countFilms() throws CustomException {
        FilmDao dao = daoManager.createAndGetDao(FilmDao.class);
        return dao.countFilms();
    }
}
