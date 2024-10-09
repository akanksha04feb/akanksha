package empdetails; 
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import java.io.FileOutputStream;
import java.io.IOException;

public class Employees {
    public static void main(String[] args) {
        try (Workbook workbook = new HSSFWorkbook(); 
             FileOutputStream fileOut = new FileOutputStream("employee_data.xls")) {
              Sheet sheet = workbook.createSheet("Employee Data");
              String[] headers = {"EmployeeId", "EmployeeName", "EmployeeSalary", "Doj"};
              Row headerRow = sheet.createRow(0);
              for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
              }
            Object[][] empData = {
                    {101, "Kyathi", 30000, "2022-07-03"},
                    {102, "Divya", 50000, "2023-04-22"},
                    {103, "Aakanksha", 60000, "2021-05-19"},
                    {104, "Priyanshi", 45000, "2023-07-28"},
                   
            };
            int rowNum = 1;
            for (Object[] emp : empData) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < emp.length; i++) {
                    if (emp[i] instanceof Integer) {
                        row.createCell(i).setCellValue((Integer) emp[i]);
                    } else {
                        row.createCell(i).setCellValue(emp[i].toString());
                    }
                }
            }
            workbook.write(fileOut);
            System.out.println("Excel file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
