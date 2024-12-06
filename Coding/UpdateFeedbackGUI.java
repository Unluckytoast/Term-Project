import java.awt.*;
import javax.swing.*;

public class UpdateFeedbackGUI 
{
    private JPanel parentPanel, panel, buttonPanel;
    private JButton backButton, updateSatisButton, writeCompButton;
    private JLabel titleLabel;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    // Constructor
    public UpdateFeedbackGUI(JPanel parentPanel)
    {
        this.parentPanel = parentPanel;
    }

    //Method to create an update feedback panel
    public JPanel createPanel()
    {
        //Create update feedback panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        Dimension dimension = new Dimension(300, 200);

        // Title label
        titleLabel = new JLabel("Employee Feedback", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        titleLabel.setForeground(Color.decode("#2A5490"));  // Sea Fun text color
        panel.add(titleLabel, BorderLayout.NORTH);

        // Button panel
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);  // Sea Fun background
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Update Job Satisfaction button
        updateSatisButton = new JButton("Update Job Satisfaction");
        updateSatisButton.setPreferredSize(dimension); // Keep the button size
        updateSatisButton.setFont(font);
        updateSatisButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        updateSatisButton.setForeground(Color.WHITE);  // White text
        updateSatisButton.setFocusPainted(false);
        updateSatisButton.addActionListener(e -> repeat.showCard(parentPanel, "WriteSatisfaction"));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        buttonPanel.add(updateSatisButton, gridBag);

        // Write Complaint button
        writeCompButton = new JButton("Write Complaint");
        writeCompButton.setPreferredSize(dimension); // Keep the button size
        writeCompButton.setFont(font);
        writeCompButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        writeCompButton.setForeground(Color.WHITE);  // White text
        writeCompButton.setFocusPainted(false);
        writeCompButton.addActionListener(e -> repeat.showCard(parentPanel, "WriteComplaint"));
        gridBag.gridx++;
        buttonPanel.add(writeCompButton, gridBag);

        // Back button
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40)); // Adjust the back button size
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        backButton.setForeground(Color.WHITE);  // White text
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }
}
