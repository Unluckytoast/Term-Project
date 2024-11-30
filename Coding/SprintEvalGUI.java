import java.awt.*;
import java.util.List;
import javax.swing.*;

class SprintEvalGUI 
{
    private JPanel panel, parentPanel, evalPanel;
    private Employee emp;
    private JLabel evalLabel;
    private SprintEval sprint;
    private List<String> evaluations;

    SprintEvalGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.CYAN);

        JLabel titleLabel = new JLabel("Sprint Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        evaluations = sprint.loadEvaluationsForEmployee(emp.getId());
        int row = 0;
        for(String eval: evaluations)
        {
            gridBag.gridx = 0;
            gridBag.gridy = row;
            panel.add(createEvalForEmployee(eval), gridBag);
            row++;
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createEvalForEmployee(String eval)
    {
        evalPanel = new JPanel(new GridBagLayout());
        evalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        evalPanel.setBackground(Color.GREEN);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);

        evalLabel = new JLabel(eval);
        evalLabel.setFont(labelFont);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        evalPanel.add(evalLabel, gridBag);

        return evalPanel;
    }

    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
