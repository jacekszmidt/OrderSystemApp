package io.github.jacekszmidt.service;

import io.github.jacekszmidt.model.Laptop;
import io.github.jacekszmidt.model.PersonalComputer;
import io.github.jacekszmidt.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelComputerOutputWriter implements ComputerOutputWriter {
    private static final String EXCEL_EXTENSION = ".xlsx";
    private static final String LAP_ENDING = "LAP#";
    private static final String PC_ENDING = "PC#";
    private final List<String> userHeaders = List.of("Imie", "Nazwisko", "Telefon", "Numer zlecenia", "Data przyjecia");
    private final List<String> laptopHeaders = List.of("Model", "Numer seryjny", "Inne", "Opis usterki", "Kasowac dane?");
    private final List<String> personalComputerHeaders = List.of("CPU", "MB", "RAM", "PSU", "Dysk", "Inne", "Opis usterki", "Kasowac dane?");
    String agreement = "Wyrazam zgode na przetwarzanie moich danych osobowych zgodnie z ustawa o ochronie danych osobowych w zwiazku z realizacja zlecenia. Podanie danych jest dobrowolne, ale niezbedne do przetworzenia zlecenia. Zostalem/am poinformowany/a, ze przysluguje mi prawo dostepu do swoich danych, mozliwosci ich poprawiania lub zadania zaprzestania ich przetwarzania.";
    long orderNumber = (System.currentTimeMillis() / 1000);
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj = new Date();
    String fontTnr = "Times New Roman";
    String clientSign = "Podpis klienta";
    String orderClientSign = "Potwierdzam odbior sprzetu\n oraz nie mam zadnych zastrzezen\n co do jego stanu - data i podpis:";

    private CellStyle userRowStyle(Workbook workbook) {
        CellStyle userRowStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(fontTnr);
        font.setFontHeightInPoints((short) 8);
        font.setBold(false);
        userRowStyle.setAlignment(HorizontalAlignment.LEFT);
        userRowStyle.setFont(font);
        userRowStyle.setBorderTop(BorderStyle.THIN);
        userRowStyle.setBorderBottom(BorderStyle.THIN);
        userRowStyle.setBorderRight(BorderStyle.THIN);
        userRowStyle.setBorderLeft(BorderStyle.THIN);
        return userRowStyle;
    }

    private CellStyle userHeaderStyle(Workbook workbook) {
        CellStyle userHeaderStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(fontTnr);
        font.setFontHeightInPoints((short) 9);
        font.setBold(true);
        userHeaderStyle.setAlignment(HorizontalAlignment.LEFT);
        userHeaderStyle.setFont(font);
        userHeaderStyle.setBorderRight(BorderStyle.THIN);
        userHeaderStyle.setBorderTop(BorderStyle.THIN);
        userHeaderStyle.setBorderBottom(BorderStyle.THIN);
        userHeaderStyle.setBorderLeft(BorderStyle.THIN);
        return userHeaderStyle;
    }

    private CellStyle dataAgreementStyle(Workbook workbook) {
        CellStyle dataAgreementStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(fontTnr);
        font.setFontHeightInPoints((short) 5);
        font.setBold(false);
        dataAgreementStyle.setWrapText(true);
        dataAgreementStyle.setAlignment(HorizontalAlignment.LEFT);
        dataAgreementStyle.setFont(font);
        dataAgreementStyle.setBorderLeft(BorderStyle.THIN);
        return dataAgreementStyle;
    }

    private CellStyle orderClientSignStyle(Workbook workbook) {
        CellStyle orderClientStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(fontTnr);
        font.setFontHeightInPoints((short) 7);
        font.setBold(false);
        orderClientStyle.setWrapText(true);
        orderClientStyle.setAlignment(HorizontalAlignment.LEFT);
        orderClientStyle.setFont(font);
        orderClientStyle.setBorderLeft(BorderStyle.THIN);
        orderClientStyle.setBorderBottom(BorderStyle.THIN);
        orderClientStyle.setBorderRight(BorderStyle.THIN);
        orderClientStyle.setBorderTop(BorderStyle.THIN);
        return orderClientStyle;
    }

    @Override
    public void writeOutput(User user, Laptop laptop) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order Details");

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
        Cell dataFormatCell = userRow.createCell(4);
        dataFormatCell.setCellValue(df.format(dateobj));
        dataFormatCell.setCellStyle(userRowStyle(workbook));


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
        CellRangeAddress range = (new CellRangeAddress(4,4,0,3));
        sheet.addMergedRegion(range);
        RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
        CellRangeAddress range1 = (new CellRangeAddress(4,4,4,6));
        sheet.addMergedRegion(range1);
        RegionUtil.setBorderBottom(BorderStyle.THIN, range1, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, range1, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, range1, sheet);
        dataAgreementRow.setHeightInPoints(30);
        Cell dataAgreementCell = dataAgreementRow.createCell(0);
        dataAgreementCell.setCellValue(agreement);
        dataAgreementCell.setCellStyle(dataAgreementStyle(workbook));
        Cell signCell = dataAgreementRow.createCell(4);
        signCell.setCellValue(clientSign);
        signCell.setCellStyle(userRowStyle(workbook));

        Row clientAcceptSignRow = sheet.createRow(sheet.getLastRowNum() + 1);
        CellRangeAddress range2 = (new CellRangeAddress(5,5,0,4));
        sheet.addMergedRegion(range2);
        RegionUtil.setBorderBottom(BorderStyle.THIN, range2, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, range2, sheet);
        clientAcceptSignRow.setHeightInPoints(35);
        Cell clientAcceptCell = clientAcceptSignRow.createCell(0);
        clientAcceptCell.setCellValue(orderClientSign);
        clientAcceptCell.setCellStyle(orderClientSignStyle(workbook));


        sheet.setColumnWidth(0, 2000);
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
        Cell phoneNumberCell = userRow.createCell(2);
        phoneNumberCell.setCellValue(user.getPhoneNumber());
        phoneNumberCell.setCellStyle(userRowStyle(workbook));
        Cell orderNumberCell = userRow.createCell(3);
        orderNumberCell.setCellValue(orderNumber);
        orderNumberCell.setCellStyle(userRowStyle(workbook));
        Cell dataFormatCell = userRow.createCell(4);
        dataFormatCell.setCellValue(df.format(dateobj));
        dataFormatCell.setCellStyle(userRowStyle(workbook));


        Row personalComputerHeader = sheet.createRow(2);
        for (int i = 0; i < personalComputerHeaders.size(); i++) {
            Cell cell = personalComputerHeader.createCell(i);
            cell.setCellValue(personalComputerHeaders.get(i));
            cell.setCellStyle(userHeaderStyle(workbook));
        }

        Row personalComputerRow = sheet.createRow(3);
        Cell cpuCell = personalComputerRow.createCell(0);
        cpuCell.setCellValue(personalComputer.getCpu());
        cpuCell.setCellStyle(userRowStyle(workbook));
        Cell motherBoardCell = personalComputerRow.createCell(1);
        motherBoardCell.setCellValue(personalComputer.getMotherBoard());
        motherBoardCell.setCellStyle(userRowStyle(workbook));
        Cell memoryRamCell = personalComputerRow.createCell(2);
        memoryRamCell.setCellValue(personalComputer.getMemoryRam());
        memoryRamCell.setCellStyle(userRowStyle(workbook));
        Cell psuCell = personalComputerRow.createCell(3);
        psuCell.setCellValue(personalComputer.getPsu());
        psuCell.setCellStyle(userRowStyle(workbook));
        Cell discCell = personalComputerRow.createCell(4);
        discCell.setCellValue(personalComputer.getDisc());
        discCell.setCellStyle(userRowStyle(workbook));
        Cell otherInfoCell = personalComputerRow.createCell(5);
        otherInfoCell.setCellValue(personalComputer.getOtherInfo());
        otherInfoCell.setCellStyle(userRowStyle(workbook));
        Cell problemDescriptionCell = personalComputerRow.createCell(6);
        problemDescriptionCell.setCellValue(personalComputer.getProblemDescription());
        problemDescriptionCell.setCellStyle(userRowStyle(workbook));
        Cell cleanDataCell = personalComputerRow.createCell(7);
        cleanDataCell.setCellValue(personalComputer.getCleanData());
        cleanDataCell.setCellStyle(userRowStyle(workbook));

        Row dataAgreementRow = sheet.createRow(sheet.getLastRowNum() + 1);
        CellRangeAddress range = (new CellRangeAddress(4,4,0,4));
        sheet.addMergedRegion(range);
        RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
        CellRangeAddress range1 = (new CellRangeAddress(4,4,5,7));
        sheet.addMergedRegion(range1);
        RegionUtil.setBorderBottom(BorderStyle.THIN, range1, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, range1, sheet);
        dataAgreementRow.setHeightInPoints(30);
        Cell dataAgreementCell = dataAgreementRow.createCell(0);
        dataAgreementCell.setCellValue(agreement);
        dataAgreementCell.setCellStyle(dataAgreementStyle(workbook));
        Cell signCell = dataAgreementRow.createCell(5);
        signCell.setCellValue(clientSign);
        signCell.setCellStyle(userRowStyle(workbook));

        Row clientAcceptSignRow = sheet.createRow(sheet.getLastRowNum() + 1);
        CellRangeAddress range2 = (new CellRangeAddress(5,5,0,5));
        sheet.addMergedRegion(range2);
        RegionUtil.setBorderBottom(BorderStyle.THIN, range2, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, range2, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, range2, sheet);
        clientAcceptSignRow.setHeightInPoints(35);
        Cell clientAcceptCell = clientAcceptSignRow.createCell(0);
        clientAcceptCell.setCellValue(orderClientSign);
        clientAcceptCell.setCellStyle(orderClientSignStyle(workbook));

        sheet.setColumnWidth(0, 2000);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);

        try {
            FileOutputStream file = new FileOutputStream(PC_ENDING + user.getLastName() + "_" + orderNumber + EXCEL_EXTENSION);
            workbook.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

