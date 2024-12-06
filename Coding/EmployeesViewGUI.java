import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class EmployeesViewGUI {
    private JPanel parentPanel, panel, formPanel;
    private Employee emp;
    private JButton backButton, viewButton, deleteButton, editEmpButton;
    private JLabel titleLabel, idLabel;
    private JTextField idField;
    private JTextArea detailsArea;
    private HR hr; // Reference to HR class to access fireEmployee method

    public EmployeesViewGUI(JPanel parentPanel, Employee emp, HR hr) {
        this.parentPanel = parentPanel;
        this.emp = emp;
        this.hr = hr; // Initialize HR instance
    }

    public JPanel createPanel() {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // Sea Fun background

        // Title label
        titleLabel = new JLabel("View Employees", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 60, 60)); // Dark gray text
        panel.add(titleLabel, BorderLayout.NORTH);

        // Form content
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE); // Sea Fun background
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Input field for Employee ID
        idLabel = new JLabel("Employee ID:");
        idLabel.setFont(new Font("Georgia", Font.PLAIN, 16));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(idLabel, gridBag);

        idField = new JTextField(15);
        idField.setFont(new Font("Georgia", Font.PLAIN, 16));
        gridBag.gridx = 1;
        formPanel.add(idField, gridBag);

        // Display area for employee details
        detailsArea = new JTextArea(20, 18);
        detailsArea.setFont(new Font("Georgia", Font.PLAIN, 16));
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        formPanel.add(scrollPane, gridBag);

        // Button to view employee details
        viewButton = new JButton("View Employee");
        styleButton(viewButton, Color.decode("#2A5490"), Color.WHITE); // Sea Fun button
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = idField.getText().trim();
                if (employeeId.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter an Employee ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String result = readEmployeeId(employeeId);
                if (result.isEmpty()) {
                    detailsArea.setText("No Employee records found for Employee ID: " + employeeId);
                } else {
                    detailsArea.setText(result);
                }
            }
        });
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.gridwidth = 2;
        formPanel.add(viewButton, gridBag);

        // Adding the Edit Employee button
        editEmpButton = new JButton("Edit Employee");
        styleButton(editEmpButton, Color.decode("#2A5490"), Color.WHITE); // Sea Fun button
        editEmpButton.addActionListener(e -> showCard("EditEmp"));
        gridBag.gridy = 3;
        gridBag.gridwidth = 2;
        formPanel.add(editEmpButton, gridBag);

        // Button to delete employee
        deleteButton = new JButton("Delete Employee");
        styleButton(deleteButton, Color.RED, Color.WHITE); // Red delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = idField.getText().trim();
                if (employeeId.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter an Employee ID to delete.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Call the HR class to delete the employee by ID
                hr.fireEmployee(employeeId);
                detailsArea.setText("Employee with ID " + employeeId + " has been deleted.");
            }
        });
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        gridBag.gridwidth = 2;
        formPanel.add(deleteButton, gridBag);

        panel.add(formPanel, BorderLayout.CENTER);

        // Back button to return to the previous screen
        backButton = new JButton("Back");
        styleButton(backButton, Color.decode("#2A5490"), Color.WHITE); // Sea Fun button
        backButton.addActionListener(e -> showCard("ManageEmps"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setFont(new Font("Georgia", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40)); // Set a fixed size for buttons
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding around the text
    }

    private String readEmployeeId(String employeeId) {
        File file = new File("employeeData.txt");
        StringBuilder result = new StringBuilder();

        // Check if the file exists
        if (!file.exists()) {
            return "No employee data available.";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split(","); // Split line by commas
                if (employeeData[0].trim().equals(employeeId)) { // Check if first element matches employee ID
                    found = true;
                    result.append("Employee ID: ").append(employeeData[0]).append("\n");
                    result.append("Name: ").append(employeeData[1]).append("\n");
                    result.append("Race: ").append(employeeData[2]).append("\n");
                    result.append("Age: ").append(employeeData[3]).append("\n");
                    result.append("Address: ").append(employeeData[4]).append("\n");
                    result.append("Contact: ").append(employeeData[5]).append("\n");
                    result.append("Job Title: ").append(employeeData[6]).append("\n");
                    result.append("Department: ").append(employeeData[7]).append("\n");
                    break;
                }
            }

            if (!found) {
                return ""; // No records found
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file.";
        }

        return result.toString();
    }

    private void showCard(String card) {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
