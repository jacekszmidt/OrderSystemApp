package io.github.jacekszmidt.service;

import io.github.jacekszmidt.model.Laptop;
import io.github.jacekszmidt.model.PersonalComputer;
import io.github.jacekszmidt.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelComputerOutputWriter implements ComputerOutputWriter {
    private static final String EXCEL_EXTENSION = ".xlsx";
    private static final String LAP_ENDING = "LAP#";
    private static final String PC_ENDING = "PC#";
    private final List<String> userHeaders = List.of("Imie", "Nazwisko", "Telefon");
    private final List<String> laptopHeaders = List.of("Model", "Numer seryjny", "Inne", "Opis usterki", "Mozna kasowac dane?");
    private final List<String> personalComputerHeaders = List.of("CPU", "Plyta Glowna", "RAM", "Zasilacz", "Dysk", "Inne", "Opis usterki", "Mozna kasowac dane?");

    @Override
    public void writeOutput(User user, Laptop laptop) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order Details");

        Row userHeader = sheet.createRow(0);
        for (int i = 0; i < userHeaders.size(); i++) {
            Cell cell = userHeader.createCell(i);
            cell.setCellValue(userHeaders.get(i));
        }

        Row userRow = sheet.createRow(1);
        Cell nameCell = userRow.createCell(0);
        nameCell.setCellValue(user.getName());
        Cell lastNameCell = userRow.createCell(1);
        lastNameCell.setCellValue(user.getLastName());
        Cell phoneNumberCell = userRow.createCell(2);
        phoneNumberCell.setCellValue(user.getPhoneNumber());


        Row laptopHeader = sheet.createRow(3);
        for (int i = 0; i < laptopHeaders.size(); i++) {
            Cell cell = laptopHeader.createCell(i);
            cell.setCellValue(laptopHeaders.get(i));
        }

        Row laptopRow = sheet.createRow(4);
        Cell modelCell = laptopRow.createCell(0);
        modelCell.setCellValue(laptop.getModel());
        Cell serialNumberCell = laptopRow.createCell(1);
        serialNumberCell.setCellValue(laptop.getSerialNumber());
        Cell otherInfoCell = laptopRow.createCell(2);
        otherInfoCell.setCellValue(laptop.getOtherInfo());
        Cell problemDescriptionCell = laptopRow.createCell(3);
        problemDescriptionCell.setCellValue(laptop.getProblemDescription());
        Cell cleanDataCell = laptopRow.createCell(4);
        cleanDataCell.setCellValue(laptop.getCleanData());

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        try {
            FileOutputStream file = new FileOutputStream(LAP_ENDING + user.getLastName() + "_" + (System.currentTimeMillis() / 1000) + EXCEL_EXTENSION);
            workbook.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeOutput(User user, PersonalComputer personalComputer) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order Details");

        Row userHeader = sheet.createRow(0);
        for (int i = 0; i < userHeaders.size(); i++) {
            Cell cell = userHeader.createCell(i);
            cell.setCellValue(userHeaders.get(i));
        }

        Row userRow = sheet.createRow(1);
        Cell nameCell = userRow.createCell(0);
        nameCell.setCellValue(user.getName());
        Cell lastNameCell = userRow.createCell(1);
        lastNameCell.setCellValue(user.getLastName());
        Cell phoneNumberCell = userRow.createCell(2);
        phoneNumberCell.setCellValue(user.getPhoneNumber());


        Row personalComputerHeader = sheet.createRow(3);
        for (int i = 0; i < personalComputerHeaders.size(); i++) {
            Cell cell = personalComputerHeader.createCell(i);
            cell.setCellValue(personalComputerHeaders.get(i));
        }

        Row personalComputerRow = sheet.createRow(4);
        Cell cpuCell = personalComputerRow.createCell(0);
        cpuCell.setCellValue(personalComputer.getCpu());
        Cell motherBoardCell = personalComputerRow.createCell(1);
        motherBoardCell.setCellValue(personalComputer.getMotherBoard());
        Cell memoryRamCell = personalComputerRow.createCell(2);
        memoryRamCell.setCellValue(personalComputer.getMemoryRam());
        Cell psuCell = personalComputerRow.createCell(3);
        psuCell.setCellValue(personalComputer.getPsu());
        Cell discCell = personalComputerRow.createCell(4);
        discCell.setCellValue(personalComputer.getDisc());
        Cell otherInfoCell = personalComputerRow.createCell(5);
        otherInfoCell.setCellValue(personalComputer.getOtherInfo());
        Cell problemDescriptionCell = personalComputerRow.createCell(6);
        problemDescriptionCell.setCellValue(personalComputer.getProblemDescription());
        Cell cleanDataCell = personalComputerRow.createCell(7);
        cleanDataCell.setCellValue(personalComputer.getCleanData());


        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);

        try {
            FileOutputStream file = new FileOutputStream(PC_ENDING + user.getLastName() + "_" + (System.currentTimeMillis() / 1000) + EXCEL_EXTENSION);
            workbook.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

