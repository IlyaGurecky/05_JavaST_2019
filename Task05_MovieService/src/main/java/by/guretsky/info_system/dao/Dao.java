package by.guretsky.info_system.dao;

import by.guretsky.info_system.entity.Entity;

public interface Dao<T extends Entity> {
    T findById(Integer id);

    boolean delete(Integer id);

    Integer create(T entity);

    boolean update(T entity);
}
