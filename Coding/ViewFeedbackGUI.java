import java.awt.*;
import javax.swing.*;

public class ViewFeedbackGUI 
{
    private JPanel parentPanel, panel, buttonPanel;
    private JButton backButton, viewJobSatisButton, viewComplaintButton;
    private JLabel titleLabel;
    private RepeatFormat repeat = new RepeatFormat();

    //Constructor
    public ViewFeedbackGUI(JPanel parentPanel)
    {
        this.parentPanel = parentPanel;
    }    

    //Create View Feedback panel
    public JPanel createPanel()
    {
        //Make panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Same button size
        Dimension dimension = new Dimension(300, 200);
        Font font = repeat.getTextFont();

        // Title label
        titleLabel = new JLabel("Employee Feedback", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        titleLabel.setForeground(Color.decode("#2A5490"));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Button panel
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // View Job Satisfaction button
        viewJobSatisButton = new JButton("View Employee's Job Satisfaction");
        viewJobSatisButton.setPreferredSize(dimension);
        viewJobSatisButton.setFont(font);
        viewJobSatisButton.setBackground(Color.decode("#2A5490"));
        viewJobSatisButton.setForeground(Color.WHITE);
        viewJobSatisButton.setFocusPainted(false);
        viewJobSatisButton.addActionListener(e -> repeat.showCard(parentPanel, "ViewSatisfaction"));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        buttonPanel.add(viewJobSatisButton, gridBag);

        // View Complaint button
        viewComplaintButton = new JButton("View Employee's Complaint");
        viewComplaintButton.setPreferredSize(dimension);
        viewComplaintButton.setFont(font);
        viewComplaintButton.setBackground(Color.decode("#2A5490"));
        viewComplaintButton.setForeground(Color.WHITE);
        viewComplaintButton.setFocusPainted(false);
        viewComplaintButton.addActionListener(e -> repeat.showCard(parentPanel, "ViewComplaint"));
        gridBag.gridx++;
        buttonPanel.add(viewComplaintButton, gridBag);

        // Back button
        backButton = new JButton("Back");
        // Adjust the back button size
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }
}
