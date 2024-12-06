import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Dimension dimension = new Dimension(200, 100);
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();
    private WriteJobSatisfactionGUI writeJob;

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
        titleLabel.setFont(repeat.getTitleFont());
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

        gridBag.gridy = row;
        jobListPanel.add(createCurrJobPanel(currentJob), gridBag);
        row++;
        gridBag.gridy = row;

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
        pastTextArea = new JTextArea(2, 18);
        pastTextArea.setLineWrap(true);
        pastTextArea.setFont(font);
        pastTextArea.setWrapStyleWord(true);
        pastTextArea.setEditable(false);
        pastTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pastTextArea.setPreferredSize(dimension);

        if(past.length > 2)
        {
            pastTextArea.setText(past[0]);
            gridBag.gridx = 0;
            gridBag.gridy = 0;
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

        currTextArea = new JTextArea(2, 18);
        currTextArea.setText(current[0]);
        currTextArea.setFont(font);
        currTextArea.setLineWrap(true);
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
    
            currTextArea.append("\nTime in job - " + repeat.getDateDifference(startDate, endDate));
        }

        jobPanel.add(currTextArea, gridBag);

        return jobPanel;
    }

    private JPanel createSkillPanel(String skill) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setBackground(Color.ORANGE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        skillTextArea = new JTextArea(2, 18);
        skillTextArea.setLineWrap(true);
        skillTextArea.setFont(font);
        skillTextArea.setWrapStyleWord(true);
        skillTextArea.setEditable(false);
        skillTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        skillTextArea.setPreferredSize(dimension);
        skillTextArea.setText(skill);
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

        talentTextArea = new JTextArea(2, 18);
        talentTextArea.setLineWrap(true);
        talentTextArea.setFont(font);
        talentTextArea.setWrapStyleWord(true);
        talentTextArea.setEditable(false);
        talentTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        talentTextArea.setPreferredSize(dimension);
        talentTextArea.setText(talent);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(talentTextArea, gridBag);

        return jobPanel;
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
        return demoPanel;
    }

    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}

