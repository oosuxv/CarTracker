package com.company.controller;

import com.company.model.Car;
import com.company.model.CarDao;
import com.company.model.DaoException;
import com.company.view.CarConsoleView;

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
        while (execute()) {}
    }

    public boolean execute() {
        String command = carView.askForInput();
        String[] commandParts = command.split(" ");
        if (command.equals("exit")) {
            return false;
        } else if (command.equals("list")) {
            list();
            return true;
        } else if (command.equals("help")) {
            carView.showHelp();
            return true;
        } else if (command.equals("add")) {
            add();
            return true;
        } else if (command.startsWith("details") && commandParts.length == 2) {
            showDetails(commandParts[1]);
            return true;
        } else if (command.startsWith("remove") && commandParts.length == 2) {
            delete(commandParts[1]);
            return true;
        } else if (command.startsWith("update") && commandParts.length == 2) {
            update(commandParts[1]);
            return true;
        } else if (command.startsWith("locale") && commandParts.length == 3) {
            changeLocale(commandParts[1], commandParts[2]);
            return true;
        } else {
            carView.showWrongCommand();
            return true;
        }
    }

    private void list() {
        try {
            carView.showList(carDao.getAll());
        } catch (DaoException e) {
            logger.error("Error while showing list", e);
            carView.showError();
        }
    }

    private void showDetails(String idString) {
        try {
            int id = Integer.parseInt(idString);
            Optional<Car> car = carDao.get(id);
            if (car.isPresent()) {
                carView.showDetails(car.get());
            } else {
                carView.showWrongId();
            }
        } catch (DaoException e) {
            logger.error("Error while showing details", e);
            carView.showError();
        } catch (NumberFormatException e) {
            logger.error("Expected number parsing failure");
            carView.showWrongCommand();
        }
    }

    private void delete(String idString) {
        try {
            int id = Integer.parseInt(idString);
            Optional<Car> optionalCar = carDao.get(id);
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                carDao.delete(car);
                carView.showSuccess();
            } else {
                carView.showWrongId();
            }
        } catch (DaoException e) {
            logger.error("Error while deleting", e);
            carView.showError();
        } catch (NumberFormatException e) {
            logger.error("Expected number parsing failure");
            carView.showWrongCommand();
        }
    }

    private void update(String idString) {
        try {
            int id = Integer.parseInt(idString);
            Optional<Car> optionalCar = carDao.get(id);
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                setCarFields(car);
                carDao.update(car);
                carView.showSuccess();
            } else {
                carView.showWrongId();
            }
        } catch (DaoException e) {
            logger.error("Error while updating", e);
            carView.showError();
        } catch (NumberFormatException e) {
            logger.error("Expected number parsing failure");
            carView.showWrongCommand();
        }
    }

    private void add() {
        Car car = new Car();
        setCarFields(car);
        try {
            carDao.save(car);
            carView.showSuccess();
        } catch (DaoException e) {
            logger.error("Error while adding", e);
            carView.showError();
        }
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
        boolean parseFailed = true;
        input = carView.askForField("year", "int");
        if (input.length() > 0) {
            do {
                try {
                    car.setYear(Integer.parseInt(carView.askForField("year", "int")));
                    parseFailed = false;
                } catch (NumberFormatException e) {
                    logger.error("Expected number parsing failure");
                }
            } while (parseFailed);
        }
    }

    private void changeLocale(String language, String country) {
        Locale locale = new Locale(language, country);
        carView.setLocale(locale);
    }
}

