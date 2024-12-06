import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SprintEval {
    private String supervisorId;
    private String employeeId;
    private String evaluation;
    private int rating;

    public SprintEval(String supervisorId, String evaluation, int rating) {
        this.supervisorId = supervisorId;
        this.employeeId = employeeId;
        this.evaluation = evaluation;
        setRating(rating);  // Use the setter to validate the rating
    }

    // Save the evaluation in a structured format
    public void saveEvaluation() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SprintEval.txt", true))) {
            String evalData = String.format("%s,%s,%s", supervisorId,evaluation, rating);
            writer.write(evalData);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving evaluation: " + e.getMessage());
        }
    }

    // Load evaluations for a specific employee
    /*public static List<String> loadEvaluationsForEmployee(String employeeId) {
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
    }*/

    /// Load evaluations for a specific employee, returning only the values after the employee ID
    public static List<String> loadEvaluationsForEmployee(String employeeId) 
    {
        List<String> evaluations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("SprintEval.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) 
        {
            String[] data = line.split(",");
            if (data.length >= 2 && data[1].equals(employeeId)) 
            {
                // Join the values after the employeeId (data[1])
                StringBuilder result = new StringBuilder();
                for (int i = 2; i < data.length; i++) 
                {
                    result.append(data[i].trim()); // Append with trimming whitespace
                    if (i < data.length - 1) 
                    {
                        result.append(", "); // Add a comma if it's not the last value
                    }
                }
                evaluations.add(result.toString());
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
