package com.company.controller;

import com.company.model.Car;
import com.company.model.CarDao;
import com.company.model.DaoException;
import com.company.view.CarView;

import java.util.Optional;

public class CarController {
    private CarDao carDao;
    private CarView carView;

    public CarController(CarDao carDao, CarView carView) {
        this.carDao = carDao;
        this.carView = carView;
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
        } else if (command.startsWith("details") && commandParts.length == 2) {
            details(commandParts[1]);
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
            carView.showError(false);
        }
    }

    private void details(String idString) {
        try {
            long id = Long.parseLong(idString);
            Optional<Car> car = carDao.get(id);
            if (car.isPresent()) {
                carView.showDetails(car.get());
            } else {
                carView.showWrongId();
            }
        } catch (DaoException e) {
            carView.showError(false);
        } catch (NumberFormatException e) {
            carView.showWrongCommand();
        }
    }
}

