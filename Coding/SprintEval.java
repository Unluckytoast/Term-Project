import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SprintEval {
    private String supervisorId;
    private String employeeId;
    private String evaluation;
    private int rating;

    public SprintEval(String supervisorId, String employeeId, String evaluation, int rating) {
        this.supervisorId = supervisorId;
        this.employeeId = employeeId;
        this.evaluation = evaluation;
        setRating(rating);  // Use the setter to validate the rating
    }

    // Save the evaluation in a structured format
    public void saveEvaluation() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SprintEval.txt", true))) {
            String evalData = String.format("%s,%s,%s,%d", supervisorId, employeeId, evaluation, rating);
            writer.write(evalData);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving evaluation: " + e.getMessage());
        }
    }

    // Load evaluations for a specific employee
    public static List<String> loadEvaluationsForEmployee(String employeeId) {
        List<String> evaluations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("SprintEval.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4 && data[1].equals(employeeId)) {  // Check for Employee ID
                    evaluations.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading evaluations: " + e.getMessage());
        }
        return evaluations;
    }

    // Validate and set the rating
    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }
}
