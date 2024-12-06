import java.awt.*;
import javax.swing.*;

public class UpdateFeedbackGUI 
{
    private JPanel parentPanel, panel, buttonPanel;
    private JButton backButton, updateSatisButton, writeCompButton;
    private JLabel titleLabel;
    private RepeatFormat repeat = new RepeatFormat();

    public UpdateFeedbackGUI(JPanel parentPanel)
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
        updateSatisButton.addActionListener(e -> showCard("WriteSatisfaction"));
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
        writeCompButton.addActionListener(e -> showCard("WriteComplaint"));
        gridBag.gridx++;
        buttonPanel.add(writeCompButton, gridBag);

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
