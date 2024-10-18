import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Supervisor extends Employee {

    // Constructor
    public Supervisor(String id, Demographics demographics, String jobTitle, String department) {
        super(id, demographics, jobTitle, department);
    }

    // Write a sprint evaluation for a lower-level employee
    public void writeSprintEvaluation(String employeeId, String evaluationText, int starRating) {
        // Ensure that the star rating is between 1 and 5
        if (starRating < 1 || starRating > 5) {
            System.out.println("Invalid star rating. Please enter a rating between 1 and 5.");
            return;
        }

        String evalData = employeeId + "," + evaluationText + "," + starRating;

        // Append the evaluation data to SprintEval.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SprintEval.txt", true))) {
            writer.write(evalData);
            writer.newLine();
            System.out.println("Sprint evaluation successfully written for Employee ID: " + employeeId);
        } catch (IOException e) {
            System.out.println("Error writing sprint evaluation.");
            e.printStackTrace();
        }
    }
}
