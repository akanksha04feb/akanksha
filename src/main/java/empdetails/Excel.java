package empdetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class Employee {
    private int employeeId;
    private String employeeName;
    private double employeeSalary;
    private String doj;
    public Employee(int employeeId, String employeeName, double employeeSalary, String doj) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.doj = doj;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId +", employeeName='" + employeeName + '\'' +", employeeSalary=" + 
          employeeSalary +", doj='" + doj + '\'' +'}';
    }
}

public class Excel {
    public static void main(String[] args) {
        String excelFilePath = "employee_data.xls";
        List<Employee> employeeList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new HSSFWorkbook(fis)) {
             Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                int employeeId = (int) row.getCell(0).getNumericCellValue(); 
                String employeeName = row.getCell(1).getStringCellValue();    
                double employeeSalary = row.getCell(2).getNumericCellValue(); 
                String doj = row.getCell(3).getStringCellValue(); 
                Employee employee = new Employee(employeeId, employeeName, employeeSalary, doj);
                employeeList.add(employee);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        MaxSal(employeeList);
        MinSal(employeeList);
        EmployeeCount(employeeList);
    }

    // Method to calculate max salary
    private static void MaxSal(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employee data available.");
            return;
        }

        double maxSal = Double.MIN_VALUE;
        Employee maxEmployee = null;

        for (Employee emp : employees) {
            if (emp.getEmployeeSalary() > maxSal) {
                maxSal = emp.getEmployeeSalary();
                maxEmployee = emp;
            }
        }

        System.out.println("Employee with max salary: " + maxEmployee);
    }
    private static void MinSal(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employee data available.");
            return;
        }

        double minSal = Double.MAX_VALUE;
        Employee minEmployee = null;

        for (Employee emp : employees) {
            if (emp.getEmployeeSalary() < minSal) {
                minSal = emp.getEmployeeSalary();
                minEmployee = emp;
            }
        }

        System.out.println("Employee with min salary: " + minEmployee);
    }
    private static void EmployeeCount(List<Employee> employees) {
        System.out.println("Total number of employees: " + employees.size());
    }
}
