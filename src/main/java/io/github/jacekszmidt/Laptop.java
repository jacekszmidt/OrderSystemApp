package io.github.jacekszmidt;

public class Laptop {
    private String model;
    private String serialNumber;
    private String cleanData;

    public Laptop(String model, String serialNumber, String cleanData) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.cleanData = cleanData;

        this.model = setModel();
        this.serialNumber = setSerialNumber();
        this.cleanData = askCleanData();
    }

    private String setModel() {
        System.out.println("Wprowadzono model laptopa: " + this.model);
        return null;
    }

    private String setSerialNumber() {
        System.out.println("Wprowadzono numer seryjny laptopa: " + this.serialNumber);
        return null;
    }

    public String askCleanData() {
        System.out.println("Mozna usuwac dane? " + this.cleanData);
        return null;
    }
}
