package io.github.jacekszmidt.service;

import io.github.jacekszmidt.model.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LaptopService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LaptopService.class);
    private static final List<Laptop> LAPTOPS = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    private Laptop getLaptop() {
        LOGGER.info("Podaj model laptopa: "); // todo add here laptop validation
        String model = SCANNER.nextLine();
        LOGGER.info("Podaj numer seryjny laptopa: ");
        String serialNumber = SCANNER.nextLine();
        LOGGER.info("Czy mozna usuwac dane? ");
        String cleanData = SCANNER.nextLine();
        return new Laptop(model, serialNumber, cleanData);
    }

    public void addNewLaptop() {
        Laptop personalComputer = getLaptop();
        LAPTOPS.add(personalComputer);
        LOGGER.info("Laptop was added: {}", personalComputer);
    }

    public void showLaptops() {
        if (LAPTOPS.isEmpty()) {
            LOGGER.info("There are no laptops");
            return;
        }
        LOGGER.info("List of all laptops:");
        for (Laptop personalComputer : LAPTOPS) {
            LOGGER.info("{}: {}", LAPTOPS.indexOf(personalComputer), personalComputer);
        }
    }
}
