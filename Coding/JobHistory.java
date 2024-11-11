import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JobHistory
{
    private static final String pastJobHist = "PastJobHistory.txt";
    File past = new File(pastJobHist);
    String currJobHis = "CurrentJobHistory.txt";
    File current = new File(currJobHis);
    String skills = "Skills.txt";
    File skl = new File(skills);
    String talentGift = "TalentsAndGifts.txt";
    File talGift = new File(talentGift);

    BufferedReader reader;
    BufferedWriter writer;
    
    public void viewPastJob(String id)
    {
        try
        {
            reader = new BufferedReader(new FileReader(past));
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
                    System.out.println("Past Job for user " + id + ":");
                    for (String part: jobs)
                    {
                        if (part.trim().startsWith("PJ:"))
                        {
                            System.out.println(part.substring(5).trim());
                        }
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }

    public void addPastJob(String id, String newPastJob, int startTimeJob, int endTimeJob)
    {
        try 
        {
            // Step 1: Read the file into a List of Strings
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(past));
            String line;
            boolean isLineModified = false;
            int totalTimeInJob = endTimeJob - startTimeJob;

            while ((line = reader.readLine()) != null) {
                // Step 2: Check if the current line contains the search text
                if (line.contains(id.substring(3)) && !isLineModified) {
                    // Step 3: Modify the line by appending or prepending the new content
                    line = line + " PJ: " + newPastJob + ",";  // You can prepend by doing: textToAdd + line
                    if(totalTimeInJob > 0)
                    {
                        line = line + " Time in Job: " + totalTimeInJob + ",";
                    }
                    else
                    {
                        line = line + " Time in Job: Less than a year,";
                    }
                    isLineModified = true; // Modify only the first occurrence
                }
                lines.add(line); // Add each line (modified or not) to the list
            }
            //reader.close();

            // Step 4: Write the modified content back to the file
            writer = new BufferedWriter(new FileWriter(past));
            for (String modifiedLine : lines) {
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

    public void removePastJob(String id, String jobToRemove, int startYear, int endYear)
    {
        try
        {
            List<String> lines = new ArrayList<>();
            reader = new BufferedReader(new FileReader(past));
            String line;
            String jobWithTime;
            if(endYear > startYear)
            {
                int totalTimeInJob = endYear - startYear;
                jobWithTime = " PJ: " + jobToRemove + ", Time in Job: " + totalTimeInJob + ",";
            }
            else
            {
                String totalTime = "Less than a year";
                jobWithTime = " PJ: " + jobToRemove + ", Time in Job: " + totalTime + ",";
            }
            //boolean foundLine = false;

            System.out.println("job with time to remove: '" + jobWithTime + "'");
            while((line = reader.readLine()) != null)
            {
                if (line.contains(jobWithTime)) {
                    // Remove both "PJ: jobToRemove" and "Time in Job: totalTimeInJob" in one go
                    line = line.replace(jobWithTime, "").trim();
                    System.out.println("Modified line: '" + line + "'");
                }
                lines.add(line);
            }

            writer = new BufferedWriter(new FileWriter(past));
            for(String newLine : lines)
            {
                writer.write(newLine);
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }
}