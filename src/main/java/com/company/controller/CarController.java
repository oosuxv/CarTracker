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

    public void execute(String command) {
        switch (command) {
            case "list":
                list();
                break;
            case "exit":
                System.exit(0);
                break;
        }
        execute(carView.askForInput());
    }

    private void list() {
        try {
            carView.printList(carDao.getAll());
        } catch (DaoException e) {
            carView.printMessage("error");
        }
    }
}
