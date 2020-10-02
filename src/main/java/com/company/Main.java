package com.company;

import com.company.controller.CarController;
import com.company.model.Car;
import com.company.model.DaoException;
import com.company.model.JpaCarDao;
import com.company.view.CarView;

import java.util.Optional;

public class Main {

    public static void main(String[] args)  {
        try (JpaCarDao jcd = new JpaCarDao("jdbc:h2:./cars");) {
            CarView view = new CarView();
            CarController controller = new CarController(jcd, view);
            controller.execute("list");
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
