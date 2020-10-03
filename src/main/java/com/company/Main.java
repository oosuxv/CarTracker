package com.company;

import com.company.controller.CarController;
import com.company.model.DaoException;
import com.company.model.JpaCarDao;
import com.company.view.CarView;

public class Main {

    public static void main(String[] args)  {
        CarView view = null;
        try (JpaCarDao jcd = new JpaCarDao("jdbc:h2:./cars");) {
            view = new CarView();
            CarController controller = new CarController(jcd, view);
            view.showHelp();
            while (controller.execute()) {};
        } catch (DaoException e) {
            if (view != null) {
                view.showError(true);
            }
        }
    }
}
