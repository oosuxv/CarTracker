package com.company.view;

import com.company.model.Car;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CarConsoleView implements CarView {
    private static final String commands = "list, add, details, remove, update, help, exit";

    private Locale currentLocale = Locale.getDefault();
    private ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    private final Scanner scanner;

    public CarConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void setLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    @Override
    public String askForInput() {
        System.out.printf("%s: ", messages.getString("prompt"));
        return scanner.nextLine();
    }

    @Override
    public String askForField(String field, String type) {
        System.out.printf("%s %s(%s): "
                , messages.getString("fieldPrompt")
                , messages.getString(field)
                , messages.getString(type)
        );
        return scanner.nextLine();
    }

    @Override
    public void showList(List<Car> list) {
        String listLayout = "%5s |%15s |%15s\n";
        System.out.println(messages.getString("list"));
        System.out.printf(listLayout
                , messages.getString("id")
                , messages.getString("maker")
                , messages.getString("model")
        );
        for (Car car : list) {
            System.out.printf(listLayout
                    , car.getCarId()
                    , car.getMaker()
                    , car.getModel()
            );
        }
    }

    @Override
    public void showDetails(Car car) {
        String detailsLayout = "%s: %s\n";
        System.out.println(messages.getString("details"));
        System.out.printf(detailsLayout, messages.getString("id"), car.getCarId());
        System.out.printf(detailsLayout, messages.getString("maker"), car.getMaker());
        System.out.printf(detailsLayout, messages.getString("model"), car.getModel());
        System.out.printf(detailsLayout, messages.getString("bodyStyle"), car.getBodyStyle());
        System.out.printf(detailsLayout, messages.getString("year"), car.getYear());
    }

    @Override
    public void showHelp() {
        System.out.printf("%s: %s.\n"
                , messages.getString("help")
                , commands);
    }

    @Override
    public void showError() {
        System.out.println(messages.getString("error"));
    }

    @Override
    public void showWrongCommand() {
        System.out.println(messages.getString("wrongCommand"));
        showHelp();
    }

    @Override
    public void showWrongId() {
        System.out.println(messages.getString("wrongId"));
    }

    @Override
    public void showNotFound() {
        System.out.println(messages.getString("carNotFound"));
    }

    @Override
    public void showSuccess() {
        System.out.println(messages.getString("success"));
    }

}
