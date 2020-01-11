package io.github.jacekszmidt;

public class PersonalComputer {
    private String cpu;
    private String motherBoard;
    private String memoryRam;
    private String psu;
    private String disc;
    private String cleanData;

    public PersonalComputer(String cpu, String motherBoard, String memoryRam, String psu, String disc, String cleanData) {
        this.cpu = cpu;
        this.motherBoard = motherBoard;
        this.memoryRam = memoryRam;
        this.psu = psu;
        this.disc = disc;
        this.cleanData = cleanData;
    }

    private void setCpu() {
        System.out.print("Wprowadzono model cpu: ");
    }

    private void setMotherBoard() {
        System.out.print("Wprowadzono model plyty glownej: " + this.motherBoard);
    }

    private void setMemoryRam() {
        System.out.print("Wprowadzono model pamieci Ram: " + this.memoryRam);
    }

    private void setPsu() {
        System.out.println("Wprowadzono model zasilacza: " + this.psu);
    }

    private void setDisc() {
        System.out.println("Wprowadzono model dysku: " + this.disc);
    }
}



