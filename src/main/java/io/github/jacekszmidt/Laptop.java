package io.github.jacekszmidt;

public class Laptop {
    private String model;
    private String serialNumber;

    public Laptop(String model, String serialNumber) {
        this.model = model;
        this.serialNumber = serialNumber;

        setModel();
        setSerialNumber();
        ComputerService.askCleanData();
    }

    private void setModel() {
        System.out.println("Wprowadzono model laptopa: " + this.model);
    }

    private void setSerialNumber() {
        System.out.println("Wprowadzono numer seryjny laptopa: " + this.serialNumber);
    }
}