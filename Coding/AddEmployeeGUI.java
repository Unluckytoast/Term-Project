import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;
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
    private HoldFont hold = new HoldFont();
    private Font font = hold.getTextFont();
    private GridBagConstraints gridBag = new GridBagConstraints();
    
    public AddEmployeeGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel()
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.YELLOW);

        titleLabel = new JLabel("Manage Employees", SwingConstants.CENTER);
        titleLabel.setFont(hold.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);
        gridBag.insets = new Insets(10, 10, 10, 10);

        addEmpPanel = new JPanel(new GridBagLayout());

        Dimension dimension = new Dimension(200, 30);
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        addEmpPanel.add(nameLabel, gridBag);

        nameText = new JTextField(15);
        nameText.setFont(font);
        //nameText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(nameText, gridBag);

        raceLabel = new JLabel("Race:");
        raceLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(raceLabel, gridBag);

        raceText = new JTextField(15);
        raceText.setFont(font);
        raceText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(raceText, gridBag);

        ageLabel = new JLabel("Age:");
        ageLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(ageLabel, gridBag);

        ageText = new JTextField(15);
        ageText.setFont(font);
        ageText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(ageText, gridBag);

        addressLabel = new JLabel("Address");
        addressLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(addressLabel, gridBag);

        addressText = new JTextField(15);
        addressText.setFont(font);
        addressText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(addressText, gridBag);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(phoneNumberLabel, gridBag);

        phoneNumberText = new JTextField(15);
        phoneNumberText.setFont(font);
        phoneNumberText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(phoneNumberText, gridBag);

        currJobDeptLabel = new JLabel("Current Job Department:");
        currJobDeptLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(currJobDeptLabel, gridBag);

        currJobDeptText = new JTextField(15);
        currJobDeptText.setFont(font);
        currJobDeptText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(currJobDeptText, gridBag);

        currJobTitleLabel = new JLabel("Current Job Title:");
        currJobTitleLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(currJobTitleLabel, gridBag);

        currJobTitleText = new JTextField(15);
        currJobTitleText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(currJobTitleText, gridBag);

        pastJobLabel = new JLabel("Past Job:");
        pastJobLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(pastJobLabel, gridBag);

        pastJobText = new JTextField(15);
        pastJobText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(pastJobText, gridBag);

        pastJobStartLabel = new JLabel("Past Job Start Date:");
        pastJobStartLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(pastJobStartLabel, gridBag);

        pastJobStartDateText = new JTextField(15);
        pastJobStartDateText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(pastJobStartDateText, gridBag);

        pastJobEndLabel = new JLabel("Past Job End Date:");
        pastJobEndLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(pastJobEndLabel, gridBag);

        pastJobEndDateText = new JTextField(15);
        pastJobEndDateText.setFont(font);
        gridBag.gridx++;
        addEmpPanel.add(pastJobEndDateText, gridBag);

        skillLabel = new JLabel("Skill:");
        skillLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(skillLabel, gridBag);

        skillText = new JTextField(15);
        skillText.setFont(font);
        skillText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(skillText, gridBag);

        talentLabel = new JLabel("Talent or Gift:");
        talentLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(talentLabel, gridBag);

        talentText = new JTextField(15);
        talentText.setFont(font);
        talentText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(talentText, gridBag);
                
        //addEmpPanel.add(addSkillButton, gridBag);

        saveButton = new JButton("Save");
        saveButton.setFont(font);
        saveButton.addActionListener(e -> 
        {
            if(nameText.getText() != null && !nameText.getText().isEmpty())
            {
                if (raceText.getText() != null && !raceText.getText().isEmpty())
                {
                    String ageTextInput = ageText.getText();
                    try 
                    {
                        int age = Integer.parseInt(ageTextInput);
                        if (age < 16)
                        {
                            messageLabel.setText("The worker is too young");
                        }
                        else if (age > 101)
                        {
                            messageLabel.setText("The number in the age textbox is too large");
                        }
                        else
                        {
                            isAddressValid = isValidAddress(addressText.getText());
                            if (isAddressValid)
                            {
                                isPhoneNumValid = isValidPhoneNumber(phoneNumberText.getText());
                                if (isPhoneNumValid)
                                {
                                    if (currJobDeptText.getText() != null && !currJobDeptText.getText().isEmpty())
                                    {
                                        newDemo = new Demographics(nameText.getText(), raceText.getText(), age, addressText.getText(), phoneNumberText.getText());
                                        HR hr = new HR();
                                        String id = hr.addEmployee(newDemo, currJobTitleText.getText(), currJobDeptText.getText());
                                    
                                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("JobHistory.txt", true)))
                                        { // 'true' enables append mode
                                            //writer.newLine(); // Add a new line (if the file already has content)
                                            writer.write("\nid: " + id + ",");
                                            writer.close();
                                            currDate = LocalDate.now();

                                            JobHistory history = new JobHistory();

                                            history.addCurrentJob(id, currJobTitleText.getText(), currDate);

                                            if (pastJobText.getText() != null && !pastJobText.getText().isEmpty())
                                            {
                                                if (!pastJobStartDateText.getText().isEmpty() && !pastJobEndDateText.getText().isEmpty())
                                                {
                                                    boolean startDateMatches = isDateInputValid(pastJobStartDateText.getText());
                                                    boolean endDateMatches = isDateInputValid(pastJobEndDateText.getText());

                                                    if (startDateMatches && endDateMatches)
                                                    {
                                                        pastStartDate = LocalDate.parse(pastJobStartDateText.getText(), formatter);
                                                        pastEndDate = LocalDate.parse(pastJobEndDateText.getText(), formatter);
        
                                                        boolean dateCheck = isDateDifferenceValid(startDateMatches, endDateMatches);
        
                                                        if (dateCheck)
                                                        {
                                                            history.addPastJob(id, pastJobText.getText(), pastStartDate, pastEndDate);
                                                            messageLabel.setText("New Employee with id " + id + "has been saved!");
                                                        }
                                                        else
                                                        {
                                                            messageLabel.setText("The date for past job are either incorrect or intefer with each other");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        messageLabel.setText("The date(s) have (a) invalid format(s).");
                                                    }
                                                }
                                                else
                                                {
                                                    messageLabel.setText("Past Job requires a start date and/or an end date");
                                                }
                                            }
                                            else
                                            {
                                                messageLabel.setText("New Employee with id " + id + "has been saved!");  
                                            }
                                            if (skillText.getText() != null && !skillText.getText().isEmpty())
                                            {
                                                history.addSkill(id, skillText.getText());
                                            }
                                            if (talentText.getText() != null & !talentText.getText().isEmpty())
                                            {
                                                history.addTalentAndGift(id, talentText.getText());
                                            }
                                        }
                                        catch (IOException r)
                                        {
                                            r.getMessage();
                                        }
                                    }
                                    else
                                    {
                                        messageLabel.setText("The current job field cannot be empty");
                                    }
                                }
                                else
                                {
                                    messageLabel.setText("The phone number is invalid");
                                }
                            }
                            else
                            {
                                messageLabel.setText("The address is not valid");
                            }
                        } 
                    }
                    catch (NumberFormatException ex) 
                    {
                        // If it's not a valid number, show an error message
                        messageLabel.setText("Age must be a valid number between 16 and 101");
                    }
                }
                else
                {
                    messageLabel.setText("Race field cannont be empty");
                }
            }
            else
            {
                messageLabel.setText("Name field cannot be empty");
            }
        });

        gridBag.gridy++;
        addEmpPanel.add(saveButton, gridBag);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        messageLabel.setFont(font);

        gridBag.gridx = 0;
        gridBag.gridy++;
        gridBag.gridwidth = 2;
        gridBag.anchor = GridBagConstraints.CENTER;
        addEmpPanel.add(messageLabel, gridBag);

        gridBag.gridwidth = 1;
        gridBag.anchor = GridBagConstraints.WEST;
                
        JScrollPane scrollPane = new JScrollPane(addEmpPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);
                        
        backButton = new JButton("Back");
        backButton.addActionListener(e -> hold.showCard(parentPanel,"OpenScreen"));
                
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }
                
    private JPanel addPastJobPanel()
    {
        pastPanel = new JPanel(new GridBagLayout());
        pastPanel.setBackground(Color.MAGENTA);
        GridBagConstraints pastConstraints = new GridBagConstraints();

        pastConstraints.gridx = 0;
        pastConstraints.gridy = 0;
        pastConstraints.insets = new Insets(10, 10, 10, 10);

        pastJobText = new JTextField(15);
        pastJobText.setFont(font);

        pastPanel.add(pastJobText, pastConstraints);

        return pastPanel;

    }

    private JPanel addSkillPanel()
    {
        skillPanel = new JPanel(new GridBagLayout());
        skillPanel.setBackground(Color.BLUE);
        GridBagConstraints skConstraint = new GridBagConstraints();
        
        skConstraint.gridx = 0;
        skConstraint.gridy = 0;
        skConstraint.insets = new Insets(10, 10, 10, 10);

        skillText = new JTextField(15);
        skillText.setFont(font);

        skillPanel.add(skillText, skConstraint);

        return skillPanel;
    }

    private String messageLabel(String problem)
    {
        return "You currently cannot add more than one " + problem + " for a new employee.";
    }

    public static boolean isValidPhoneNumber(String phoneNumber) 
    {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");
        return digitsOnly.length() >= 7 && digitsOnly.length() <= 10 && Pattern.matches("[0-9()\\- ]+", phoneNumber);
    }

    public static boolean isValidAddress(String address) 
    {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        String addressPattern = "^[\\d]+[\\s]+[a-zA-Z0-9\\s.,'-]+( Apt [\\d]+)?$";
        return Pattern.matches(addressPattern, address);
    }

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

    private boolean isDateDifferenceValid(boolean startMatch, boolean endMatch)
    {
        try
        {
            int differenceYear = pastEndDate.getYear() - pastStartDate.getYear();

            if ((startMatch && endMatch) && (differenceYear > 0))
            {
                return true;
            }

            else if ((startMatch && endMatch) && (differenceYear == 0))
            {
                int differenceMonth = pastEndDate.getMonthValue() - pastStartDate.getMonthValue();

                if (differenceMonth > 0)
                {
                    return true;
                }
                else if (differenceMonth == 0)
                {
                    long daysDifference = ChronoUnit.DAYS.between(pastEndDate, pastStartDate);
                    if (daysDifference != 0)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else 
                {
                    return false;
                }
            }
        }
        catch(DateTimeParseException e)
        {
            messageLabel.setText("Invalid date format. Please use yyyy/MM/dd.");
            return false;
        }

        return false;
    }
}

/*
isPastButtonPressed = false;
        isSkillButtonPressed = false;
        isTalentButtonPressed = false;

        addPastJobButton.addActionListener(e -> 
        {
            GridBagConstraints pastJobConst = new GridBagConstraints();
            pastJobConst.insets = new Insets(10, 10, 10, 10);

            if (!isPastButtonPressed)
            {
                pastJobConst.gridx = 1;
                addEmpPanel.add(addPastJobPanel(), pastJobConst);
                isPastButtonPressed = true;
                messageLabel.setText("");
            }
            else
            {
                messageLabel.setText(messageLabel("Past Job"));
            }
            addEmpPanel.revalidate();
            addEmpPanel.repaint();
        });
                
        addEmpPanel.add(addPastJobButton, gridBag);
                
        addSkillButton = new JButton("Add Skill:");
        addSkillButton.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
                
        addSkillButton.addActionListener(e -> 
        {
            GridBagConstraints skillConstraint = new GridBagConstraints();
            skillConstraint.gridx = 1;
            skillConstraint.gridy = gridBag.gridy++;
            skillConstraint.insets = new Insets(10, 10, 10, 10);

            if (!isSkillButtonPressed)
            {
                skillConstraint.gridx = 1;
                skillConstraint.gridy = gridBag.gridy++;
                addEmpPanel.add(addSkillPanel(), skillConstraint);
                isSkillButtonPressed = true;
                messageLabel.setText("");
            }
            else
            {
                messageLabel.setText(messageLabel("Skill"));
            }
        
            addEmpPanel.revalidate();
            addEmpPanel.repaint();
        });*/