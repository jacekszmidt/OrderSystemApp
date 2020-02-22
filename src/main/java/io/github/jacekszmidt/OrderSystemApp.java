package io.github.jacekszmidt;

import io.github.jacekszmidt.service.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class OrderSystemApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSystemApp.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ComputerService COMPUTER_SERVICE = new ComputerService();
    private static final UserService USER_SERVICE = new UserService();
    private static final LaptopService LAPTOP_SERVICE = new LaptopService();
    private static final ComputerOutputWriter COMPUTER_OUTPUT_WRITER = new ExcelComputerOutputWriter();

    private OrderSystemApp() {
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            int userChoice = getUserChoice();
            switch (userChoice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    USER_SERVICE.addUser();
                    break;
//                case 2:
//                    LAPTOP_SERVICE.addNewLaptop();
//                    break;
//                case 3:
//                    COMPUTER_SERVICE.addNewComputer();
//                    break;
                case 4:
                    USER_SERVICE.showUsers();
                    break;
//                case 5:
//                    LAPTOP_SERVICE.showLaptops();
//                    break;
//                case 6:
//                    COMPUTER_SERVICE.showComputers();
//                    break;
                case 7:
                    COMPUTER_OUTPUT_WRITER.writeOutput(USER_SERVICE.getUser(), LAPTOP_SERVICE.getLaptop());
                    break;
                case 8:
                    COMPUTER_OUTPUT_WRITER.writeOutput(USER_SERVICE.getUser(), COMPUTER_SERVICE.getPersonalComputer());
                    break;

            }
        }

    }

    public static void main(String[] args) {
        new OrderSystemApp();
    }

    private int getUserChoice() {
        while (true) {
            String choice = SCANNER.nextLine();
            if (!NumberUtils.isParsable(choice) || Integer.parseInt(choice) > 8 || Integer.parseInt(choice) < 0) {
                LOGGER.info("Choose correct number");
                continue;
            }
            return Integer.parseInt(choice);
        }
    }

    private void printMainMenu() {
        String mainMenu = "\n0: exit" + System.lineSeparator() +
                "1: add user" + System.lineSeparator() +
//                "2: add laptop" + System.lineSeparator() +
//                "3: add computer" + System.lineSeparator() +
                "4: show users" + System.lineSeparator() +
                "5: show laptops" + System.lineSeparator() +
                "6: show computers" + System.lineSeparator() +
                "7: assign laptop to the user" + System.lineSeparator() +
                "8: assign PC to the user" + System.lineSeparator();

        LOGGER.info(mainMenu);
    }

}