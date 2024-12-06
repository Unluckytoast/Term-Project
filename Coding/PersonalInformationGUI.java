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
    private JTextArea pastTextArea, currTextArea, pastTimeTextArea, currTimeTextArea, skillTextArea, talentTextArea;
    private Dimension dimension = new Dimension(200, 75);
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();
    private WriteJobSatisfactionGUI writeJob;
    private Color themeBlue = Color.decode("#2A5490"); // Sea Fun theme color

    PersonalInformationGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // Light background for contrast

        titleLabel = new JLabel("Personal Information", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        jobListPanel = new JPanel(new GridBagLayout());
        jobListPanel.setBackground(Color.WHITE); // White background for job list panel
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobListPanel.add(createDemographicsPanel(), gridBag);

        String id = emp.getId();

        // Add job panels dynamically
        int row = 1;
        String currentJob = jobHistory.getCurrentJob(id);

        gridBag.gridy = row;
        jobListPanel.add(createCurrJobPanel(currentJob), gridBag);
        row++;
        gridBag.gridy = row;

        pastJobs = jobHistory.getPastJob(id);
        // Add job panels dynamically

        for (String job : pastJobs) 
        {
            gridBag.gridy = row;
            jobListPanel.add(createPastJobPanel(job), gridBag);
            row++;
        }

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
        backButton.setBackground(themeBlue); // Set the back button background to theme blue
        backButton.setForeground(Color.WHITE); // Set button text to white
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDemographicsPanel()
    {
        demoPanel = new JPanel(new GridBagLayout());
        demoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        demoPanel.setBackground(themeBlue); // Background color for demographics section

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        demoNameLabel = new JLabel("Name: " + emp.getDemographics().getName());
        demoNameLabel.setFont(font);
        demoNameLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        demoPanel.add(demoNameLabel, gridBag);

        demoRaceLabel = new JLabel("Race: " + emp.getDemographics().getRace());
        demoRaceLabel.setFont(font);
        demoRaceLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        demoPanel.add(demoRaceLabel, gridBag);

        demoAgeLabel = new JLabel("Age: " + emp.getDemographics().getAge());
        demoAgeLabel.setFont(font);
        demoAgeLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        demoPanel.add(demoAgeLabel, gridBag);

        demoAddressLabel = new JLabel("Address: " + emp.getDemographics().getAddress());
        demoAddressLabel.setFont(font);
        demoAddressLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        demoPanel.add(demoAddressLabel, gridBag);

        demoContactInfoLabel = new JLabel("Contact Info: " + emp.getDemographics().getContactInfo());
        demoContactInfoLabel.setFont(font);
        demoContactInfoLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        
        demoPanel.add(demoContactInfoLabel, gridBag);
        int height = demoPanel.getPreferredSize().height + 25;
        int width = demoPanel.getPreferredSize().height + 75;
        demoPanel.setPreferredSize(new Dimension(width, height));

        return demoPanel;
    }

    private JPanel createCurrJobPanel(String job) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        String current[] = job.split(":");

        currJobLabel = new JLabel("Current Job:");
        currJobLabel.setFont(font);
        currJobLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        jobPanel.add(currJobLabel, gridBag);

        currTextArea = new JTextArea(1, 18);
        currTextArea.setText(current[0]);
        currTextArea.setFont(font);
        currTextArea.setLineWrap(true);
        currTextArea.setEditable(false);
        currTextArea.setPreferredSize(dimension);
        currTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gridBag.gridx++;
        jobPanel.add(currTextArea, gridBag);

        if(current.length > 1)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate startDate = LocalDate.parse(current[1].trim(), formatter);
            LocalDate endDate = LocalDate.now();
    
            currTextArea.append("\nTime in job - " + repeat.getDateDifference(startDate, endDate));
        }

        jobPanel.add(currTextArea, gridBag);

        return jobPanel;
    }

    private JPanel createPastJobPanel(String job) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue); // Change to a more neutral color for job panel

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        pastJobLabel = new JLabel("Past Job:");
        pastJobLabel.setFont(font);
        pastJobLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        jobPanel.add(pastJobLabel, gridBag);

        String past[] = job.split(":");
        pastTextArea = new JTextArea(2, 18);
        pastTextArea.setLineWrap(true);
        pastTextArea.setFont(font);
        pastTextArea.setWrapStyleWord(true);
        pastTextArea.setEditable(false);
        pastTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pastTextArea.setPreferredSize(dimension);
        gridBag.gridx++;

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
        else
        {
            pastTextArea.setText(past[0]);
            pastTextArea.setFont(font);
            jobPanel.add(pastTextArea, gridBag);
        }

        return jobPanel;
    }

    private JPanel createSkillPanel(String skill) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        skillLabel = new JLabel("Skill");
        skillLabel.setForeground(Color.WHITE);
        skillLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(skillLabel, gridBag);

        skillTextArea = new JTextArea(2, 18);
        skillTextArea.setLineWrap(true);
        skillTextArea.setFont(font);
        skillTextArea.setWrapStyleWord(true);
        skillTextArea.setEditable(false);
        skillTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        skillTextArea.setPreferredSize(dimension);
        skillTextArea.setText(skill);
        gridBag.gridx++;
        jobPanel.add(skillTextArea, gridBag);

        return jobPanel;
    }

    private JPanel createTalentAndGiftPanel(String talent) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(themeBlue);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        talentLabel = new JLabel("Talent:");
        talentLabel.setFont(font);
        talentLabel.setForeground(Color.WHITE);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(talentLabel, gridBag);

        talentTextArea = new JTextArea(2, 18);
        talentTextArea.setLineWrap(true);
        talentTextArea.setFont(font);
        talentTextArea.setWrapStyleWord(true);
        talentTextArea.setEditable(false);
        talentTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        talentTextArea.setPreferredSize(dimension);
        talentTextArea.setText(talent);
        gridBag.gridx++;
        jobPanel.add(talentTextArea, gridBag);

        return jobPanel;
    }

    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}

