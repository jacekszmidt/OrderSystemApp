package io.github.jacekszmidt;

public class Laptop {
    private String model;
    private String serialNumber;
    private boolean cleanData;

    public Laptop(String name) {
        this.model = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel() {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isCleanData() {
        return cleanData;
    }

    public void setCleanData(boolean cleanData) {
        this.cleanData = cleanData;
    }
}
