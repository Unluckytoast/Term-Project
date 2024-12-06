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
    private JLabel evalLabel, ratingLabel, noInfoFoundLabel;
    private JTextArea commentTextArea;
    private List<String> evaluations;
    private Dimension dimension;
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
        panel.setBackground(Color.CYAN);

        JLabel titleLabel = new JLabel("Sprint Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);
        
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        evaluations = getSprintEvaluationsForLoggedInEmployee();

        int row = 0;
        employeeEvalPanel = new JPanel(new GridBagLayout());

        for(String eval: evaluations)
        {
            System.out.println(eval);
            gridBag.gridx = 0;
            gridBag.gridy = row;
            employeeEvalPanel.add(createEvalForEmployee(eval), gridBag);
            row++;
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(employeeEvalPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createEvalForEmployee(String eval)
    {
        evalPanel = new JPanel(new GridBagLayout());
        evalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        evalPanel.setBackground(Color.GREEN);

        Border coloredBorder = BorderFactory.createLineBorder(Color.RED, 3);
        evalPanel.setBorder(coloredBorder);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);

        String[] parts = eval.split(",");
        if (parts.length >= 2)
        {
            String comment = parts[0].trim();
            String rating = parts[1].trim();

            commentTextArea = new JTextArea("Comment: " + comment);
            commentTextArea.setFont(labelFont);
            commentTextArea.setLineWrap(true);
            commentTextArea.setWrapStyleWord(true);
            commentTextArea.setEditable(false); // Make it read-only
            commentTextArea.setPreferredSize(new Dimension(400, 100)); // Set width and dynamic height
            commentTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            evalPanel.add(commentTextArea, gridBag);

            ratingLabel = new JLabel("Rating: " + rating);
            ratingLabel.setFont(labelFont);
            gridBag.gridy = 1;
            evalPanel.add(ratingLabel, gridBag);
        }
        else
        {
            noInfoFoundLabel = new JLabel("No sprint evaluation found for " + emp.getDemographics().getName());
            noInfoFoundLabel.setFont(labelFont);
            gridBag.gridx = 0;
            gridBag.gridy = 0;
            evalPanel.add(noInfoFoundLabel, gridBag);
        }

        JScrollPane scroll = new JScrollPane();
        evalPanel.add(scroll);

        return evalPanel;
    }

    // View sprint evaluations for the logged-in employee and return them as a list
    private List<String> getSprintEvaluationsForLoggedInEmployee() 
    {
        String employeeId = emp.getId();
        List<String> evaluations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("SprintEval.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(employeeId)) 
                {
                    // Add evaluation details (comment and rating) to the list
                    evaluations.add(parts[1].trim() + ", " + parts[2].trim());
                }
            }
        }
        catch (IOException e) 
        {
            System.out.println("Error reading sprint evaluations.");
            e.printStackTrace();
        }
        if(evaluations.isEmpty())
        {
            evaluations.add("No evaluations found for " + emp.getDemographics().getName());
        }
        return evaluations; // Return the list of evaluations
    }


    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
