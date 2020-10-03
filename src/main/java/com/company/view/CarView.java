package com.company.view;

import com.company.model.Car;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CarView {
    private Locale currentLocale = Locale.getDefault();
    private ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    private final Scanner scanner;

    public CarView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Locale getLocale() {
        return currentLocale;
    }

    public void setLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public String askForInput() {
        System.out.print(messages.getString("prompt"));
        return scanner.nextLine();
    }

    public String askForField(String field, String type) {
        System.out.printf("%s %s(%s): "
                , messages.getString("fieldPrompt")
                , messages.getString(field)
                , messages.getString(type)
        );
        return scanner.nextLine();
    }

    public void showList(List<Car> list) {
        String layout = "%5s|%15s|%15s\n";
        System.out.println(messages.getString("list"));
        System.out.printf(layout
                , messages.getString("id")
                , messages.getString("maker")
                , messages.getString("model")
        );
        for (Car car : list) {
            System.out.printf(layout
                    , car.getCarId()
                    , car.getMaker()
                    , car.getModel()
            );
        }
    }

    public void showDetails(Car car) {
        System.out.println(messages.getString("details"));
        System.out.printf("%s: %d\n", messages.getString("id"), car.getCarId());
        System.out.printf("%s: %s\n", messages.getString("maker"), car.getMaker());
        System.out.printf("%s: %s\n", messages.getString("model"), car.getModel());
        System.out.printf("%s: %s\n", messages.getString("bodyStyle"), car.getBodyStyle());
        System.out.printf("%s: %s\n", messages.getString("year"), car.getYear());
    }

    public void showHelp() {
        System.out.println(messages.getString("help"));
    }

    public void showError() {
        System.out.println(messages.getString("error"));
    }

    public void showWrongCommand() {
        System.out.println(messages.getString("wrongCommand"));
        System.out.println(messages.getString("help"));
    }

    public void showWrongId() {
        System.out.println(messages.getString("wrongId"));
    }



}
