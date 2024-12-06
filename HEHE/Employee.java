import java.io.*;
import java.util.regex.Pattern;

public abstract class Employee implements Serializable {
    private String id;
    private Demographics demographics;
    private JobSatisfaction jobSatisfaction;
    private String jobTitle;
    private String department;

    // File paths 
    private static final String SPRINT_EVAL_FILE = "SprintEval.txt";
    private static final String COMPLAINT_FILE = "Complaints.txt";

    // Constructor
    public Employee(String id, Demographics demographics, String jobTitle, String department) {
        this.id = id;
        this.demographics = demographics;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Demographics getDemographics() {
        return demographics;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public JobSatisfaction getJobSatisfaction() {
        return jobSatisfaction;
    }

    public String getDepartment() {
        return department;
    }

    // Setters
    public void setJobSatisfaction(JobSatisfaction jobSatisfaction) {
        this.jobSatisfaction = jobSatisfaction;
    }

    public void setDemographics(Demographics updatedDemographics) {
        this.demographics = updatedDemographics;
    }

    public void setJobTitle(String newJobTitle) {
        this.jobTitle = newJobTitle;
    }

    public void setDepartment(String newDepartment) {
        this.department = newDepartment;
    }

    // Validator
    public class NameValidator {
        public static boolean isValidName(String name) {
            return name != null && !name.trim().isEmpty() && Pattern.matches("[a-zA-Z\\-\\' ]+", name);
        }
    }

    // Method to file complaints
    public void fileComplaint(String complaintText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMPLAINT_FILE, true))) {
            writer.write(id + "," + complaintText);
            writer.newLine();
            System.out.println("Complaint filed for Employee ID: " + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to view sprint evaluations
    public void viewSprintEvaluations() {
        File file = new File(SPRINT_EVAL_FILE);
        if (!file.exists()) {
            System.out.println("No sprint evaluations available.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    System.out.println("Evaluation: " + data[1]);
                    System.out.println("Rating: " + data[2]);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No evaluations found for Employee ID: " + id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to view complaints
    public void viewComplaints() {
        File file = new File(COMPLAINT_FILE);
        if (!file.exists()) {
            System.out.println("No complaints file found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean complaintFound = false;
            System.out.println("Complaints for Employee ID: " + id);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", 2);
                if (data.length == 2 && data[0].equals(id)) {
                    System.out.println("Complaint: " + data[1]);
                    complaintFound = true;
                }
            }

            if (!complaintFound) {
                System.out.println("No complaints found for this employee.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
