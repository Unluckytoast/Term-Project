import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

class SprintEvalGUI 
{
    private JPanel panel, parentPanel, evalPanel, employeeEvalPanel;
    private Employee emp;
    private JLabel evalLabel, ratingLabel, noInfoFoundLabel, empIdLabel;
    private JTextArea commentTextArea;
    private List<String> evaluations;
    private Dimension dimension;
    private JButton backButton;
    private RepeatFormat repeat = new RepeatFormat();
    private Font labelFont = repeat.getTextFont();

    SprintEvalGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Sprint Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        // Container for evaluations with a scroll pane
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        // Modify this method to show evaluations based on department
        evaluations = getSprintEvaluations();

        if (evaluations.isEmpty()) {
            noInfoFoundLabel = new JLabel("No evaluations found for " + emp.getDemographics().getName());
            noInfoFoundLabel.setFont(labelFont);
            containerPanel.add(noInfoFoundLabel);
        } else {
            for (String eval : evaluations) 
            {
                containerPanel.add(createEvalForEmployee(eval));
            }
        }

        // Add the container panel to a scroll pane for better scrolling functionality
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setPreferredSize(new Dimension(500, 400)); // Set preferred size for scroll pane

        panel.add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.setFont(labelFont);
        backButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        backButton.setForeground(Color.WHITE);  // White text
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createEvalForEmployee(String eval)
    {
        evalPanel = new JPanel();
        evalPanel.setLayout(new BoxLayout(evalPanel, BoxLayout.Y_AXIS));
        evalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        evalPanel.setBackground(Color.WHITE);

        Border coloredBorder = BorderFactory.createLineBorder(Color.decode("#2a5490"), 3);
        evalPanel.setBorder(coloredBorder);

        String[] parts = eval.split(",");
        if (parts.length >= 3)
        {
            String empId = parts[0].trim();  // Extract Employee ID
            String comment = parts[1].trim();
            String rating = parts[2].trim();

            // Display the Employee ID who wrote the evaluation
            empIdLabel = new JLabel("Written by: " + empId);
            empIdLabel.setFont(labelFont);
            evalPanel.add(empIdLabel);

            commentTextArea = new JTextArea("Comment: " + comment);
            commentTextArea.setFont(labelFont);
            commentTextArea.setLineWrap(true);
            commentTextArea.setWrapStyleWord(true);
            commentTextArea.setEditable(false); // Make it read-only
            commentTextArea.setPreferredSize(new Dimension(400, 100)); // Set width and dynamic height
            commentTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            evalPanel.add(commentTextArea);

            ratingLabel = new JLabel("Rating: " + rating);
            ratingLabel.setFont(labelFont);
            evalPanel.add(ratingLabel);

            evalPanel.add(Box.createVerticalStrut(10)); // Add some space between evaluations
        }
        else
        {
            noInfoFoundLabel = new JLabel("No sprint evaluation found for " + emp.getDemographics().getName());
            noInfoFoundLabel.setFont(labelFont);
            evalPanel.add(noInfoFoundLabel);
        }

        return evalPanel;
    }

    // Fetch sprint evaluations based on the department
    private List<String> getSprintEvaluations() 
    {
        String employeeId = emp.getId();
        List<String> evaluations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("SprintEval.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                if (parts.length >= 3)
                {
                    if (emp.getDepartment().equalsIgnoreCase("HR") || emp.getJobTitle().equalsIgnoreCase("Supervisor")) 
                    {
                        // Supervisor can see all evaluations
                        evaluations.add(line);  // Include the full line (ID, comment, rating)
                    }
                    else if (parts[0].equals(employeeId)) 
                    {
                        // Regular employee sees only their own evaluations
                        evaluations.add(line);  // Include the full line (ID, comment, rating)
                    }
                }
            }
        }
        catch (IOException e) 
        {
            System.out.println("Error reading sprint evaluations.");
            e.printStackTrace();
        }

        return evaluations;
    }

    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
