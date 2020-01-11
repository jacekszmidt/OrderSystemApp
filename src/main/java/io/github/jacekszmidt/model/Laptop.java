package io.github.jacekszmidt.model;

public class Laptop {
    private String model;
    private String serialNumber;
    private String cleanData;

    public Laptop(String model, String serialNumber, String cleanData) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.cleanData = cleanData;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCleanData() {
        return cleanData;
    }

    public void setCleanData(String cleanData) {
        this.cleanData = cleanData;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", cleanData='" + cleanData + '\'' +
                '}';
    }
}