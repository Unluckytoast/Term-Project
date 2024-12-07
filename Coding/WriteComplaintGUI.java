import java.awt.*;
import javax.swing.*;

public class WriteComplaintGUI 
{
    private JPanel parentPanel, panel, complaintPanel;
    private JLabel titleLabel, complaintLabel, messageLabel;
    private JTextArea complaintTextArea;
    private JButton backButton, saveButton;
    private Employee emp;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    //Constructor
    public WriteComplaintGUI(JPanel parentPanel, Employee emp)
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    //Create Write Complaint Panel
    public JPanel createPanel()
    {
        //Make a panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);  // White background

        // Title label
        titleLabel = new JLabel("Write Complaint", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        //Make a complaint panel to add to the panel
        complaintPanel = new JPanel(new GridBagLayout());
        complaintPanel.setBackground(Color.WHITE);  // White background
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Complaint label
        complaintLabel = new JLabel("Write Complaint:");
        complaintLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        complaintPanel.add(complaintLabel, gridBag);

        // Complaint text area, enable text wrapping at word boundaries
        complaintTextArea = new JTextArea();
        complaintTextArea.setFont(font);
        complaintTextArea.setLineWrap(true);
        complaintTextArea.setWrapStyleWord(true);

        // Scroll pane for the complaint text area
        JScrollPane scrollPane = new JScrollPane(complaintTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        gridBag.gridy++;
        complaintPanel.add(scrollPane, gridBag);

        // Message label for save feedback
        messageLabel = new JLabel("");
        messageLabel.setFont(font);
        gridBag.gridy++;
        complaintPanel.add(messageLabel, gridBag);

        // Save button
        saveButton = new JButton("Save");
        saveButton.setFont(font);
        saveButton.setBackground(Color.decode("#2A5490"));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> {
            if (!complaintTextArea.getText().isEmpty()) {
                emp.fileComplaint(complaintTextArea.getText());
                messageLabel.setText("The complaint has been saved!");
            } else {
                messageLabel.setText("An empty complaint cannot be saved into the system");
            }
        });

        gridBag.gridy++;
        complaintPanel.add(saveButton, gridBag);

        // Back button
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "UpdateFeedback"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(complaintPanel, BorderLayout.CENTER);

        return panel;
    }
}
