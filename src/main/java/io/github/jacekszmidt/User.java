package io.github.jacekszmidt;

import java.util.Scanner;

public class User {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String confirmData;
    private boolean isNotValid = false;
    private static final String PHONE_REGEX = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)";

    public User(String name, String lastName, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

        this.name = setName();
        this.lastName = setLastName();
        this.phoneNumber = setPhoneNumber();
        confirmData(confirmData);
    }

    private String setName() {
        System.out.print("Wprowadzono imie klienta: " + name);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private String setLastName() {
        System.out.print("Wprowadzono nazwisko klienta: " + lastName);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private String setPhoneNumber() {
        System.out.println("Wprowadzono numer klienta - 9 cyfr: " + phoneNumber);
        return phoneNumber;
    }

    private boolean isNotValid(String phoneNumber) {
        while (isNotValid(PHONE_REGEX)){
            return false;
        }
    }

    public void confirmData(String confirmData) {
        System.out.println("Czy wszystkie dane sie zgadzaja? Y/N?");
        Scanner sc = new Scanner(System.in);
        String checkData = sc.nextLine();
        if (checkData.equals("Y") || checkData.equals("y")) {
            System.out.println("Dane zatwierdzone!");
        } else if (checkData.equals("N") || checkData.equals("n")){
            System.out.println("Wprowadzone dane nie zostaly zatwierdzone");
        }
    }
}


