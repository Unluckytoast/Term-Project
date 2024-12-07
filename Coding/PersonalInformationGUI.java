import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;

class PersonalInformationGUI 
{
    private JPanel panel, parentPanel, jobListPanel, jobPanel, demoPanel;
    private JLabel titleLabel, currJobLabel, pastJobLabel, skillLabel, talentLabel;
    private JLabel demoNameLabel, demoRaceLabel, demoAgeLabel, demoAddressLabel, demoContactInfoLabel;
    private JButton backButton;
    private List<String> pastJobs, skills, talents;
    private JobHistory jobHistory = new JobHistory();
    private Employee emp;
    private JTextArea pastTextArea, currTextArea, skillTextArea, talentTextArea;
    private Dimension dimension = new Dimension(200, 75);
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();
    private Color themeBlue = Color.decode("#2A5490"); 

    //Constructor
    PersonalInformationGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    //Create Personal Information Panel
    public JPanel createPanel() 
    {
        //Make panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        //Create title label
        titleLabel = new JLabel("Personal Information", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        //Create job list panel to add job information to
        jobListPanel = new JPanel(new GridBagLayout());
        jobListPanel.setBackground(Color.WHITE); // White background for job list panel
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        //Add demographics to the job list panel
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobListPanel.add(createDemographicsPanel(), gridBag);

        String id = emp.getId();

        // Add current job to the job list panel
        String currentJob = jobHistory.getCurrentJob(id);

        gridBag.gridy = 1;
        jobListPanel.add(createCurrJobPanel(currentJob), gridBag);
        int row = 2;

        // Add past job panels to the job list panel dynamically
        pastJobs = jobHistory.getPastJob(id);

        for (String job : pastJobs) 
        {
            gridBag.gridy = row;
            jobListPanel.add(createPastJobPanel(job), gridBag);
            row++;
        }

        // Add skill panels to job list panel dynamically
        skills = jobHistory.getSkill(id);

        for (String skl : skills) 
        {
            gridBag.gridy = row;
            jobListPanel.add(createSkillPanel(skl), gridBag);
            row++;
        }

        // Add talent panels to job list panel dynamically
        talents = jobHistory.getTalentsAndGifts(id);

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

        // Create a new back button
        backButton = new JButton("Back");
        backButton.setBackground(themeBlue);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDemographicsPanel()
    {
        //Create demo panel to return
        demoPanel = new JPanel(new GridBagLayout());
        demoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        demoPanel.setBackground(themeBlue); // Background color for demographics section

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        //Add name of employee to demoPanel
        demoNameLabel = new JLabel("Name: " + emp.getDemographics().getName());
        demoNameLabel.setFont(font);
        demoNameLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        demoPanel.add(demoNameLabel, gridBag);

        //Add race of employee to demoPanel
        demoRaceLabel = new JLabel("Race: " + emp.getDemographics().getRace());
        demoRaceLabel.setFont(font);
        demoRaceLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        demoPanel.add(demoRaceLabel, gridBag);

        //Add age of employee to demoPanel
        demoAgeLabel = new JLabel("Age: " + emp.getDemographics().getAge());
        demoAgeLabel.setFont(font);
        demoAgeLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        demoPanel.add(demoAgeLabel, gridBag);

        //Add address of employee to demoPanel
        demoAddressLabel = new JLabel("Address: " + emp.getDemographics().getAddress());
        demoAddressLabel.setFont(font);
        demoAddressLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        demoPanel.add(demoAddressLabel, gridBag);

        //Add contact information of employee to demoPanel
        demoContactInfoLabel = new JLabel("Contact Info: " + emp.getDemographics().getContactInfo());
        demoContactInfoLabel.setFont(font);
        demoContactInfoLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        demoPanel.add(demoContactInfoLabel, gridBag);
        
        //Set demoPanel size
        int height = demoPanel.getPreferredSize().height + 25;
        int width = demoPanel.getPreferredSize().height + 75;
        demoPanel.setPreferredSize(new Dimension(width, height));

        return demoPanel;
    }

    //Method to create current job panel
    private JPanel createCurrJobPanel(String job) 
    {
        //Create current job panel to return
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        String current[] = job.split(":");

        //Create current job label and add it to current job panel
        currJobLabel = new JLabel("Current Job:");
        currJobLabel.setFont(font);
        currJobLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        jobPanel.add(currJobLabel, gridBag);

        //Create current job text area and add it to the current job panel
        //Set area to where it cannot be edited. Also set
        //text area to where line can wrap to the next line.
        currTextArea = new JTextArea(2, 18);
        currTextArea.setText(current[0]);
        currTextArea.setFont(font);
        currTextArea.setLineWrap(true);
        currTextArea.setEditable(false);
        currTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gridBag.gridx++;

        //If current[] is longer than one, it will add a time in job to the text area
        if(current.length > 1)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate startDate = LocalDate.parse(current[1].trim(), formatter);
            LocalDate endDate = LocalDate.now();
    
            currTextArea.append("\nTime in job - " + repeat.getDateDifference(startDate, endDate));
        }

        //Add the text area to the current job panel
        jobPanel.add(currTextArea, gridBag);

        return jobPanel;
    }

    //Method to create past job panel(s)
    private JPanel createPastJobPanel(String job) 
    {
        //Create past job panel to return
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        //Create past job label and add it to the past job panel
        pastJobLabel = new JLabel("Past Job:");
        pastJobLabel.setFont(font);
        pastJobLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        jobPanel.add(pastJobLabel, gridBag);

        //Create past job text area
        String past[] = job.split(":");
        pastTextArea = new JTextArea(2, 18);
        pastTextArea.setLineWrap(true);
        pastTextArea.setFont(font);
        pastTextArea.setWrapStyleWord(true);
        pastTextArea.setEditable(false);
        pastTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gridBag.gridx++;

        //If past length is longer than two, it will get start
        //and end date and show how long that job was worked
        //Add the past text area to the job panel
        if(past.length > 2)
        {
            pastTextArea.setText(past[0]);
            jobPanel.add(pastTextArea, gridBag);
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
            LocalDate startDate = LocalDate.parse(past[1].trim(), formatter);
            LocalDate endDate = LocalDate.parse(past[2].trim(), formatter);
    
            pastTextArea.append("\nTime in job - " + repeat.getDateDifference(startDate, endDate));
            jobPanel.add(pastTextArea, gridBag); 
        }
        //This will return that there is no past job
        else
        {
            pastTextArea.setText(past[0]);
            pastTextArea.setFont(font);
            jobPanel.add(pastTextArea, gridBag);
        }

        return jobPanel;
    }

    //Method to create skill panel(s)
    private JPanel createSkillPanel(String skill) 
    {
        //Create skill panel to return
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        //Create skill label and add it to the skill panel
        skillLabel = new JLabel("Skill:");
        skillLabel.setForeground(Color.WHITE);
        skillLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        jobPanel.add(skillLabel, gridBag);

        //Create skill text area and add it to the panel
        skillTextArea = new JTextArea(2, 18);
        skillTextArea.setLineWrap(true);
        skillTextArea.setFont(font);
        skillTextArea.setWrapStyleWord(true);
        skillTextArea.setEditable(false);
        skillTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        skillTextArea.setText(skill);
        gridBag.gridx++;
        jobPanel.add(skillTextArea, gridBag);

        return jobPanel;
    }

    //Method to create talent and gift panel(s)
    private JPanel createTalentAndGiftPanel(String talent) 
    {
        //Create talent and gift panel to return
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        //Create talent label and add it to the talent panel
        talentLabel = new JLabel("Talent:");
        talentLabel.setFont(font);
        talentLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        jobPanel.add(talentLabel, gridBag);

        //Create talent text area and add it to the talent panel
        talentTextArea = new JTextArea(2, 18);
        talentTextArea.setLineWrap(true);
        talentTextArea.setFont(font);
        talentTextArea.setWrapStyleWord(true);
        talentTextArea.setEditable(false);
        talentTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        talentTextArea.setText(talent);
        gridBag.gridx++;
        jobPanel.add(talentTextArea, gridBag);

        return jobPanel;
    }
}

