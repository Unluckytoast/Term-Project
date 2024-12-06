import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JobHistory
{
    private static final String JOBHIST = "JobHistory.txt";
    BufferedReader reader;
    BufferedWriter writer;
    
    //Method to view past jobs of a certain id
    public List<String> getPastJob(String id)
    {
        List<String> pastJobs = new ArrayList<>();
        try
        {
            //Read Lines on the jobHistory.txt file to find the line with
            //the matching id
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean idFound = false;

            while ((line = reader.readLine()) != null)
            {
                if(line.startsWith("id: " + id + ","))
                {
                    idFound = true;
                }
                
                //If id is found, this searches for the past jobs, and adds them to pastJobs
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
                    break;
                }
            }
        }
        catch(IOException e)
        {
            e.getMessage();
        }
        //If pastJobs is Empty, it will say that nothing is found
        if (pastJobs.isEmpty()) 
        {
            pastJobs.add("No past jobs found for user " + id);
        }
        System.out.println(pastJobs);
        return pastJobs;
    }

    //Method to add a past job for a certain id
    public void addPastJob(String id, String newPastJob, LocalDate startDate, LocalDate endDate)
    {
        try 
        {
            //Read the file into a List of Strings
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean isLineModified = false;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);

            while ((line = reader.readLine()) != null) 
            {
                //Check if the current line contains the search text
                if (line.contains(id) && !isLineModified) 
                {
                    //Modify the line by appending or prepending the new content
                    line = line + " PJ: " + newPastJob + ": " + formattedStartDate + ": " + formattedEndDate + ",";
                    isLineModified = true;
                }
                lines.add(line);
            }
            reader.close();

            //Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(JOBHIST));
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
            //Create a list to store lines read from the file
            List<String> lines = new ArrayList<>();

            //Open the file for reading
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;

            //Create String to find with past job
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);
            String jobWithDates = "PJ: " + jobToRemove + ": " + formattedStartDate + ": " + formattedEndDate + ",";

            //Read each line of the file
            while((line = reader.readLine()) != null)
            {
                //Check if the line contains both the user id and job to remove
                if (line.contains(id) && line.contains(jobToRemove)) 
                {
                    //Remove the job along with the time in job if it matches
                    line = line.replace(jobWithDates, "").trim();
                }
                //Add the (possibly modified) line to the list
                lines.add(line);
            }

            //Close the reader as reading is finished
            reader.close();

            //Open the file for writing (overwriting the file)
            writer = new BufferedWriter(new FileWriter(JOBHIST));

            //Write the modified lines back to the file
            for(String newLine : lines)
            {
                writer.write(newLine);
                writer.newLine();
            }

            //Close the writer after writing
            writer.close();
        }
        catch(IOException e)
        {
            //Handle any potential IO exceptions
            e.getMessage();
        }
    }

    //Method to view the current job of a certain id
    public String getCurrentJob(String id) 
    {
        String curr = "";
        try 
        {
            //Open the file for reading
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean idFound = false;
    
            //Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                
                //Check if the current line contains the user ID
                if (line.startsWith("id: " + id + ",")) 
                {
                    idFound = true;
                }
    
                //If ID was found and the line contains a "CJ:" (current job), process it
                if (idFound && line.contains("CJ:")) 
                {
                    //Split the line by commas to get the job parts
                    String[] jobs = line.split(",");
    
                    //Loop through each part of the job description
                    for (String part : jobs) 
                    {
                        //If the part starts with "CJ:", print the job description
                        if (part.trim().startsWith("CJ:")) 
                        {
                            //Print the job description, removing the "CJ: " prefix
                            curr = part.substring(5).trim();
                        }
                    }
                    break;
                }
            }
            //Reader will be closed automatically at the end of the try block
        }
        catch (IOException e) 
        {
            //Handle any IO exceptions (printing the message if one occurs)
            e.getMessage();
        }
        if (curr.equals(""))
        {
            curr = "No current job is found";
        }
        return curr;
    }

    //Method to add current job to a certain id
    public void addCurrentJob(String id, String newCurrentJob, LocalDate startDate) 
    {
        try 
        {
            //Read the file into a List of Strings
            //Create a list to hold all the lines from the file
            List<String> lines = new ArrayList<>();
            
            //Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean isLineModified = false; //Flag to ensure only one modification is made
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedStartDate = startDate.format(formatter);

            //Read each line of the file
            while ((line = reader.readLine()) != null) 
            {
                //Check if the current line contains the user ID and hasn't been modified yet
                if (line.contains(id) && !isLineModified) 
                {
                    //Append new job information (CJ: newCurrentJob: time) to the line
                    line = line + " CJ: " + newCurrentJob + ": "+ formattedStartDate + ","; 
                    isLineModified = true;
                }
                
                //Add the (possibly modified) line to the list of lines
                lines.add(line);
            }
    
            //Close the reader after reading all the lines
            reader.close();
    
            //Write the modified content back to the file
            //Open the file for writing (overwriting it with new content)
            writer = new BufferedWriter(new FileWriter(JOBHIST));
            
            //Write each line from the list (with the modifications) back to the file
            for (String modifiedLine : lines) 
            {
                writer.write(modifiedLine);
                writer.newLine();
            }
            
            //Close the writer after writing all lines
            writer.close();
        } 
        catch (IOException e) 
        {
            //Handle any potential IOExceptions
            e.getMessage();
        }
    }
    
    //Method to remove current job for a certain id
    public void removeCurrentJob(String id, String jobToRemove, LocalDate startDate) 
    {
        try 
        {
            //Initialize a list to store the lines from the file
            List<String> lines = new ArrayList<>();
            
            //Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
    
            //Create a string that represents the current job with its total time in job
            String jobWithTime = "CJ: " + jobToRemove + ": " + startDate + ",";
    
            //Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //Check if the line contains both the user ID and the job to be removed
                if (line.contains(id) && line.contains(jobToRemove)) 
                {
                    //Remove the "CJ: jobToRemove" and its corresponding time from the line
                    line = line.replace(jobWithTime, "").trim();
                }
    
                //Add the (modified or unmodified) line to the list of lines
                lines.add(line);
            }
            reader.close();
    
            //Open the file for writing (overwrite mode) using BufferedWriter
            writer = new BufferedWriter(new FileWriter(JOBHIST));
            
            //Write each line from the list (with the modifications) back to the file
            for (String newLine : lines) 
            {
                writer.write(newLine);
                writer.newLine();
            }
    
            //Close the writer after writing all lines
            writer.close();
        } 
        catch (IOException e) 
        {
            //Handle any potential IOExceptions
            e.getMessage();
        }
    }
    
    //Method to view skills of a certain id
    public List<String> getSkill(String id) 
    {
        List<String> skills = new ArrayList<>();
        try 
        {
            //Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean idFound = false;

            //Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //Check if the current line contains the user ID
                if (line.contains(id)) 
                {
                    idFound = true;
                }
    
                //If the ID is found and the line contains "Skill:", extract and print the skills
                if (idFound && line.contains("Skill:")) 
                {
                    String[] jobs = line.split(","); 
                    for (String part : jobs) 
                    { 
                        if (part.trim().startsWith("Skill:")) 
                        {
                            skills.add(part.substring(8).trim());
                        }
                    }
                }
            }
        } catch (IOException e) 
        {
            //Handle any IOException that occurs
            e.getMessage();
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
        try 
        {
            //Read the file into a List of Strings
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean isLineModified = false;
    
            //Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //Check if the current line contains the user ID and hasn't been modified yet
                if (line.contains(id) && !isLineModified) 
                {
                    //Modify the line by appending the new skill
                    line = line + " Skill: " + newSkill + ",";
                    isLineModified = true;
                }
                lines.add(line);
            }
            reader.close();
    
            //Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(JOBHIST));
            for (String modifiedLine : lines) 
            {
                writer.write(modifiedLine);
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            //Handle any IOException that occurs
            e.getMessage();
        }
    }
    
    //Method to remove a skill for a certain id
    public void removeSkill(String id, String skillToRemove)
    {
        try 
        {
            //Initialize a list to store the lines from the file
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
    
            //Create a string that represents the skill to remove
            String removeSkill = "Skill: " + skillToRemove + ",";
    
            //Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //If the line contains the skill to remove, replace it
                if (line.contains(removeSkill)) 
                {
                    line = line.replace(removeSkill, "").trim();
                }
                lines.add(line);
            }
            reader.close();
    
            //Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(JOBHIST));
            for (String newLine : lines) 
            {
                writer.write(newLine);
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            //Handle any IOException that occurs
            e.getMessage();
        }
    }
    
    //Method to view talents and gifts of a certain id
    public List<String> getTalentsAndGifts(String id) 
    {
        List<String> talents = new ArrayList<>();
        try 
        {
            //Open the file for reading using BufferedReader
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean idFound = false;
            
            //Read the file line by line
            while ((line = reader.readLine()) != null)
            {
                //Check if the current line contains the user ID
                if (line.contains(id)) 
                {
                    idFound = true;
                }
    
                //If the ID is found and the line contains "Talent or Gift:", extract and print the talents/gifts
                if (idFound && line.contains("Talent or Gift:")) 
                {
                    String[] jobs = line.split(",");
                    for (String part : jobs) 
                    {
                        //Check if the part starts with "Talent or Gift:"
                        if (part.trim().startsWith("Talent or Gift:")) 
                        {
                            talents.add(part.substring(17).trim());
                        }
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            //Handle any IOException that occurs
            e.getMessage();
        }
        if (talents.isEmpty())
        {
            talents.add("No past talents or gifts found");
        }
        return talents;
    }
    
    //Method to add a talent or gift from a certain id
    public void addTalentAndGift(String id, String newTalentOrGift) 
    {
        try 
        {
            //Read the file into a List of Strings
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
            boolean isLineModified = false;
    
            //Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //Check if the current line contains the user ID and hasn't been modified yet
                if (line.contains(id) && !isLineModified) 
                {
                    //Modify the line by appending the new talent or gift
                    line = line + " Talent or Gift: " + newTalentOrGift + ",";
                    isLineModified = true;
                }
                lines.add(line);
            }
            reader.close();
    
            //Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(JOBHIST));
            for (String modifiedLine : lines) 
            {
                writer.write(modifiedLine);
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            //Handle any IOException that occurs
            e.getMessage();
        }
    }
    
    //Method to remove a talent or gift for a certain id
    public void removeTalentsAndGifts(String id, String talentOrGiftToRemove) 
    {
        try 
        {
            //Initialize a list to store the lines from the file
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(JOBHIST));
            String line;
    
            //Create a string that represents the talent or gift to remove
            String removeSkill = "Talent or Gift: " + talentOrGiftToRemove + ",";
    
            //Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //If the line contains the talent or gift to remove, replace it
                if (line.contains(removeSkill)) 
                {
                    line = line.replace(removeSkill, "").trim();
                }
                lines.add(line); //Add the (modified or unmodified) line to the list
            }
            reader.close();
    
            //Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(JOBHIST));
            for (String newLine : lines) 
            {
                writer.write(newLine);
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            //Handle any IOException that occurs
            e.getMessage();
        }
    }
}