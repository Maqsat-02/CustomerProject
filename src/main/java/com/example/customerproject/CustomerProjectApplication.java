package com.example.customerproject;

import com.example.customerproject.dao.CustomerRepository;
import com.example.customerproject.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class CustomerProjectApplication implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(CustomerProjectApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        FileInputStream fis = new FileInputStream(".\\datafiles\\customer_dat.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        List<Customer> customerList = new ArrayList<>();
        int rows = sheet.getLastRowNum();

        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);
            String fullname = row.getCell(0).getStringCellValue();
            Long iin = null;
            Date birthdate = null;
            if (row.getCell(1) != null) {
                iin = ((Double) getCellValue(row.getCell(1))).longValue();
            }
            String docNum = String.valueOf(getCellValue(row.getCell(2)));
            String docDetails = row.getCell(3).getStringCellValue();
            if (row.getCell(4) != null) {
                birthdate = row.getCell(4).getDateCellValue();
            }
            boolean resident = row.getCell(5).getBooleanCellValue();
            customerList.add(new Customer(iin, fullname, docNum, docDetails, birthdate, resident));
        }

        try {
            customerRepository.saveAll(customerList);
            log.info("Database initialization with excel file was successfully");
        } catch (Exception e) {
            log.error("Error while initialization data");
            e.printStackTrace();
        }
        fis.close();

    }


    public static Object getCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                case Cell.CELL_TYPE_NUMERIC:
                    return cell.getNumericCellValue();
                case Cell.CELL_TYPE_STRING:
                    return (cell.getStringCellValue());
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    return (cell.getErrorCellValue());

                // CELL_TYPE_FORMULA will never occur
                case Cell.CELL_TYPE_FORMULA:
                    break;
                default:
                    return null;
            }

        }
        return null;
    }

}
