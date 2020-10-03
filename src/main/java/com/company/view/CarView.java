package com.company.view;

import com.company.model.Car;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CarView {
    private Locale currentLocale = Locale.getDefault();
    private ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

    public Locale getLocale() {
        return currentLocale;
    }

    public void setLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public String askForInput(String messageKey) {
        System.out.println(messages.getString(messageKey));
        return askForInput();
    }

    public String askForInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    public void printMessage(String messageKey, String... values ) {
        System.out.printf(messages.getString(messageKey), values);
        System.out.println();
    }

    public void printList(List<Car> list) {
        System.out.println(messages.getString("list"));
        for (Car car : list) {
            System.out.printf(messages.getString("car"),
                    car.getMaker(), car.getModel(), car.getBodyStyle(), car.getYear());
            System.out.println();
        }
    }
    
    public void printHelp() {
        System.out.println(messages.getString("help"));
    }

    public void printError() {
        System.out.println(messages.getString("error"));
    }

    public void printUnknownCommand() {
        System.out.println(messages.getString("unknownCommand"));
        System.out.println(messages.getString("help"));
    }
}
