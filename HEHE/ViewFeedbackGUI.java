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
        panel.setBackground(Color.LIGHT_GRAY);
        Dimension dimension = new Dimension(300, 200);
        Font font = repeat.getTextFont();

        titleLabel = new JLabel("Employee Feedback");
        titleLabel.setFont(repeat.getTitleFont());
        panel.add(titleLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        viewJobSatisButton = new JButton("View Employee's Job Satisfaction");
        viewJobSatisButton.setPreferredSize(dimension);
        viewJobSatisButton.setFont(font);
        viewJobSatisButton.addActionListener(e -> showCard("ViewSatisfaction"));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        buttonPanel.add(viewJobSatisButton, gridBag);

        viewComplaintButton = new JButton("View Employee's Complaint");
        viewComplaintButton.setPreferredSize(dimension);
        viewComplaintButton.setFont(font);
        viewComplaintButton.addActionListener(e -> showCard("ViewComplaint"));
        gridBag.gridx++;
        buttonPanel.add(viewComplaintButton, gridBag);

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
