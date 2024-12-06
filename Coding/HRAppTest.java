
public class HRAppTest {

    private static Employee loggedInEmployee;  // Store the logged-in employee
    private static JobSatisfaction jobSatisfaction; // Store JobSatisfaction for the logged-in employee

    // Login method
    public static Employee login(HR hr, String id) {
        System.out.print("Enter your Employee ID to log in: ");
        String employeeId = id;

        Employee employee = hr.getEmployees().stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst()
            .orElse(null);

        if (employee == null) {
            System.out.println("Invalid Employee ID.");
        }

        return employee;
    }
}
