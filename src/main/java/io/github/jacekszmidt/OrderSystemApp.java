package io.github.jacekszmidt;

import io.github.jacekszmidt.service.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OrderSystemApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSystemApp.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ComputerService COMPUTER_SERVICE = new ComputerService();
    private static final UserService USER_SERVICE = new UserService();
    private static final LaptopService LAPTOP_SERVICE = new LaptopService();
    private static final ComputerOutputWriter COMPUTER_OUTPUT_WRITER = new ExcelComputerOutputWriter();
    private static final Queue<Pair<String, byte[]>> EXCEL_FILE_NAME_WITH_CONTENT_QUEUE = new LinkedList<>();

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
                    Pair<String, byte[]> laptopExcelFile = COMPUTER_OUTPUT_WRITER.writeOutput(USER_SERVICE.getUser(), LAPTOP_SERVICE.getLaptop());
                    if (laptopExcelFile != null) {
                        EXCEL_FILE_NAME_WITH_CONTENT_QUEUE.add(laptopExcelFile);
                    }
                    break;
                case 8:
                    Pair<String, byte[]> pcExcelFile = COMPUTER_OUTPUT_WRITER.writeOutput(USER_SERVICE.getUser(), COMPUTER_SERVICE.getPersonalComputer());
                    if (pcExcelFile != null) {
                        EXCEL_FILE_NAME_WITH_CONTENT_QUEUE.add(pcExcelFile);
                    }
                    break;
                case 9:
                    if (EXCEL_FILE_NAME_WITH_CONTENT_QUEUE.isEmpty()) {
                        LOGGER.warn("Excel file queue is empty");
                    } else {
                        Pair<String, byte[]> excelFileNameWithContent = EXCEL_FILE_NAME_WITH_CONTENT_QUEUE.poll();
                        try (FileOutputStream file = new FileOutputStream(excelFileNameWithContent.getKey())) {
                            file.write(excelFileNameWithContent.getValue());
                            LOGGER.info("Saved file: {}", excelFileNameWithContent.getKey());
                        } catch (IOException e) {
                            LOGGER.error("Error during saving file: {}", e.getMessage());
                        }
                    }
            }
        }

    }

    public static void main(String[] args) {
        new OrderSystemApp();
    }

    private int getUserChoice() {
        while (true) {
            String choice = SCANNER.nextLine();
            if (!NumberUtils.isParsable(choice) || Integer.parseInt(choice) > 9 || Integer.parseInt(choice) < 0) {
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
                "8: assign PC to the user" + System.lineSeparator() +
                "9: save user report excel file" + System.lineSeparator();

        LOGGER.info(mainMenu);
    }

}