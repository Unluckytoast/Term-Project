import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.*;

class PersonalInformationGUI 
{
    private JPanel panel, parentPanel, jobListPanel, jobPanel, demoPanel;//, buttonPanel;
    private JLabel titleLabel;
    private JLabel demoNameLabel, demoRaceLabel, demoAgeLabel, demoAddressLabel, demoContactInfoLabel;
    private JButton backButton; //, editButton, deleteButton;
    private List<String> pastJobs, skills, talents;
    private JobHistory jobHistory = new JobHistory();
    private Employee emp;
    private JTextArea pastTextArea, currTextArea, pastTimeTextArea, currTimeTextArea, skillTextArea, talentTextArea;
    private Dimension dimension = new Dimension(450, 100);
    private HoldFont hold = new HoldFont();
    private Font font = hold.getTextFont();

    PersonalInformationGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;

    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        titleLabel = new JLabel("Personal Information", SwingConstants.CENTER);
        titleLabel.setFont(hold.getTitleFont());
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

        gridBag.gridy = gridBag.gridy + 1;
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
        String past[] = job.split(":");

        if(past.length > 2)
        {
            pastTextArea = new JTextArea(past[0]);
            pastTextArea.setFont(font);
            pastTextArea.setLineWrap(true);
            pastTextArea.setWrapStyleWord(true);
            pastTextArea.setEditable(false);
            pastTextArea.setPreferredSize(dimension);
            pastTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            jobPanel.add(pastTextArea, gridBag);
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
            LocalDate startDate = LocalDate.parse(past[1].trim(), formatter);
            LocalDate endDate = LocalDate.parse(past[2].trim(), formatter);
    
            pastTimeTextArea = new JTextArea("Time in job - " + getDateDifference(startDate, endDate));
            pastTimeTextArea.setFont(font);
            gridBag.gridy = 1;
            jobPanel.add(pastTimeTextArea, gridBag); 
        }
        else
        {
            pastTextArea = new JTextArea(past[0]);
            pastTextArea.setFont(font);
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            jobPanel.add(pastTextArea, gridBag);
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

        currTextArea = new JTextArea(current[0]);
        currTextArea.setFont(font);
        currTextArea.setLineWrap(true);
        currTextArea.setWrapStyleWord(true);
        currTextArea.setEditable(false);
        currTextArea.setPreferredSize(dimension);
        currTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(currTextArea, gridBag);

        if(current.length > 1)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate startDate = LocalDate.parse(current[1].trim(), formatter);
            LocalDate endDate = LocalDate.now();
    
            currTimeTextArea = new JTextArea("Time in job - " + getDateDifference(startDate, endDate));
            currTimeTextArea.setFont(font);
            gridBag.gridy = 1;
            jobPanel.add(currTimeTextArea, gridBag);
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

        skillTextArea = new JTextArea(skill);
        skillTextArea.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(skillTextArea, gridBag);


        return jobPanel;
    }

    private JPanel createTalentAndGiftPanel(String talent) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(Color.ORANGE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        talentTextArea = new JTextArea(talent);
        talentTextArea.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(talentTextArea, gridBag);

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

            demoNameLabel.setFont(font);
            demoRaceLabel.setFont(font);
            demoAgeLabel.setFont(font);
            demoAddressLabel.setFont(font);
            demoContactInfoLabel.setFont(font);

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

    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}

