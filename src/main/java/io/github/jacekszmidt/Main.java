package io.github.jacekszmidt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj imie klienta: ");
        String name = sc.nextLine();
        System.out.print("Podaj nazwisko klienta: ");
        String lastName = sc.nextLine();
        System.out.print("Podaj numer telefonu klienta: ");
        String phoneNumber = sc.nextLine();
        User lap = new User(name, lastName, phoneNumber);
        }
    }


