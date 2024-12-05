import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class ViewComplaintGUI
{
    private JPanel parentPanel, panel, formPanel;
    private Employee emp;
    private JButton backButton, viewButton;
    private JLabel titleLabel, idLabel;
    private JTextField idField;
    private JTextArea detailsArea;

    public ViewComplaintGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // Alice blue

        // Title label
        titleLabel = new JLabel("View Complaints", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 60, 60));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Form content
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Input field for Employee ID
        idLabel = new JLabel("Employee ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(idLabel, gridBag);

        idField = new JTextField(15);
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        gridBag.gridx = 1;
        formPanel.add(idField, gridBag);

        // Display area for job satisfaction details
        detailsArea = new JTextArea(27, 27);
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        formPanel.add(scrollPane, gridBag);

        // Button to view job satisfaction
        viewButton = new JButton("View Employee Complaints");
        viewButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewButton.setBackground(new Color(135, 206, 250)); // Light blue
        viewButton.setForeground(Color.BLACK);
        viewButton.setFocusPainted(false);
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
                    detailsArea.setText("No complaint records found for Employee ID: " + employeeId);
                } else {
                    detailsArea.setText(result);
                }
            }
        });
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.gridwidth = 2;
        formPanel.add(viewButton, gridBag);

        panel.add(formPanel, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("ManageEmps"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private String readEmployeeId(String employeeId) {
        File file = new File("Complaints.txt");
        StringBuilder result = new StringBuilder();
    
        // Check if the file exists
        if (!file.exists()) {
            return "No complaints available.";
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
                    result.append("Complaint: ").append(employeeData[1]).append("\n");
                    result.append("\n");
        
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
    

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}