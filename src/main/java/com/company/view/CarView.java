package com.company.view;

import com.company.model.Car;

import java.util.List;
import java.util.Locale;

public interface CarView {
    void setLocale(Locale currentLocale);

    String askForInput();

    String askForField(String field, String type);

    void showList(List<Car> list);

    void showDetails(Car car);

    void showHelp();

    void showError();

    void showWrongCommand();

    void showWrongId();

    void showNotFound();

    void showSuccess();
}
