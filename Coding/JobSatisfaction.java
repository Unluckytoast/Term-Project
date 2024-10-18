import java.io.*;

public class JobSatisfaction {
    private String id; //Store Id
    private String feedback;
    private String suggestion;
    private int rating;

    // Constructor
    public JobSatisfaction(String employeeId, String suggestion, String feedback, int rating) {
        this.id = employeeId;
        this.feedback = feedback;
        this.suggestion = suggestion;
        this.rating = rating;
    }

    // Getters
    public String getEmployeeId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getSuggestion() {
        return suggestion;
    }

    // feedback validator
    public boolean isValidFeedback() {
        return feedback != null && !feedback.trim().isEmpty();
    }

    //  suggestion validator 
    public boolean isValidSuggestion() {
        return suggestion != null && !suggestion.trim().isEmpty();
    }

    // Save job satisfaction
    public void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jobsatisfaction.txt", true))) {
            writer.write("Employee ID: " + id);
            writer.newLine();
            writer.write("Rating (1-10): " + rating);
            writer.newLine();
            writer.write("Feedback: " + feedback);
            writer.newLine();
            writer.write("Suggestion: " + suggestion);
            writer.newLine();
            writer.write("-----");
            writer.newLine();
            writer.flush();
            System.out.println("Job satisfaction data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving job satisfaction data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void viewJobSatisfaction() {
        File file = new File("jobsatisfaction.txt");
        
        // Check if the file exists
        if (!file.exists()) {
            System.out.println("No job satisfaction data available.");
            return;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;
    
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line to find the employee ID
                if (line.contains("Employee ID: " + id)) {
                    String ratingLine = reader.readLine();
                    String feedbackLine = reader.readLine();
                    String suggestionLine = reader.readLine();
    
                    // Extracting the details from the file
                    int rating = Integer.parseInt(ratingLine.split(": ")[1]);
                    String feedback = feedbackLine.split(": ")[1];
                    String suggestion = suggestionLine.split(": ")[1];
    
                    // Print out the job satisfaction details
                    System.out.println("Job Satisfaction for Employee ID: " + id);
                    System.out.println("Rating: " + rating);
                    System.out.println("Feedback: " + feedback);
                    System.out.println("Suggestion: " + suggestion);
                    found = true;
    
                    // Skip the separator line (if exists)
                    reader.readLine();
                }
            }
    
            // If no records are found for this employee
            if (!found) {
                System.out.println("No job satisfaction records found for Employee ID: " + id);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//toString to display job satisfaction
    @Override
    public String toString() {
        return "Employee ID: " + id + "\n" +
               "Rating: " + rating + "\n" +
               "Feedback: " + feedback + "\n" +
               "Suggestion: " + suggestion;
    }
}
