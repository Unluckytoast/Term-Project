import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class ViewJobSatisfactionGUI 
{
    private JPanel parentPanel, panel, formPanel;
    private Employee emp;
    private JButton backButton, viewButton;
    private JLabel titleLabel, idLabel;
    private JTextField idField;
    private JTextArea detailsArea;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    //Constructor
    public ViewJobSatisfactionGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    //Create Personal Information Panel
    public JPanel createPanel() 
    {
        //Make a panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Title label
        titleLabel = new JLabel("View Job Satisfaction", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        // Form content
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Input field for Employee ID
        idLabel = new JLabel("Employee ID:");
        idLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(idLabel, gridBag);

        idField = new JTextField(15);
        idField.setFont(font);
        gridBag.gridx = 1;
        formPanel.add(idField, gridBag);

        // Display area for job satisfaction details
        detailsArea = new JTextArea(20, 18);
        detailsArea.setFont(font);
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        formPanel.add(scrollPane, gridBag);

        // Button to view job satisfaction
        viewButton = new JButton("View Job Satisfaction");
        viewButton.setFont(font);
        viewButton.setBackground(Color.decode("#2A5490"));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFocusPainted(false);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = idField.getText().trim();
                if (employeeId.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter an Employee ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String result = readJobSatisfaction(employeeId);
                if (result.isEmpty()) {
                    detailsArea.setText("No job satisfaction records found for Employee ID: " + employeeId);
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

        // Back button 
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> showCard("ViewFeedback"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private String readJobSatisfaction(String employeeId) {
        File file = new File("jobsatisfaction.txt");
        StringBuilder result = new StringBuilder();

        // Check if the file exists
        if (!file.exists()) {
            return "No job satisfaction data available.";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                if (line.contains("Employee ID: " + employeeId)) {
                    found = true;
                    result.append(line).append("\n");
                    // Rating
                    result.append(reader.readLine()).append("\n");
                    // Feedback
                    result.append(reader.readLine()).append("\n");
                    // Suggestion
                    result.append(reader.readLine()).append("\n");
                    reader.readLine(); // Separator line
                    result.append("\n");
                }
            }
            //Checks if there are no records found
            if (!found) 
            {
                return "";
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
