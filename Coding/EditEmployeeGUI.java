import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;

public class EditEmployeeGUI {
    private JPanel parentPanel, panel, addEmpPanel, pastPanel, skillPanel, messagePanel;
    private JLabel titleLabel, nameLabel, raceLabel, ageLabel, addressLabel, phoneNumberLabel, currJobDeptLabel, currJobTitleLabel;
    private JLabel messageLabel;
    private JTextField idText, nameText, raceText, ageText, addressText, phoneNumberText, currJobDeptText, currJobTitleText;
    private JButton backButton, saveButton, editButton;
    private Employee emp;
    private Demographics newDemo;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();
    private GridBagConstraints gridBag;
    private JComboBox<String> idDropdown;
    private HR hr;

    // Constructor takes both the parent panel and the selected searched employee
    public EditEmployeeGUI(JPanel parentPanel, Employee emp) {
        this.parentPanel = parentPanel;
        this.emp = emp;
        this.hr = new HR();  // Initialize the HR object to access employee list
        gridBag = new GridBagConstraints();
    }

    // Create the panel with the auto-populated details for the searched employee
    public JPanel createPanel(boolean isSupervisor) {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);  // Set background to white

        titleLabel = new JLabel("Edit Employee", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);
        gridBag.insets = new Insets(10, 10, 10, 10);

        addEmpPanel = new JPanel(new GridBagLayout());
        Dimension dimension = new Dimension(200, 30);

        // ID field - If supervisor, use dropdown, otherwise, keep it editable
        idText = new JTextField(15);
        idText.setFont(font);
        if (!isSupervisor) {
            idText.setText(emp.getId()); // Set ID based on searched employee
            idText.setEditable(false); // ID should not be editable if not supervisor
        } else {
            idDropdown = new JComboBox<>();
            List<Employee> employees = hr.getEmployees();
            for (Employee employee : employees) {
                idDropdown.addItem(employee.getId());
            }
            idDropdown.addActionListener(e -> populateFieldsBasedOnId((String) idDropdown.getSelectedItem()));
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            addEmpPanel.add(new JLabel("Employee ID:"), gridBag);
            gridBag.gridx = 1;
            addEmpPanel.add(idDropdown, gridBag);
        }

        // Name field - auto populated
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(nameLabel, gridBag);

        nameText = new JTextField(15);
        nameText.setFont(font);
        nameText.setText(emp.getDemographics().getName()); // Set name based on searched employee
        gridBag.gridx = 1;
        addEmpPanel.add(nameText, gridBag);

        // Race field - auto populated
        raceLabel = new JLabel("Race:");
        raceLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(raceLabel, gridBag);

        raceText = new JTextField(15);
        raceText.setFont(font);
        raceText.setText(emp.getDemographics().getRace());
        gridBag.gridx = 1;
        addEmpPanel.add(raceText, gridBag);

        // Age field - auto populated
        ageLabel = new JLabel("Age:");
        ageLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(ageLabel, gridBag);

        ageText = new JTextField(15);
        ageText.setFont(font);
        ageText.setText(String.valueOf(emp.getDemographics().getAge()));
        gridBag.gridx = 1;
        addEmpPanel.add(ageText, gridBag);

        // Address field - auto populated
        addressLabel = new JLabel("Address:");
        addressLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(addressLabel, gridBag);

        addressText = new JTextField(15);
        addressText.setFont(font);
        addressText.setText(emp.getDemographics().getAddress());
        gridBag.gridx = 1;
        addEmpPanel.add(addressText, gridBag);

        // Phone Number field - auto populated
        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(phoneNumberLabel, gridBag);

        phoneNumberText = new JTextField(15);
        phoneNumberText.setFont(font);
        phoneNumberText.setText(emp.getDemographics().getContactInfo());
        gridBag.gridx = 1;
        addEmpPanel.add(phoneNumberText, gridBag);

        // Current Job Department field - auto populated
        currJobDeptLabel = new JLabel("Current Job Department:");
        currJobDeptLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(currJobDeptLabel, gridBag);

        currJobDeptText = new JTextField(15);
        currJobDeptText.setFont(font);
        currJobDeptText.setText(emp.getDepartment());
        gridBag.gridx = 1;
        addEmpPanel.add(currJobDeptText, gridBag);

        // Current Job Title field - auto populated
        currJobTitleLabel = new JLabel("Current Job Title:");
        currJobTitleLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy++;
        addEmpPanel.add(currJobTitleLabel, gridBag);

        currJobTitleText = new JTextField(15);
        currJobTitleText.setFont(font);
        currJobTitleText.setText(emp.getJobTitle());
        gridBag.gridx = 1;
        addEmpPanel.add(currJobTitleText, gridBag);

        // Button styles
        editButton = new JButton("Edit");
        editButton.setFont(font);
        editButton.setBackground(Color.decode("#2A5490")); // Set button color
        editButton.setForeground(Color.WHITE); // Set text color to white
        editButton.addActionListener(e -> editEmployeeDetails());
        gridBag.gridy++;
        addEmpPanel.add(editButton, gridBag);

        saveButton = new JButton("Save");
        saveButton.setFont(font);
        saveButton.setBackground(Color.decode("#2A5490")); // Set button color
        saveButton.setForeground(Color.WHITE); // Set text color to white
        saveButton.addActionListener(e -> saveEmployeeDetails());
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

        JScrollPane scrollPane = new JScrollPane(addEmpPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Back button setup
        backButton = new JButton("Back");
        backButton.setBackground(Color.decode("#2A5490")); // Set button color
        backButton.setForeground(Color.WHITE); // Set text color to white
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    // Method to populate the fields based on selected employee ID for supervisors
    private void populateFieldsBasedOnId(String selectedId) {
        Employee selectedEmployee = hr.findEmployeeById(selectedId);
        if (selectedEmployee != null) {
            emp = selectedEmployee; // Update employee reference to selected one
            nameText.setText(emp.getDemographics().getName());
            raceText.setText(emp.getDemographics().getRace());
            ageText.setText(String.valueOf(emp.getDemographics().getAge()));
            addressText.setText(emp.getDemographics().getAddress());
            phoneNumberText.setText(emp.getDemographics().getContactInfo());
            currJobDeptText.setText(emp.getDepartment());
            currJobTitleText.setText(emp.getJobTitle());
        }
    }

    // Method to save new or edited employee details
    private void saveEmployeeDetails() {
        // Validation for name, race, and age fields, etc.
        if (!nameText.getText().isEmpty()) {
            try {
                int age = Integer.parseInt(ageText.getText());
                if (age < 16 || age > 101) {
                    messageLabel.setText("Age must be between 16 and 101.");
                    return;
                }
                // Further validations
                boolean isAddressValid = isValidAddress(addressText.getText());
                boolean isPhoneNumValid = isValidPhoneNumber(phoneNumberText.getText());

                if (isAddressValid && isPhoneNumValid) {
                    newDemo = new Demographics(
                        nameText.getText(),
                        raceText.getText(),
                        age,
                        addressText.getText(),
                        phoneNumberText.getText()
                    );
                    hr.editEmployee(emp.getId(), newDemo, currJobTitleText.getText(), currJobDeptText.getText());
                    messageLabel.setText("Employee details have been saved.");
                } else {
                    messageLabel.setText("Invalid address or phone number.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid age format.");
            }
        } else {
            messageLabel.setText("Name field cannot be empty.");
        }
    }

    // Method to edit employee details
    private void editEmployeeDetails() {
        saveEmployeeDetails();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");
        return digitsOnly.length() >= 7 && digitsOnly.length() <= 10 && Pattern.matches("[0-9()\\- ]+", phoneNumber);
    }

    public static boolean isValidAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        String addressPattern = "^[\\d]+[\\s]+[a-zA-Z0-9\\s.,'-]+( Apt [\\d]+)?$";
        return Pattern.matches(addressPattern, address);
    }
}

