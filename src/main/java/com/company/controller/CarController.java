package com.company.controller;

import com.company.model.Car;
import com.company.model.CarDao;
import com.company.model.DaoException;
import com.company.view.CarView;

import java.util.List;

public class CarController {
    private CarDao carDao;
    private CarView carView;

    public CarController(CarDao carDao, CarView carView) {
        this.carDao = carDao;
        this.carView = carView;
    }

    public boolean execute(String command) {
        if (command.equals("list")) {
            list();
            return true;
        }
        if (command.equals("add")) {
            parseAdd(command);
            return true;
        }
        if (command.startsWith("remove")) {
            parseAdd(command);
            return true;
        }
        if (command.startsWith("update")) {
            parseAdd(command);
            return true;
        }


        return false;
    }

    private void parseAdd(String command) {
    }

    private void list() {
        try {
            List<Car> carList = carDao.getAll();
            carView.printMessage("list");
            for (Car car : carList) {
                carView.printMessage("car", car.getMaker(), car.getModel(), car.getBodyStyle(), String.valueOf(car.getYear()));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
