import java.awt.*;
import javax.swing.*;

public class EmployeeViewGUI 
{
    private JPanel parentPanel, panel;
    private Employee emp;
    private JLabel titleLabel;
    private JButton backButton;

    EmployeeViewGUI(JPanel parentPanel, Employee emp)
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel()
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        Font font = new Font("Georgia", Font.BOLD, 20);
        titleLabel = new JLabel("View Employees", SwingConstants.CENTER);
        titleLabel.setFont(font);
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
