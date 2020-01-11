package io.github.jacekszmidt;

import java.util.Scanner;

public class User {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String confirmData;

    public User(String name, String lastName, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    void setName() {
        System.out.println("Wprowadzono imie klienta: " + this.name);
    }

    void setLastName() {
        System.out.println("Wprowadzono nazwisko klienta: " + this.lastName);
    }

    void setPhoneNumber() {
        System.out.println("Wprowadzono numer kontaktowy klienta: " + this.phoneNumber);
    }


    public void confirmData() {
        System.out.print("Czy wszystkie dane sie zgadzaja? Y/N? ");
        Scanner sc = new Scanner(System.in);
        String checkData = sc.nextLine();
        if (checkData.equals("Y") || checkData.equals("y")) {
            System.out.println("Dane zatwierdzone!");
        } else if (checkData.equals("N") || checkData.equals("n")){
            System.out.println("Wprowadzone dane nie zostaly zatwierdzone");
        }
    }
}

