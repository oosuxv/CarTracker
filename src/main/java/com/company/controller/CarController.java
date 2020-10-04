package com.company.controller;

import com.company.model.Car;
import com.company.model.CarDao;
import com.company.model.DaoException;
import com.company.view.CarView;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Locale;
import java.util.Optional;

public class CarController {
    private final CarDao carDao;
    private final CarView carView;
    private static final Logger logger = LogManager.getLogger(CarController.class);

    public CarController(CarDao carDao, CarView carView) {
        this.carDao = carDao;
        this.carView = carView;
    }

    public void start() {
        carView.showHelp();
        while (execute()) {
        }
    }

    public boolean execute() {
        String command = carView.askForInput();
        try {
            switch (command) {
                case "exit":
                    return false;
                case "list":
                    list();
                    return true;
                case "help":
                    carView.showHelp();
                    return true;
                case "add":
                    add();
                    return true;
                case "details":
                    showDetails();
                    return true;
                case "remove":
                    delete();
                    return true;
                case "update":
                    update();
                    return true;
                case "locale":
                    changeLocale();
                    return true;
                default:
                    carView.showWrongCommand();
                    return true;
            }
        } catch (DaoException e) {
            logger.error("data access error on command " + command, e);
            carView.showError();
            return true;
        } catch (Exception e) {
            logger.error("something went in totally wrong direction, but this program is supposed to be resilient", e);
            carView.showError();
            return true;
        }
    }

    private int getIntFromView(String field) {
        boolean parseFailed = true;
        int result = 0;
        do {
            try {
                result = Integer.parseInt(carView.askForField(field, "int"));
                parseFailed = false;
            } catch (NumberFormatException e) {
                logger.error("Expected number parsing failure");
                carView.showWrongId();
            }
        } while (parseFailed);
        return result;
    }

    private void setCarFields(Car car) {
        String input = carView.askForField("maker", "string");
        if (input.length() > 0) {
            car.setMaker(input);
        }
        input = carView.askForField("model", "string");
        if (input.length() > 0) {
            car.setModel(input);
        }
        input = carView.askForField("bodyStyle", "string");
        if (input.length() > 0) {
            car.setBodyStyle(input);
        }
        input = carView.askForField("year", "int");
        if (input.length() > 0) {
            try {
                car.setYear(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                logger.error("Expected number parsing failure");
                car.setYear(getIntFromView("year"));
            }
        }
    }

    private void list() throws DaoException {
        carView.showList(carDao.getAll());
    }

    private void showDetails() throws DaoException {
        int id = getIntFromView("id");
        Optional<Car> car = carDao.get(id);
        if (!car.isPresent()) {
            carView.showNotFound();
            return;
        }
        carView.showDetails(car.get());
    }

    private void delete() throws DaoException {
        int id = getIntFromView("id");
        Optional<Car> car = carDao.get(id);
        if (!car.isPresent()) {
            carView.showNotFound();
            return;
        }
        carDao.delete(car.get());
        carView.showSuccess();
    }


    private void update() throws DaoException {
        int id = getIntFromView("id");
        Optional<Car> optionalCar = carDao.get(id);
        if (!optionalCar.isPresent()) {
            carView.showNotFound();
            return;
        }
        Car car = optionalCar.get();
        setCarFields(car);
        carDao.update(car);
        carView.showSuccess();
    }

    private void add() throws DaoException {
        Car car = new Car();
        setCarFields(car);
        carDao.save(car);
        carView.showSuccess();
    }

    private void changeLocale() {
        carView.setLocale(new Locale("ru", "RU"));
    }
}

