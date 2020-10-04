package com.company;

import com.company.controller.CarController;
import com.company.model.DaoException;
import com.company.model.JpaCarDao;
import com.company.view.CarConsoleView;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
    private static final Logger logger = LogManager.getLogger(CarController.class);

    public static void main(String[] args) {

        Properties appProps = new Properties();
        try {
            appProps.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            logger.error("properties not found", e);
            System.out.println("properties are missing. please contact support.");
            return;
        }

        CarConsoleView view = null;
        try (Scanner scanner = new Scanner(System.in);
             JpaCarDao jcd = new JpaCarDao(appProps.getProperty("jdbcurl"))) {
            view = new CarConsoleView(scanner);
            CarController controller = new CarController(jcd, view);
            controller.start();
        } catch (DaoException e) {
            logger.error("db connection issues", e);
            if (view != null) {
                view.showError();
            } else {
                System.out.println("something went wrong with db. please contact support.");
            }
        } catch (Exception e) {
            logger.error("unknown exception at top level", e);
            System.out.println("something went really wrong. please contact support.");
        }
    }
}
