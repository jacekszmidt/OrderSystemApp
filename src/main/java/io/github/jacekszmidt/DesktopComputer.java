package io.github.jacekszmidt;

public class DesktopComputer {
    private String cpu;
    private String motherBoard;
    private String memoryRam;
    private String psu;
    private String disc;
    private boolean cleanData;

    public DesktopComputer(String cpu, String motherBoard, String memoryRam, String psu, String disc, boolean cleanData) {
        this.cpu = cpu;
        this.motherBoard = motherBoard;
        this.memoryRam = memoryRam;
        this.psu = psu;
        this.disc = disc;
        this.cleanData = cleanData;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(String motherBoard) {
        this.motherBoard = motherBoard;
    }

    public String getMemoryRam() {
        return memoryRam;
    }

    public void setMemoryRam(String memoryRam) {
        this.memoryRam = memoryRam;
    }

    public String getPsu() {
        return psu;
    }

    public void setPsu(String psu) {
        this.psu = psu;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public boolean isCleanData() {
        return cleanData;
    }

    public void setCleanData(boolean cleanData) {
        this.cleanData = cleanData;
    }
}

