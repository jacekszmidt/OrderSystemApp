package io.github.jacekszmidt;

import java.util.Scanner;

public class UserService {

    public static void computerChoice(){
        System.out.println("Jakiego rodzaju komputer przyjmujesz? Laptop czy komputer stacjonarny?");
        Scanner sc = new Scanner(System.in);
        String devChoice = sc.nextLine();
        if(devChoice.equals("laptop") || devChoice.equals("lap")
                                      || devChoice.equals("Lap") || devChoice.equals("Laptop")){
            System.out.print("Podaj model laptopa: ");
            String model = sc.nextLine();
            System.out.print("Podaj numer seryjny laptopa: ");
            String serialNumber = sc.nextLine();
            System.out.print("Czy mozna usuwac dane? ");
            String cleanData = sc.nextLine();
        } else if (devChoice.equals("Komputer stacjonarny") || devChoice.equals("komputer stacjonarny")
                                                            || devChoice.equals("Komputer Stacjonarny")
                                                            || devChoice.equals("ks")
                                                            || devChoice.equals("komp stacjonarny")){
            System.out.print("Podaj model procesora: ");
            String cpu = sc.nextLine();
            System.out.print("Podaj model plyty glownej: ");
            String motherBoard = sc.nextLine();
            System.out.print("Podaj model pamieci RAM: ");
            String memoryRam = sc.nextLine();
            System.out.print("Podaj model zasilacza: ");
            String psu = sc.nextLine();
            System.out.print("Podaj model dysku: ");
            String disc = sc.nextLine();
            System.out.print("Czy mozna usuwac dane? ");
            String cleanData = sc.nextLine();
        }else{
            System.out.println("Jesli chcesz przyjac laptopa masz mozliwosc wpisania nastepujacych fraz: \nlaptop \nlap \nLap \nLaptop" +
                    "\nas\nJesli chcesz przyjac komputer stacjonarny masz mozliwosc wpisania nastepujacych fraz: \nKomputer stacjonarny \nkomputer stacjonarny \nKomputer Stacjonarny \nks \nkomp stacjonarny");
        }
    }
}
