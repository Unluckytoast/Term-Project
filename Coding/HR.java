import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HR {
    private List<Employee> employees;

    // Constructor to load employees on startup
    public HR() {
        employees = new ArrayList<>();
        loadEmployees();
    }

    // Add a new employee
    public String addEmployee(Demographics demographics, String jobTitle, String department) {
        String id = String.valueOf((int) (Math.random() * 10000)); // Generate random employee ID
        Employee newEmployee = new LowerLevelEmployee(id, demographics, jobTitle, department);
        employees.add(newEmployee);
        saveEmployees();
        return id;
    }

    // Fire an employee
    public void fireEmployee(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            employees.remove(employee);
            saveEmployees();
            System.out.println("Employee with ID " + employeeId + " has been fired.");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
        }
    }

    // Edit employee information
    public void editEmployee(String employeeId, Demographics updatedDemographics, String newJobTitle, String newDepartment) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            employee.setDemographics(updatedDemographics);
            employee.setJobTitle(newJobTitle);
            employee.setDepartment(newDepartment);
            saveEmployees();
            System.out.println("Employee with ID " + employeeId + " has been updated.");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
        }
    }

    // Find employee by ID
    public Employee findEmployeeById(String employeeId) {
        return employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    // Save employees to file
    public void saveEmployees() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employeeData.txt"))) {
            for (Employee employee : employees) {
                Demographics d = employee.getDemographics();
                writer.write(employee.getId() + "," +
                        d.getName() + "," +
                        d.getRace() + "," +
                        d.getAge() + "," +
                        d.getAddress() + "," +
                        d.getContactInfo() + "," +
                        employee.getJobTitle() + "," +
                        employee.getDepartment());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load employees from file
    private void loadEmployees() {
        File file = new File("employeeData.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {
                    Demographics demographics = new Demographics(data[1], data[2], Integer.parseInt(data[3]), data[4], data[5]);
                    Employee employee = new LowerLevelEmployee(data[0], demographics, data[6], data[7]);
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void viewComplaints(String employeeId) {
        Employee employee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst()
            .orElse(null);

        if (employee != null) {
            employee.viewComplaints(); // Call the employee's viewComplaints method
        } else {
            System.out.println("Employee ID not found.");
        }
    }

    // Get all employees
    public List<Employee> getEmployees() {
        return employees;
    }
}
