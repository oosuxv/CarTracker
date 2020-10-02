package com.company;

import com.company.model.Car;
import com.company.model.DaoException;
import com.company.model.JpaCarDao;

import java.util.Optional;

public class Main {

    public static void main(String[] args)  {
        try (JpaCarDao jcd = new JpaCarDao("jdbc:h2:./cars");) {
            Optional<Car> car = jcd.get(3);
            if (car.isPresent()) {
                System.out.println(car.get());
            }
            car = jcd.get(5);
            if (car.isPresent()) {
                System.out.println(car.get());
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
