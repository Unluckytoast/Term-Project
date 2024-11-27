import java.awt.*;
import javax.swing.*;

public class EmployeesViewGUI 
{
    private JPanel parentPanel;
    private JPanel panel;
    private JLabel titleLabel;
    private JButton backButton;

    EmployeesViewGUI(JPanel parentPanel)
    {
        this.parentPanel = parentPanel;
    }

    public JPanel createPanel()
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        titleLabel = new JLabel("View Employees", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
