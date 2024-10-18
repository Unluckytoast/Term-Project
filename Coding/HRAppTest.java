import java.io.*;
import java.util.Scanner;

public class HRAppTest {

    private static Employee loggedInEmployee;  // Store the logged-in employee
    private static JobSatisfaction jobSatisfaction; // Store JobSatisfaction for the logged-in employee

    public static void main(String[] args) throws IOException {
        String suggestion = "", feedback = "";
        int rating = 0;
        HR hr = new HR();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Login process
        loggedInEmployee = login(hr, scanner);
        // Initialize job satisfaction for the logged-in employee
        jobSatisfaction = loggedInEmployee.getJobSatisfaction();

        if (loggedInEmployee == null) {
            System.out.println("Login failed. Exiting the system.");
            return;
        }

        System.out.println("Login successful. Welcome, " + loggedInEmployee.getDemographics().getName() + "!");
        if (loggedInEmployee.getJobTitle().equalsIgnoreCase("Supervisor")) {
            System.out.println("You have supervisor access.");
        }

        // Menu after login
        while (!exit) {
            System.out.println("\nHR Management System");
            System.out.println("1. View Your Information");
            System.out.println("2. Write Complaint");
            System.out.println("3. View Sprint Evaluation");
            System.out.println("4. Update Job Satisfaction"); // New option
            System.out.println("5. Update Contact Information"); // New option

            if (loggedInEmployee.getJobTitle().equalsIgnoreCase("Supervisor")) {
                System.out.println("6. Write Sprint Evaluation (Supervisor Only)");
            }

            if (loggedInEmployee.getDepartment().equalsIgnoreCase("HR")) {
                System.out.println("7. View Complaints (HR Only)");
            }
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // View logged-in employee's information
                    viewEmployeeInfo();
                    break;

                case 2:
                    // File a complaint
                    System.out.print("Enter your complaint: ");
                    String complaint = scanner.nextLine();
                    loggedInEmployee.fileComplaint(complaint);
                    break;

                case 3:
                    // View sprint evaluations for the logged-in employee
                    viewSprintEvaluations();
                    break;

                case 4:
                    // Update Job Satisfaction
                    updateJobSatisfaction();
                    break;
                case 5:
                    // Update Contact Information
                    updateContactInformation(scanner);
                    break;

                case 6:
                    // Write Sprint Evaluation (Supervisor only)
                    if (loggedInEmployee.getJobTitle().equalsIgnoreCase("Supervisor")) {
                        writeSprintEvaluation(hr, scanner);
                    } else {
                        System.out.println("Access Denied. This option is only available to supervisors.");
                    }
                    break;

                case 7:
                    // Only HR can view complaints
                    if (loggedInEmployee.getDepartment().equalsIgnoreCase("HR")) {
                        // Allow HR to enter another employee ID
                        System.out.print("Enter Employee ID to view their complaints: ");
                        String inputId = scanner.nextLine().trim();

                        // Call the HR class method to view complaints
                        hr.viewComplaints(inputId);
                    } else {
                        System.out.println("You do not have permission to view complaints.");
                    }
                    break;

                case 8:
                    // Exit the application
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Exiting the HR Management System.");
    }

    // Login method
    private static Employee login(HR hr, Scanner scanner) {
        System.out.print("Enter your Employee ID to log in: ");
        String employeeId = scanner.nextLine();

        Employee employee = hr.getEmployees().stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst()
            .orElse(null);

        if (employee == null) {
            System.out.println("Invalid Employee ID.");
        }

        return employee;
    }

    // View the logged-in employee's information
    private static void viewEmployeeInfo() {
        Demographics demographics = loggedInEmployee.getDemographics();
        System.out.println("ID: " + loggedInEmployee.getId());
        System.out.println("Name: " + demographics.getName());
        System.out.println("Job Title: " + loggedInEmployee.getJobTitle());
        System.out.println("Department: " + loggedInEmployee.getDepartment());
        System.out.println("Job Satisfaction: " + loggedInEmployee.getJobSatisfaction()); // Display job satisfaction
        System.out.println("Contact Info: " + demographics.getContactInfo()); // Display contact info
    }

    // Update Job Satisfaction
    private static void updateJobSatisfaction() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String suggestion = "", feedback = "";
        int rating = 0;

        System.out.print("Enter your job satisfaction rating (0-10): ");
        rating = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter your suggestion: ");
        suggestion = scanner.nextLine();
        System.out.print("Enter your feedback: ");
        feedback = scanner.nextLine();

        JobSatisfaction sjob = new JobSatisfaction(loggedInEmployee.getId(), suggestion, feedback, rating);
        loggedInEmployee.setJobSatisfaction(sjob);
        System.out.println("Job satisfaction updated to: " + loggedInEmployee.getJobSatisfaction());

        // Save changes to file
        sjob.saveToFile();
    }

    // Update Contact Information
    private static void updateContactInformation(Scanner scanner) {
        System.out.print("Enter your new contact information: ");
        String newContactInfo = scanner.nextLine();
        loggedInEmployee.getDemographics().setContactInfo(newContactInfo); // Update contact info in demographics
        System.out.println("Contact information updated successfully.");
    }

    // View sprint evaluations for the logged-in employee
    private static void viewSprintEvaluations() {
        String employeeId = loggedInEmployee.getId();
        boolean foundEvaluation = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("SprintEval.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(employeeId)) {
                    foundEvaluation = true;
                    System.out.println("Evaluation: " + parts[1] + ", Rating: " + parts[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading sprint evaluations.");
            e.printStackTrace();
        }

        if (!foundEvaluation) {
            System.out.println("No evaluations found for employee ID: " + employeeId);
        }
    }

    // Write a sprint evaluation (supervisor only)
    private static void writeSprintEvaluation(HR hr, Scanner scanner) {
        System.out.print("Enter Employee ID to write evaluation for: ");
        String employeeId = scanner.nextLine();

        Employee employeeToEvaluate = hr.getEmployees().stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst()
            .orElse(null);

        if (employeeToEvaluate == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter sprint evaluation text: ");
        String evaluation = scanner.nextLine();
        System.out.print("Enter star rating (1-5): ");
        int starRating = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        // Write the evaluation to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SprintEval.txt", true))) {
            writer.write(employeeToEvaluate.getId() + "," + evaluation + "," + starRating);
            writer.newLine();
            System.out.println("Sprint evaluation written for Employee ID: " + employeeToEvaluate.getId());
        } catch (IOException e) {
            System.out.println("Error writing sprint evaluation.");
            e.printStackTrace();
        }
    }
}
