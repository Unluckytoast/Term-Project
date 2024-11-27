import java.awt.*;
import javax.swing.*;

class SprintEvalGUI 
{
    private JPanel panel;
    private JPanel parentPanel;

    SprintEvalGUI(JPanel parentPanel) 
    {
        this.parentPanel = parentPanel;
    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.CYAN);

        JLabel titleLabel = new JLabel("Sprint Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        JButton backButton = new JButton("Back");
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
