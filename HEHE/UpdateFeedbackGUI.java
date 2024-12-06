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
        panel.setBackground(Color.LIGHT_GRAY);
        Dimension dimension = new Dimension(300, 200);
        Font font = repeat.getTextFont();

        titleLabel = new JLabel("Employee Feedback");
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        updateSatisButton = new JButton("Update Job Satisfaction");
        updateSatisButton.setPreferredSize(dimension);
        updateSatisButton.setFont(font);
        updateSatisButton.addActionListener(e -> showCard("WriteSatisfaction"));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        buttonPanel.add(updateSatisButton, gridBag);

        writeCompButton = new JButton("Write Complaint");
        writeCompButton.setPreferredSize(dimension);
        writeCompButton.setFont(font);
        writeCompButton.addActionListener(e -> showCard("WriteComplaint"));
        gridBag.gridx++;
        buttonPanel.add(writeCompButton, gridBag);

        backButton = new JButton("Back");
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
