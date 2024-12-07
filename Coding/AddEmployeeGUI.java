import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import javax.swing.*;

public class AddEmployeeGUI 
{
    private JPanel parentPanel, panel, addEmpPanel, pastPanel, skillPanel, talentPanel, messagePanel;
    private JLabel titleLabel, nameLabel, raceLabel, ageLabel, addressLabel, phoneNumberLabel, currJobDeptLabel, currJobTitleLabel;
    private JLabel pastJobLabel, pastJobStartLabel, pastJobEndLabel, skillLabel, talentLabel, messageLabel;
    private JTextField nameText, raceText, ageText, addressText, phoneNumberText, currJobDeptText, currJobTitleText;
    private JTextField pastJobText, pastJobStartDateText, pastJobEndDateText, skillText, talentText;
    private JButton backButton, saveButton;
    private boolean isAddressValid, isPhoneNumValid;
    private LocalDate pastStartDate, pastEndDate, currDate;
    private Employee emp, newEmployee;
    private Demographics newDemo;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();
    private GridBagConstraints gridBag = new GridBagConstraints();
    
    //Constructor
    public AddEmployeeGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    //Create Add Employee Panel
    public JPanel createPanel()
    {
        //Make panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        //Create title label
        titleLabel = new JLabel("Manage Employees", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);
        gridBag.insets = new Insets(10, 10, 10, 10);

        //Create panel to add everything else to,
        //so that this panel can be easily added to "panel" later
        addEmpPanel = new JPanel(new GridBagLayout());

        //Create Name label
        Dimension dimension = new Dimension(200, 30);
        nameLabel = new JLabel("* Name:");
        nameLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        addEmpPanel.add(nameLabel, gridBag);

        //Create Name text field
        nameText = new JTextField(15);
        nameText.setFont(font);
        nameText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(nameText, gridBag);

        //Create Race label
        raceLabel = new JLabel("* Race:");
        raceLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(raceLabel, gridBag);

        //Create Race text field
        raceText = new JTextField(15);
        raceText.setFont(font);
        raceText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(raceText, gridBag);

        //Create Age label
        ageLabel = new JLabel("* Age:");
        ageLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(ageLabel, gridBag);

        //Create Age text field
        ageText = new JTextField(15);
        ageText.setFont(font);
        ageText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(ageText, gridBag);

        //Create Address label
        addressLabel = new JLabel("* Address");
        addressLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(addressLabel, gridBag);

        //Create Address text field
        addressText = new JTextField(15);
        addressText.setFont(font);
        addressText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(addressText, gridBag);

        //Create Phone Number label
        phoneNumberLabel = new JLabel("* Phone Number:");
        phoneNumberLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(phoneNumberLabel, gridBag);

        //Create Phone Number text field
        phoneNumberText = new JTextField(15);
        phoneNumberText.setFont(font);
        phoneNumberText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(phoneNumberText, gridBag);

        //Create Current Job Department label
        currJobDeptLabel = new JLabel("* Current Job Department:");
        currJobDeptLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(currJobDeptLabel, gridBag);

        //Create Current Job Department text field
        currJobDeptText = new JTextField(15);
        currJobDeptText.setFont(font);
        currJobDeptText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(currJobDeptText, gridBag);

        //Create Current Job Title label
        currJobTitleLabel = new JLabel("* Current Job Title:");
        currJobTitleLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(currJobTitleLabel, gridBag);

        //Create Current Job text field
        currJobTitleText = new JTextField(15);
        currJobTitleText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(currJobTitleText, gridBag);

        //Create Past Job label
        pastJobLabel = new JLabel("Past Job:");
        pastJobLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(pastJobLabel, gridBag);

        //Create Past Job text field
        pastJobText = new JTextField(15);
        pastJobText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(pastJobText, gridBag);

        //Create Past Job Start Date label
        pastJobStartLabel = new JLabel("Past Job Start Date: yyyy/MM/dd");
        pastJobStartLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(pastJobStartLabel, gridBag);

        //Create Past Job Start Date text field
        pastJobStartDateText = new JTextField(15);
        pastJobStartDateText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(pastJobStartDateText, gridBag);

        //Create Past Job End Date label
        pastJobEndLabel = new JLabel("Past Job End Date: yyyy/MM/dd");
        pastJobEndLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(pastJobEndLabel, gridBag);

        //Create Past Job End Date text field
        pastJobEndDateText = new JTextField(15);
        pastJobEndDateText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(pastJobEndDateText, gridBag);

        //Create Skill label
        skillLabel = new JLabel("Skill:");
        skillLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(skillLabel, gridBag);

        //Create Skill text field
        skillText = new JTextField(15);
        skillText.setFont(font);
        skillText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(skillText, gridBag);

        //Create Talent or Gift label
        talentLabel = new JLabel("Talent or Gift:");
        talentLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(talentLabel, gridBag);

        //Create Talent or Gift text field
        talentText = new JTextField(15);
        talentText.setFont(font);
        talentText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(talentText, gridBag);

        //Create Save Button with Action Listener
        saveButton = new JButton("Save");
        saveButton.setBackground(Color.decode("#2A5490"));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(font);
        saveButton.addActionListener(e -> 
        {
            //Validator - Makes sure that Name text field is not empty or null
            if(nameText.getText() != null && !nameText.getText().isEmpty())
            {
                //Validator - Makes sure that Race text field is not empty or null
                if (raceText.getText() != null && !raceText.getText().isEmpty())
                {
                    //Validator - Makes sure that Age text field is not null, empty, or too young or too old
                    String ageTextInput = ageText.getText();
                    try 
                    {
                        int age = Integer.parseInt(ageTextInput);
                        //If age is too small
                        if (age < 16)
                        {
                            messageLabel.setText("The worker is too young");
                        }
                        //If age is too big
                        else if (age > 101)
                        {
                            messageLabel.setText("The number in the age textbox is too large");
                        }
                        //If age fits between 16 and 101
                        else
                        {
                            //Validator - Makes sure that Address is a valid address
                            isAddressValid = repeat.isValidAddress(addressText.getText());
                            if (isAddressValid)
                            {
                                //Validator - Makes sure that Phone Number is a Valid phone number
                                isPhoneNumValid = repeat.isValidPhoneNumber(phoneNumberText.getText());
                                if (isPhoneNumValid)
                                {
                                    //Validator - Makes sure that Current Job Department is not empty or null
                                    if (currJobDeptText.getText() != null && !currJobDeptText.getText().isEmpty())
                                    {
                                        //Creates a new Employee in employeeData.txt with the Demographics
                                        newDemo = new Demographics(nameText.getText(), raceText.getText(), age, addressText.getText(), phoneNumberText.getText());
                                        HR hr = new HR();
                                        String id = hr.addEmployee(newDemo, currJobTitleText.getText(), currJobDeptText.getText());
                                    
                                        //Creates a new Employee in jobHistory.txt to track past job, skills, and talents
                                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("JobHistory.txt", true)))
                                        {
                                            //Puts id: with new id on a new line
                                            writer.write("\nid: " + id + ",");
                                            writer.close();

                                            currDate = LocalDate.now();
                                            JobHistory history = new JobHistory();

                                            //Adds current job to jobHistory.txt to track the start date
                                            history.addCurrentJob(id, currJobTitleText.getText(), currDate);

                                            //Validator - Checks to see if Past Job is empty or null
                                            //If not, it will get Past Job, and that job's Start and End Date
                                            if (pastJobText.getText() != null && !pastJobText.getText().isEmpty())
                                            {
                                                //Validator - Makes sure that Start Date and End Date are not empty, null, or invalid dates
                                                if (!pastJobStartDateText.getText().isEmpty() && !pastJobEndDateText.getText().isEmpty())
                                                {
                                                    //Validator - Makes sure that the date format matches what is needed to be inputted
                                                    boolean startDateMatches = isDateInputValid(pastJobStartDateText.getText());
                                                    boolean endDateMatches = isDateInputValid(pastJobEndDateText.getText());

                                                    //If both dates match the format, it will check the dates 
                                                    //to make sure that the End date is not before the Start date
                                                    if (startDateMatches && endDateMatches)
                                                    {
                                                        //Gets the past job date text fields and turns them into dates
                                                        pastStartDate = LocalDate.parse(pastJobStartDateText.getText(), formatter);
                                                        pastEndDate = LocalDate.parse(pastJobEndDateText.getText(), formatter);
        
                                                        boolean dateCheck = isDateDifferenceValid();
        
                                                        //Validator - Makes sure that end date is before start date
                                                        if (dateCheck)
                                                        {
                                                            //Adds the past job to the jobHistory.txt, and outputs new id
                                                            history.addPastJob(id, pastJobText.getText(), pastStartDate, pastEndDate);
                                                            messageLabel.setText("New Employee with id " + id + " has been saved!");
                                                        }
                                                        else
                                                        {
                                                            //Gives an error message to the user that means that the dates overlap
                                                            messageLabel.setText("The date for past job are either incorrect or intefer with each other");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        //Gives a error message that one or both of the dates have invalid formats,
                                                        //or that the dates have values like (month = 14) that don't exist on a calendar
                                                        messageLabel.setText("The date(s) have (a) invalid format(s).");
                                                    }
                                                }
                                                else
                                                {
                                                    //Gives an error message that the past job end/start date field(s) are empty
                                                    messageLabel.setText("Past Job requires a start date and/or an end date");
                                                }
                                            }
                                            else
                                            {
                                                //Adds employee without a past job, and outputs new id
                                                messageLabel.setText("New Employee with id " + id + " has been saved!");  
                                            }
                                            //Validator - Checks to see if Skill text is not empty or null
                                            if (skillText.getText() != null && !skillText.getText().isEmpty())
                                            {
                                                //Adds skill to the jobHistory.txt
                                                history.addSkill(id, skillText.getText());
                                            }
                                            //Validator - Checks to see if Talent text is not empty or null
                                            if (talentText.getText() != null & !talentText.getText().isEmpty())
                                            {
                                                //Adds talent to the jobHistory.txt
                                                history.addTalentAndGift(id, talentText.getText());
                                            }
                                        }
                                        //Catches error when trying to write to the job history file
                                        catch (IOException r)
                                        {
                                            r.getMessage();
                                        }
                                    }
                                    //Gives error message when the current job field is empty
                                    else
                                    {
                                        messageLabel.setText("The current job field cannot be empty");
                                    }
                                }
                                //Gives error message when the phone number field is either empty, null, or too big/small
                                else
                                {
                                    messageLabel.setText("The phone number is invalid");
                                }
                            }
                            //Gives error message when the address field is either empty, null, or incorrect format
                            else
                            {
                                messageLabel.setText("The address is not valid");
                            }
                        } 
                    }
                    //If it's not a valid number, show an error message
                    catch (NumberFormatException ex) 
                    {
                        messageLabel.setText("Age must be a valid number between 16 and 101");
                    }
                }
                //Gives error message when race field is empty
                else
                {
                    messageLabel.setText("Race field cannont be empty");
                }
            }
            //Gives error message when name field is empty
            else
            {
                messageLabel.setText("Name field cannot be empty");
            }
        });

        //Adds save button to the addEmpPanel
        gridBag.gridy++;
        addEmpPanel.add(saveButton, gridBag);

        //Creates message label
        messageLabel = new JLabel("* marks Required Fields");
        messageLabel.setForeground(Color.decode("#2A5490"));
        messageLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        gridBag.gridwidth = 2;
        gridBag.anchor = GridBagConstraints.CENTER;
        addEmpPanel.add(messageLabel, gridBag);

        //Resets the gridbag to have a width of one
        //Just in case
        gridBag.gridwidth = 1;
        gridBag.anchor = GridBagConstraints.WEST;
             
        //Creates a Scroll Pane using the addEmpPanel
        JScrollPane scrollPane = new JScrollPane(addEmpPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        //Creates the back button
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));// Blue Background
        backButton.setForeground(Color.WHITE);  // White text
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "ManageEmps"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    //Method to check if a string with a date matches the needed format
    private boolean isDateInputValid(String dateText) 
    {
        try 
        {
            // Attempt to parse the date
            LocalDate.parse(dateText, formatter);
            // The format matches
            return true;
        } 
        catch (DateTimeParseException e) 
        {
            // The format doesn't match
            return false;
        }
    }

    //Method to check if a string with a date matches the needed format
    private boolean isDateDifferenceValid()
    {
        //Trys to compare the past job start date and end date
        try
        {
            int differenceYear = pastEndDate.getYear() - pastStartDate.getYear();

            //Returns true if the start year is smaller than end year
            if ((differenceYear > 0))
            {
                return true;
            }
            //If the year is the same, checks the months
            else if ((differenceYear == 0))
            {
                int differenceMonth = pastEndDate.getMonthValue() - pastStartDate.getMonthValue();

                //Returns true if the start month is smaller than end month
                if (differenceMonth > 0)
                {
                    return true;
                }
                //If the month is the same, checks the day
                else if (differenceMonth == 0)
                {
                    long daysDifference = ChronoUnit.DAYS.between(pastStartDate, pastEndDate);
                    //Returns true if the start day is before the end day
                    if (daysDifference > 0)
                    {
                        return true;
                    }
                    //Returns false if the start day is before the end day
                    else
                    {
                        return false;
                    }
                }
                //Returns false if the start month is after the end month
                else 
                {
                    return false;
                }
            }
            //Returns false if the start year is bigger than the end year
            else
            {
                return false;
            }
        }
        //Catches error for comparing dates, returns false, and inputs error to panel
        catch(DateTimeParseException e)
        {
            messageLabel.setText("Invalid date format. Please use yyyy/MM/dd.");
            return false;
        }
    }
}