package com.company.view;

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
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    public void printMessage(String messageKey, String... values ) {
        System.out.printf(messages.getString(messageKey), values);
        System.out.println();
    }
}
