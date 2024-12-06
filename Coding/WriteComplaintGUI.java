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

    public WriteComplaintGUI(JPanel parentPanel, Employee emp)
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel()
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);  // Sea Fun background

        // Title label with Sea Fun style
        titleLabel = new JLabel("Write Complaint", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        complaintPanel = new JPanel(new GridBagLayout());
        complaintPanel.setBackground(Color.WHITE);  // Sea Fun background
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Complaint label
        complaintLabel = new JLabel("Write Complaint:");
        complaintLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        complaintPanel.add(complaintLabel, gridBag);

        // Complaint text area
        complaintTextArea = new JTextArea();
        complaintTextArea.setFont(font);
        complaintTextArea.setLineWrap(true);  // Enable line wrapping
        complaintTextArea.setWrapStyleWord(true);  // Wrap at word boundaries

        // Scroll pane for the complaint text area
        JScrollPane scrollPane = new JScrollPane(complaintTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));  // Adjust size here
        gridBag.gridy++;
        complaintPanel.add(scrollPane, gridBag);

        // Message label for save feedback
        messageLabel = new JLabel("");
        messageLabel.setFont(font);
        gridBag.gridy++;
        complaintPanel.add(messageLabel, gridBag);

        // Save button with Sea Fun styling
        saveButton = new JButton("Save");
        saveButton.setFont(font);
        saveButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        saveButton.setForeground(Color.WHITE);  // White text
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

        // Back button with Sea Fun styling
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
        backButton.setForeground(Color.WHITE);  // White text
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> showCard("UpdateFeedback"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(complaintPanel, BorderLayout.CENTER);

        return panel;
    }

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
