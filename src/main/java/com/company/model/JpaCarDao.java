package com.company.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JpaCarDao implements CarDao, AutoCloseable {
    private JdbcPooledConnectionSource connectionSource;
    private Dao<Car, Integer> carDao;

    public JpaCarDao(String jdbcUrl) throws DaoException {
        try {
            connectionSource = new JdbcPooledConnectionSource(jdbcUrl);
            carDao = DaoManager.createDao(connectionSource, Car.class);
            if (!carDao.isTableExists()) {
                TableUtils.createTable(carDao);
                carDao.create(new Car(2005, "Honda", "Civic", "Sedan"));
                carDao.create(new Car(2014, "Porsche", "918 Spyder", "Roadster"));
                carDao.create(new Car(2008, "Toyota", "Tundra", "Pickup"));
            }
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Car> get(int id) throws DaoException {
        try {
            return Optional.ofNullable(carDao.queryForId(id));
        }  catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<Car> getAll() throws DaoException {
        try {
            return carDao.queryForAll();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void save(Car car) throws DaoException {
        try {
            carDao.create(car);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void update(Car car) throws DaoException {
        try {
            carDao.update(car);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void delete(int id) throws DaoException {
        try {
            carDao.deleteById(id);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connectionSource.close();
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
