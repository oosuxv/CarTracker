package com.company.model;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    Optional<Car> get(long id) throws DaoException;

    List<Car> getAll() throws DaoException;

    void save(Car car) throws DaoException;

    void update(Car car) throws DaoException;

    void delete(long id) throws DaoException;
}
