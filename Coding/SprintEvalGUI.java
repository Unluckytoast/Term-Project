import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

class SprintEvalGUI 
{
    private JPanel parentPanel, panel, containerPanel, evalPanel;
    private Employee emp;
    private JLabel titleLabel, ratingLabel, noInfoFoundLabel, empIdLabel;
    private JTextArea commentTextArea;
    private JButton backButton;
    private List<String> evaluations;
    private RepeatFormat repeat = new RepeatFormat();
    private Font labelFont = repeat.getTextFont();

    // Constructor
    SprintEvalGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    //Method to create sprint evaluation panel
    public JPanel createPanel() 
    {
        //Make panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        //Create title label
        titleLabel = new JLabel("Sprint Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        // Container for evaluations with a scroll pane
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        //Get evaluations and add it to the container for evaluations
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
        scrollPane.setPreferredSize(new Dimension(500, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

        //Create and add a back button to the panel
        backButton = new JButton("Back");
        backButton.setFont(labelFont);
        backButton.setBackground(Color.decode("#2A5490"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    //Method to create evaluation panel(s)
    private JPanel createEvalForEmployee(String eval)
    {
        //Create eval panel to return
        evalPanel = new JPanel();
        evalPanel.setLayout(new BoxLayout(evalPanel, BoxLayout.Y_AXIS));
        evalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        evalPanel.setBackground(Color.WHITE);
        Border coloredBorder = BorderFactory.createLineBorder(Color.decode("#2a5490"), 3);
        evalPanel.setBorder(coloredBorder);

        //Add evaluations, or lack thereof, to the evalPanel
        String[] parts = eval.split(",");
        //If parts length is bigger than three get the evaluation
        if (parts.length >= 3)
        {
            //Get id, eval comment and rating
            String empId = parts[0].trim();
            String comment = parts[1].trim();
            String rating = parts[2].trim();

            // Display the Employee ID who wrote the evaluation
            empIdLabel = new JLabel("Written by: " + empId);
            empIdLabel.setFont(labelFont);
            evalPanel.add(empIdLabel);

            //Display the Comment of the Evaluation
            commentTextArea = new JTextArea("Comment: " + comment);
            commentTextArea.setFont(labelFont);
            commentTextArea.setLineWrap(true);
            commentTextArea.setWrapStyleWord(true);
            commentTextArea.setEditable(false); // Make it read-only
            commentTextArea.setPreferredSize(new Dimension(400, 100)); // Set width and dynamic height
            commentTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            evalPanel.add(commentTextArea);

            //Display the Rating of the Evaluation
            ratingLabel = new JLabel("Rating: " + rating);
            ratingLabel.setFont(labelFont);
            evalPanel.add(ratingLabel);

            // Add some space between evaluations
            evalPanel.add(Box.createVerticalStrut(10));
        }
        //Else get the message that there is no evaluation available
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

        //Try to read the text file where the sprint evaluations are located
        try (BufferedReader reader = new BufferedReader(new FileReader("SprintEval.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                if (parts.length >= 3)
                {
                    //If the employee is in HR or is a Supervisor, then let them view all of the evaluations
                    if (emp.getDepartment().equalsIgnoreCase("HR") || emp.getJobTitle().equalsIgnoreCase("Supervisor")) 
                    {
                        // Supervisor can see all evaluations
                        evaluations.add(line);
                    }
                    else if (parts[0].equals(employeeId)) 
                    {
                        // Regular employee sees only their own evaluations
                        evaluations.add(line);
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
}
