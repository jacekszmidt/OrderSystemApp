package io.github.jacekszmidt.service;

import io.github.jacekszmidt.model.Laptop;
import io.github.jacekszmidt.model.PersonalComputer;
import io.github.jacekszmidt.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelComputerOutputWriter implements ComputerOutputWriter {
    private static final String EXCEL_EXTENSION = ".xlsx";
    private static final String LAP_ENDING = "LAP#";
    private static final String PC_ENDING = "PC#";
    private final List<String> userHeaders = List.of("Imie", "Nazwisko", "Telefon", "Numer zlecenia");
    private final List<String> laptopHeaders = List.of("Model", "Numer seryjny", "Inne", "Opis usterki", "Kasowac dane?");
    private final List<String> personalComputerHeaders = List.of("CPU", "Plyta Glowna", "RAM", "Zasilacz", "Dysk", "Inne", "Opis usterki", "Kasowac dane?");


    private CellStyle userRowStyle(Workbook workbook) {
        CellStyle userRowStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 9);
        font.setBold(false);
        userRowStyle.setAlignment(HorizontalAlignment.CENTER);
        userRowStyle.setFont(font);
        userRowStyle.setBorderTop(BorderStyle.THIN);
        return userRowStyle;
    }

    private CellStyle userHeaderStyle(Workbook workbook) {
        CellStyle userHeaderStyle = workbook.createCellStyle();
        userHeaderStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        userHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 10);
        font.setBold(false);
        userHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
        userHeaderStyle.setFont(font);
        userHeaderStyle.setBorderRight(BorderStyle.THIN);
        userHeaderStyle.setBorderTop(BorderStyle.THIN);
        return userHeaderStyle;
    }

    private CellStyle dataAgreementStyle(Workbook workbook) {
        CellStyle dataAgreementStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 5);
        font.setBold(false);
//        dataAgreementStyle.setWrapText(true);
        dataAgreementStyle.setAlignment(HorizontalAlignment.LEFT);
        dataAgreementStyle.setFont(font);
        dataAgreementStyle.setBorderLeft(BorderStyle.THIN);
        dataAgreementStyle.setBorderTop(BorderStyle.THIN);
        dataAgreementStyle.setBorderBottom(BorderStyle.THIN);
        return dataAgreementStyle;
    }

    @Override
    public void writeOutput(User user, Laptop laptop) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order Details");

        long orderNumber = (System.currentTimeMillis() / 1000);

        String agreement = "Wyrazam zgode na przetwarzanie moich danych osobowych\n zgodnie z ustawa o ochronie danych osobowych\n w zwiazku z realizacja zlecenia. Podanie danych\n jest dobrowolne, ale niezbedne do przetworzenia zlecenia.\n Zostalem/am poinformowany/a, ze przysluguje mi prawo\n dostepu do swoich danych, mozliwosci ich\n poprawiania lub zadania zaprzestania ich przetwarzania.";

        Row userHeader = sheet.createRow(0);
        for (int i = 0; i < userHeaders.size(); i++) {
            Cell cell = userHeader.createCell(i);
            cell.setCellValue(userHeaders.get(i));
            cell.setCellStyle(userHeaderStyle(workbook));
        }

        Row userRow = sheet.createRow(1);
        Cell nameCell = userRow.createCell(0);
        nameCell.setCellValue(user.getName());
        nameCell.setCellStyle(userRowStyle(workbook));
        Cell lastNameCell = userRow.createCell(1);
        lastNameCell.setCellValue(user.getLastName());
        lastNameCell.setCellStyle(userRowStyle(workbook));
        lastNameCell.setCellStyle(userRowStyle(workbook));
        Cell phoneNumberCell = userRow.createCell(2);
        phoneNumberCell.setCellValue(user.getPhoneNumber());
        phoneNumberCell.setCellStyle(userRowStyle(workbook));
        Cell orderNumberCell = userRow.createCell(3);
        orderNumberCell.setCellValue(orderNumber);
        orderNumberCell.setCellStyle(userRowStyle(workbook));

        Row laptopHeader = sheet.createRow(2);
        for (int i = 0; i < laptopHeaders.size(); i++) {
            Cell cell = laptopHeader.createCell(i);
            cell.setCellValue(laptopHeaders.get(i));
            cell.setCellStyle(userHeaderStyle(workbook));
        }

        Row laptopRow = sheet.createRow(3);
        Cell modelCell = laptopRow.createCell(0);
        modelCell.setCellValue(laptop.getModel());
        modelCell.setCellStyle(userRowStyle(workbook));
        Cell serialNumberCell = laptopRow.createCell(1);
        serialNumberCell.setCellValue(laptop.getSerialNumber());
        serialNumberCell.setCellStyle(userRowStyle(workbook));
        Cell otherInfoCell = laptopRow.createCell(2);
        otherInfoCell.setCellValue(laptop.getOtherInfo());
        otherInfoCell.setCellStyle(userRowStyle(workbook));
        Cell problemDescriptionCell = laptopRow.createCell(3);
        problemDescriptionCell.setCellValue(laptop.getProblemDescription());
        problemDescriptionCell.setCellStyle(userRowStyle(workbook));
        Cell cleanDataCell = laptopRow.createCell(4);
        cleanDataCell.setCellValue(laptop.getCleanData());
        cleanDataCell.setCellStyle(userRowStyle(workbook));

        Row dataAgreementRow = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell dataAgreementCell = dataAgreementRow.createCell(0);
        dataAgreementCell.setCellValue(agreement);
        dataAgreementCell.setCellStyle(dataAgreementStyle(workbook));


        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        try {
            FileOutputStream file = new FileOutputStream(LAP_ENDING + user.getLastName() + "_" + orderNumber + EXCEL_EXTENSION);
            workbook.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeOutput(User user, PersonalComputer personalComputer) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order Details");

        String agreement = "Wyrazam zgode na przetwarzanie moich danych osobowych\n zgodnie z ustawa o ochronie danych osobowych w zwiazku\n z realizacja zlecenia. Podanie danych jest dobrowolne,\n ale niezbedne do przetworzenia zlecenia.\n Zostalem/am poinformowany/a, ze przysluguje mi\n prawo dostepu do swoich danych, mozliwosci ich poprawiania lub zadania zaprzestania ich przetwarzania.";


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

