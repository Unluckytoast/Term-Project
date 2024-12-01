import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.*;

class PersonalInformationGUI 
{
    private JPanel panel, parentPanel, jobListPanel, jobPanel, demoPanel, buttonPanel;
    private JLabel titleLabel, pastLabel, pastTimeLabel, currLabel, currTimeLabel, skillLabel, talentLabel;
    private JLabel demoNameLabel, demoRaceLabel, demoAgeLabel, demoAddressLabel, demoContactInfoLabel;
    private JButton backButton, editButton, deleteButton;
    private List<String> pastJobs, skills, talents;
    private JobHistory jobHistory = new JobHistory();
    private Employee emp;

    PersonalInformationGUI(JPanel parentPanel) 
    {
        this.parentPanel = parentPanel;

    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        titleLabel = new JLabel("Personal Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        jobListPanel = new JPanel(new GridBagLayout());
        jobListPanel.setBackground(Color.WHITE);
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobListPanel.add(createDemographicsPanel(), gridBag);

        String id = emp.getId();
        pastJobs = jobHistory.getPastJob(id);
        // Add job panels dynamically
        int row = 1;

        for (String job : pastJobs) 
        {
            gridBag.gridy = row;
            jobListPanel.add(createPastJobPanel(job), gridBag);
            row++;
        }
        
        String currentJob = jobHistory.getCurrentJob(id);

        jobListPanel.add(createCurrJobPanel(currentJob), gridBag);
        gridBag.gridy++;

        skills = jobHistory.getSkill(id);
        // Add job panels dynamically

        for (String skl : skills) 
        {
            gridBag.gridy = row;
            jobListPanel.add(createSkillPanel(skl), gridBag);
            row++;
        }

        talents = jobHistory.getTalentsAndGifts(id);
        // Add job panels dynamically

        for (String tal : talents) 
        {
            gridBag.gridy = row;
            jobListPanel.add(createTalentAndGiftPanel(tal), gridBag);
            row++;
        }

        // Wrap the job list in a scroll pane
        JScrollPane scrollPane = new JScrollPane(jobListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPastJobPanel(String job) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(Color.ORANGE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        String past[] = job.split(":");

        if(past.length > 2)
        {
            pastLabel = new JLabel(past[0]);
            pastLabel.setFont(labelFont);
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            jobPanel.add(pastLabel, gridBag);
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
            LocalDate startDate = LocalDate.parse(past[1].trim(), formatter);
            LocalDate endDate = LocalDate.parse(past[2].trim(), formatter);
    
            pastTimeLabel = new JLabel("Time in job - " + getDateDifference(startDate, endDate));
            pastTimeLabel.setFont(labelFont);
            gridBag.gridx = 0;
            gridBag.gridy = 1;
            jobPanel.add(pastTimeLabel, gridBag); 
        }
        else
        {
            pastLabel = new JLabel(past[0]);
            pastLabel.setFont(labelFont);
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            jobPanel.add(pastLabel, gridBag);
        }
        return jobPanel;
    }

    private JPanel createCurrJobPanel(String job) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(Color.ORANGE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        String current[] = job.split(":");
        Font labelFont = new Font("Arial", Font.PLAIN, 24);

        if(current.length > 1)
        {
            currLabel = new JLabel(current[1]);
            currLabel.setFont(labelFont);
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            jobPanel.add(pastLabel, gridBag);
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate startDate = LocalDate.parse(current[2].trim(), formatter);
            LocalDate endDate = LocalDate.now();
    
            currTimeLabel = new JLabel("Time in job - " + getDateDifference(startDate, endDate));
            currTimeLabel.setFont(labelFont);
            gridBag.gridx = 0;
            gridBag.gridy = 1;
            jobPanel.add(pastTimeLabel, gridBag);
        }
        else
        {
            currLabel = new JLabel(current[0]);
            currLabel.setFont(labelFont);
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            jobPanel.add(pastLabel, gridBag);
        }

        return jobPanel;
    }

    private JPanel createSkillPanel(String skill) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(Color.ORANGE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        skillLabel = new JLabel(skill);
        skillLabel.setFont(labelFont);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(skillLabel, gridBag);

        return jobPanel;
    }

    private JPanel createTalentAndGiftPanel(String talent) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(Color.ORANGE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        talentLabel = new JLabel(talent);
        talentLabel.setFont(labelFont);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(talentLabel, gridBag);

        return jobPanel;
    }

    public static String getDateDifference(LocalDate date1, LocalDate date2) 
    {
        // Compare the dates and calculate the difference
        if (date1.getYear() != date2.getYear()) 
        {
            // If the years are different, return the difference in years
            long yearsDifference = ChronoUnit.YEARS.between(date1, date2);
            return yearsDifference + " year(s)";
        } 
        else if (date1.getMonthValue() != date2.getMonthValue()) 
        {
            // If the years are the same but months are different, return the difference in months
            long monthsDifference = ChronoUnit.MONTHS.between(date1, date2);
            return monthsDifference + " month(s)";
        } 
        else 
        {
            // If the years and months are the same but days are different, return the difference in days
            long daysDifference = ChronoUnit.DAYS.between(date1, date2);
            return daysDifference + " day(s)";
        }
    }

    private JPanel createDemographicsPanel()
    {
        demoPanel = new JPanel(new GridBagLayout());
        demoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        demoPanel.setBackground(Color.CYAN);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        gridBag.anchor = GridBagConstraints.WEST; 

        if (emp != null && emp.getDemographics() != null)
        {
            Demographics demo = emp.getDemographics();
            if(demo.getName() != null)
            {
                demoNameLabel = new JLabel(demo.getName());
            }
            else
            {
                demoNameLabel = new JLabel("No name available");
            }
            if(demo.getRace() != null)
            {
                demoRaceLabel = new JLabel(demo.getRace());
            }
            else
            {
                demoRaceLabel = new JLabel("No race available");
            }
            int age = demo.getAge();
            String demoAge = "" + age;
            if(age != 0)
            {
                demoAgeLabel = new JLabel(demoAge);
            }
            else
            {
                demoNameLabel = new JLabel("No age available");
            }
            if(demo.getAddress() != null)
            {
                demoAddressLabel = new JLabel(demo.getAddress());
            }
            else
            {
                demoAddressLabel = new JLabel("No address available");
            }
            if(demo.getContactInfo() != null)
            {
                demoContactInfoLabel = new JLabel(demo.getContactInfo());
            }
            else
            {
                demoContactInfoLabel = new JLabel("No contact information available");
            }

            Font labelFont = new Font("Arial", Font.PLAIN, 24);
            demoNameLabel.setFont(labelFont);
            demoRaceLabel.setFont(labelFont);
            demoAgeLabel.setFont(labelFont);
            demoAddressLabel.setFont(labelFont);
            demoContactInfoLabel.setFont(labelFont);

            gridBag.gridx = 0;
            gridBag.gridy = 0;
            demoPanel.add(demoNameLabel, gridBag);
            
            gridBag.gridy = 1;
            demoPanel.add(demoAgeLabel, gridBag);
            
            gridBag.gridy = 2;
            demoPanel.add(demoRaceLabel, gridBag);
            
            gridBag.gridy = 3;
            demoPanel.add(demoAddressLabel, gridBag);
            
            gridBag.gridy = 4;
            demoPanel.add(demoContactInfoLabel, gridBag);
        }
        else
        {
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            demoNameLabel = new JLabel("No employee information available.");
            demoPanel.add(demoNameLabel, gridBag);
        }

        /*buttonPanel = new JPanel(new FlowLayout());
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        editButton.addActionListener(e -> 
        {
            // Action for edit button
            JOptionPane.showMessageDialog(jobPanel, "Edit job: " + job);
        });

        deleteButton.addActionListener(e -> 
        {
            // Action for delete button
            int response = JOptionPane.showConfirmDialog(jobPanel, "Are you sure you want to delete this job?");
            if (response == JOptionPane.YES_OPTION) {
                jobListPanel.remove(jobPanel);
                jobListPanel.updateUI();
                jobListPanel.revalidate();
                jobListPanel.repaint();
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    
        // Add buttonPanel to the jobPanel
        gridBag.gridx = 1;
        gridBag.gridy = 0;
        demoPanel.add(buttonPanel, gridBag);*/

        return demoPanel;
    }
    
    public void setPersonalInfoEmployee(Employee emp)
    {
        this.emp = emp;
    }

    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}

