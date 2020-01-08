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

        this.cpu = setCpu();
        this.motherBoard = setMotherBoard();
        this.memoryRam = setMemoryRam();
        this.psu = setPsu();
        this.disc = setDisc();
        this.cleanData = askCleanData();
    }

    public String getCpu() {
        return cpu;
    }

    private String setCpu() {
        System.out.print("Wprowadzono model cpu: ");
        return this.cpu;
    }

    private String setMotherBoard() {
        System.out.print("Wprowadzono model plyty glownej: " + this.motherBoard);
        return null;
    }

    private String setMemoryRam() {
        System.out.print("Wprowadzono model pamieci Ram: " + this.memoryRam);
        return null;
    }

    private String setPsu() {
        System.out.println("Wprowadzono model zasilacza: " + this.psu);
        return null;
    }

    private String setDisc() {
        System.out.println("Wprowadzono model dysku: " + this.disc);
        return null;
    }

    public String askCleanData() {
        System.out.println("Mozna usuwac dane? " + this.cleanData);
        return null;
    }
}


