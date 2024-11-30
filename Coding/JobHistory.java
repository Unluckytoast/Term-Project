import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JobHistory
{
    private static final String jobHist = "JobHistory.txt";
    BufferedReader reader;
    BufferedWriter writer;
    
    //Method to view past jobs of a certain id
    public List<String> getPastJob(String id)
    {
        List<String> pastJobs = new ArrayList<>();
        try
        {
            reader = new BufferedReader(new FileReader(jobHist));
            String line;
            boolean idFound = false;

            while ((line = reader.readLine()) != null)
            {
                if(line.contains(id))
                {
                    idFound = true;
                }
                
                if(idFound && line.contains("PJ:"))
                {
                    String[] jobs = line.split(",");
                    for (String part: jobs)
                    {
                        if (part.trim().startsWith("PJ:"))
                        {
                            pastJobs.add(part.substring(5).trim());
                        }
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.getMessage();
        }
        if (pastJobs.isEmpty()) 
        {
            pastJobs.add("No past jobs found for user " + id); // Default if no jobs found
        }
        return pastJobs;
    }

    //Method to add a past job for a certain id
    public void addPastJob(String id, String newPastJob, LocalDate startDate, LocalDate endDate)
    {
        try 
        {
            // Step 1: Read the file into a List of Strings
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(jobHist));
            String line;
            boolean isLineModified = false;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);

            while ((line = reader.readLine()) != null) 
            {
                // Step 2: Check if the current line contains the search text
                if (line.contains(id) && !isLineModified) 
                {
                    // Step 3: Modify the line by appending or prepending the new content
                    line = line + " PJ: " + newPastJob + ": " + formattedStartDate + ": " + formattedEndDate;  // You can prepend by doing: textToAdd + line
                    isLineModified = true; // Modify only the first occurrence
                }
                lines.add(line); // Add each line (modified or not) to the list
            }
            reader.close();

            // Step 4: Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(jobHist));
            for (String modifiedLine : lines) 
            {
                writer.write(modifiedLine);
                writer.newLine();
            }
            writer.close();
        } 
        catch(IOException e)
        {
            e.getMessage();
        }
    }

    //Method to remove a past job for a certain id
    public void removePastJob(String id, String jobToRemove, LocalDate startDate, LocalDate endDate)
    {
        try
        {
            // Step 1: Create a list to store lines read from the file
            List<String> lines = new ArrayList<>();

            // Step 2: Open the file for reading
            reader = new BufferedReader(new FileReader(jobHist));
            String line;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);
            String jobWithDates = "PJ: " + jobToRemove + ": " + formattedStartDate + ": " + formattedEndDate;

            // Step 3: Calculate the total time in the job
            //Add the total time in job to the string jobWithTime

            // Step 4: Read each line of the file
            while((line = reader.readLine()) != null)
            {
                // Step 5: Check if the line contains both the user id and job to remove
                if (line.contains(id) && line.contains(jobToRemove)) 
                {
                    // Step 6: Remove the job along with the time in job if it matches
                    line = line.replace(jobWithDates, "").trim();
                }
                // Add the (possibly modified) line to the list
                lines.add(line);
            }

            // Step 7: Close the reader as reading is finished
            reader.close();

            // Step 8: Open the file for writing (overwriting the file)
            writer = new BufferedWriter(new FileWriter(jobHist));

            // Step 9: Write the modified lines back to the file
            for(String newLine : lines)
            {
                writer.write(newLine);
                writer.newLine();
            }

            // Step 10: Close the writer after writing
            writer.close();
        }
        catch(IOException e)
        {
            // Step 11: Handle any potential IO exceptions
            e.getMessage();
        }
    }

    //Method to view the current job of a certain id
    public String getCurrentJob(String id) 
    {
        String curr = "";
        try 
        {
            // Step 1: Open the file for reading
            reader = new BufferedReader(new FileReader(jobHist));
            String line;
            boolean idFound = false;  // To track if the user ID has been found
    
            // Step 2: Print a message indicating the job search for the user
            System.out.println("Current Job for user " + id + ":");
    
            // Step 3: Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                
                // Step 4: Check if the current line contains the user ID
                if (line.contains(id)) 
                {
                    idFound = true;  // Mark that the ID was found
                }
    
                // Step 5: If ID was found and the line contains a "CJ:" (current job), process it
                if (idFound && line.contains("CJ:")) 
                {
                    // Step 6: Split the line by commas to get the job parts
                    String[] jobs = line.split(",");
    
                    // Step 7: Loop through each part of the job description
                    for (String part : jobs) 
                    {
                        // Step 8: If the part starts with "CJ:", print the job description
                        if (part.trim().startsWith("CJ:")) 
                        {
                            // Step 9: Print the job description, removing the "CJ: " prefix
                            curr = part;
                        }
                    }
                }
            }
            // Step 10: Reader will be closed automatically at the end of the try block
        }
        catch (IOException e) 
        {
            // Step 11: Handle any IO exceptions (printing the message if one occurs)
            e.getMessage();
        }
        if (curr.isEmpty())
        {
            curr = "No current job is found";
        }
        return curr;
    }

    //Method to add current job to a certain id
    public void addCurrentJob(String id, String newCurrentJob, int startYear) 
    {
        try 
        {
            // Step 1: Read the file into a List of Strings
            // Create a list to hold all the lines from the file
            List<String> lines = new ArrayList<>();
            
            // Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(jobHist));
            String line;
            boolean isLineModified = false; // Flag to ensure only one modification is made
            int totalTimeInJob = 2024 - startYear; // Calculate total time in job based on the start year
    
            // Step 2: Read each line of the file
            while ((line = reader.readLine()) != null) 
            {
                // Check if the current line contains the user ID and hasn't been modified yet
                if (line.contains(id) && !isLineModified) 
                {
                    // Step 3: Append new job information (CJ: newCurrentJob: time) to the line
                    line = line + " CJ: " + newCurrentJob + ": ";  // Add new current job to the line
                    
                    // Add time duration depending on how long the user has been in the job
                    if (totalTimeInJob > 0) 
                    {
                        line = line + totalTimeInJob + ",";  // Add the job duration if more than a year
                    } 
                    else 
                    {
                        line = line + "Less than a year,";  // Otherwise, indicate it's less than a year
                    }
                    
                    isLineModified = true; // Ensure only the first occurrence of the ID is modified
                }
                
                // Add the (possibly modified) line to the list of lines
                lines.add(line);
            }
    
            // Close the reader after reading all the lines
            reader.close();
    
            // Step 4: Write the modified content back to the file
            // Open the file for writing (overwriting it with new content)
            writer = new BufferedWriter(new FileWriter(jobHist));
            
            // Write each line from the list (with the modifications) back to the file
            for (String modifiedLine : lines) 
            {
                writer.write(modifiedLine);
                writer.newLine(); // Ensure each line ends with a new line
            }
            
            // Close the writer after writing all lines
            writer.close();
        } 
        catch (IOException e) 
        {
            // Handle any potential IOExceptions
            e.getMessage();  // Get and display the exception message if any error occurs
        }
    }
    
    //Method to remove current job for a certain id
    public void removeCurrentJob(String id, String jobToRemove, int startYear) 
    {
        try 
        {
            // Step 1: Initialize a list to store the lines from the file
            List<String> lines = new ArrayList<>();
            
            // Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(jobHist));
            String line;
    
            // Calculate total time in the job based on the start year
            int totalTimeInJob = 2024 - startYear;
    
            // Create a string that represents the current job with its total time in job
            String jobWithTime = "CJ: " + jobToRemove + ": " + totalTimeInJob + ",";
    
            // Step 2: Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                // Check if the line contains both the user ID and the job to be removed
                if (line.contains(id) && line.contains(jobToRemove)) 
                {
                    // Step 3: Remove the "CJ: jobToRemove" and its corresponding time from the line
                    line = line.replace(jobWithTime, "").trim(); // Remove the job and time in one go
                }
    
                // Add the (modified or unmodified) line to the list of lines
                lines.add(line);
            }
    
            // Close the reader after reading all the lines
            reader.close();
    
            // Step 4: Open the file for writing (overwrite mode) using BufferedWriter
            writer = new BufferedWriter(new FileWriter(jobHist));
            
            // Write each line from the list (with the modifications) back to the file
            for (String newLine : lines) 
            {
                writer.write(newLine); // Write the modified or unmodified line back
                writer.newLine(); // Ensure each line ends with a new line
            }
    
            // Close the writer after writing all lines
            writer.close();
        } 
        catch (IOException e) 
        {
            // Handle any potential IOExceptions
            e.getMessage();  // Get and display the exception message if any error occurs
        }
    }
    
    //Method to view skills of a certain id
    public List<String> getSkill(String id) 
    {
        List<String> skills = new ArrayList<>();
        try 
        {
            // Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(jobHist));
            String line;
            boolean idFound = false; // Flag to check if the ID has been found
    
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Check if the current line contains the user ID
                if (line.contains(id)) {
                    idFound = true; // Set flag to true once the ID is found
                }
    
                // If the ID is found and the line contains "Skill:", extract and print the skills
                if (idFound && line.contains("Skill:")) {
                    String[] jobs = line.split(","); // Split the line by commas
                    for (String part : jobs) { // Loop through each part of the split line
                        if (part.trim().startsWith("Skill:")) { // Check if the part starts with "Skill:"
                            skills.add(part.substring(8).trim()); // Print the skill (after "Skill:")
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage(); // Handle any IOException that occurs
        }
        if (skills.isEmpty())
        {
            skills.add("No skills found");
        }
        return skills;
    }
    
    //Method to add skill to a certain id
    public void addSkill(String id, String newSkill)
    {
        try {
            // Step 1: Read the file into a List of Strings
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(jobHist)); // Open file for reading
            String line;
            boolean isLineModified = false; // Flag to ensure only one line is modified
    
            // Step 2: Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Step 3: Check if the current line contains the user ID and hasn't been modified yet
                if (line.contains(id) && !isLineModified) {
                    // Step 4: Modify the line by appending the new skill
                    line = line + " Skill: " + newSkill + ",";
                    isLineModified = true; // Set flag to true once the line is modified
                }
                lines.add(line); // Add each line (modified or not) to the list
            }
            reader.close(); // Close the reader
    
            // Step 5: Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(jobHist)); // Open file for writing
            for (String modifiedLine : lines) {
                writer.write(modifiedLine); // Write each line to the file
                writer.newLine(); // Add a new line after each line
            }
            writer.close(); // Close the writer
        } catch (IOException e) {
            e.getMessage(); // Handle any IOException that occurs
        }
    }
    
    //Method to remove a skill for a certain id
    public void removeSkill(String id, String skillToRemove)
    {
        try 
        {
            // Step 1: Initialize a list to store the lines from the file
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(jobHist)); // Open file for reading
            String line;
    
            // Step 2: Create a string that represents the skill to remove
            String removeSkill = "Skill: " + skillToRemove + ",";
    
            // Step 3: Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Step 4: If the line contains the skill to remove, replace it
                if (line.contains(removeSkill)) {
                    line = line.replace(removeSkill, "").trim(); // Remove the skill from the line
                }
                lines.add(line); // Add the (modified or unmodified) line to the list
            }
            reader.close(); // Close the reader
    
            // Step 5: Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(jobHist)); // Open file for writing
            for (String newLine : lines) {
                writer.write(newLine); // Write each line back to the file
                writer.newLine(); // Add a new line after each line
            }
            writer.close(); // Close the writer
        } catch (IOException e) {
            e.getMessage(); // Handle any IOException that occurs
        }
    }
    
    //Method to view talents and gifts of a certain id
    public List<String> getTalentsAndGifts(String id) 
    {
        List<String> talents = new ArrayList<>();
        try 
        {
            // Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(jobHist));
            String line;
            boolean idFound = false; // Flag to check if the ID has been found
    
            // Print out the header for the talents or gifts
            System.out.println("Talents or Gifts for user " + id + ":");
    
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Check if the current line contains the user ID
                if (line.contains(id)) {
                    idFound = true; // Set flag to true once the ID is found
                }
    
                // If the ID is found and the line contains "Talent or Gift:", extract and print the talents/gifts
                if (idFound && line.contains("Talent or Gift:")) {
                    String[] jobs = line.split(","); // Split the line by commas
                    for (String part : jobs) { // Loop through each part of the split line
                        if (part.trim().startsWith("Talent or Gift:")) { // Check if the part starts with "Talent or Gift:"
                            talents.add(part.substring(17).trim()); // Print the talent or gift (after "Talent or Gift:")
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage(); // Handle any IOException that occurs
        }
        if (talents.isEmpty())
        {
            talents.add("Np past talents or gifts found");
        }
        return talents;
    }
    
    //Method to add a talent or gift from a certain id
    public void addTalentsAndGifts(String id, String newTalentOrGift) 
    {
        try {
            // Step 1: Read the file into a List of Strings
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(jobHist)); // Open file for reading
            String line;
            boolean isLineModified = false; // Flag to ensure only one line is modified
    
            // Step 2: Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Step 3: Check if the current line contains the user ID and hasn't been modified yet
                if (line.contains(id) && !isLineModified) {
                    // Step 4: Modify the line by appending the new talent or gift
                    line = line + " Talent or Gift: " + newTalentOrGift + ",";
                    isLineModified = true; // Set flag to true once the line is modified
                }
                lines.add(line); // Add each line (modified or not) to the list
            }
            reader.close(); // Close the reader
    
            // Step 5: Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(jobHist)); // Open file for writing
            for (String modifiedLine : lines) {
                writer.write(modifiedLine); // Write each line to the file
                writer.newLine(); // Add a new line after each line
            }
            writer.close(); // Close the writer
        } catch (IOException e) {
            e.getMessage(); // Handle any IOException that occurs
        }
    }
    
    //Method to remove a talent or gift for a certain id
    public void removeTalentsAndGifts(String id, String talentOrGiftToRemove) 
    {
        try {
            // Step 1: Initialize a list to store the lines from the file
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(jobHist)); // Open file for reading
            String line;
    
            // Step 2: Create a string that represents the talent or gift to remove
            String removeSkill = "Talent or Gift: " + talentOrGiftToRemove + ",";
    
            // Step 3: Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Step 4: If the line contains the talent or gift to remove, replace it
                if (line.contains(removeSkill)) {
                    line = line.replace(removeSkill, "").trim(); // Remove the talent or gift from the line
                }
                lines.add(line); // Add the (modified or unmodified) line to the list
            }
            reader.close(); // Close the reader
    
            // Step 5: Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(jobHist)); // Open file for writing
            for (String newLine : lines) {
                writer.write(newLine); // Write each line back to the file
                writer.newLine(); // Add a new line after each line
            }
            writer.close(); // Close the writer
        } catch (IOException e) {
            e.getMessage(); // Handle any IOException that occurs
        }
    }
    
    public static void main(String[] args)
    {
        JobHistory job = new JobHistory();
    }
}
