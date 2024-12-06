import java.awt.*;
import javax.swing.*;

public class ViewFeedbackGUI 
{
    private JPanel parentPanel, panel, buttonPanel;
    private JButton backButton, viewJobSatisButton, viewComplaintButton;
    private JLabel titleLabel;
    private RepeatFormat repeat = new RepeatFormat();

    public ViewFeedbackGUI(JPanel parentPanel)
    {
        this.parentPanel = parentPanel;
    }    

    public JPanel createPanel()
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // Sea Fun background

        Dimension dimension = new Dimension(300, 200); // Same button size
        Font font = repeat.getTextFont();

        // Title label
        titleLabel = new JLabel("Employee Feedback", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        titleLabel.setForeground(Color.decode("#2A5490"));  // Sea Fun text color
        panel.add(titleLabel, BorderLayout.NORTH);

        // Button panel
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE); // Sea Fun background
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // View Job Satisfaction button
        viewJobSatisButton = new JButton("View Employee's Job Satisfaction");
        viewJobSatisButton.setPreferredSize(dimension); // Keep the button size
        viewJobSatisButton.setFont(font);
        viewJobSatisButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        viewJobSatisButton.setForeground(Color.WHITE);  // White text
        viewJobSatisButton.setFocusPainted(false);
        viewJobSatisButton.addActionListener(e -> showCard("ViewSatisfaction"));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        buttonPanel.add(viewJobSatisButton, gridBag);

        // View Complaint button
        viewComplaintButton = new JButton("View Employee's Complaint");
        viewComplaintButton.setPreferredSize(dimension); // Keep the button size
        viewComplaintButton.setFont(font);
        viewComplaintButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        viewComplaintButton.setForeground(Color.WHITE);  // White text
        viewComplaintButton.setFocusPainted(false);
        viewComplaintButton.addActionListener(e -> showCard("ViewComplaint"));
        gridBag.gridx++;
        buttonPanel.add(viewComplaintButton, gridBag);

        // Back button
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40)); // Adjust the back button size
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        backButton.setForeground(Color.WHITE);  // White text
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
