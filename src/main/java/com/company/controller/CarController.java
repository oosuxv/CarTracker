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

    public boolean execute() {
        String command = carView.askForInput();
        switch (command) {
            case "exit":
                System.exit(0);
                return false;
            case "list":
                list();
                return true;
            default:
                return true;
        }
    }

    private void list() {
        try {
            carView.printList(carDao.getAll());
        } catch (DaoException e) {
            carView.printMessage("error");
        }
    }
}
