package io.github.jacekszmidt;

public class Laptop extends PersonalComputer {
    private String model;
    private String serialNumber;

    public Laptop(String cpu, String motherBoard, String memoryRam, String psu, String disc, boolean cleanData, String model, String serialNumber) {
        super(cpu, motherBoard, memoryRam, psu, disc, cleanData);
        this.model = model;
        this.serialNumber = serialNumber;
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
}
