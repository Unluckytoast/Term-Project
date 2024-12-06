import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class OpenScreen implements ActionListener, KeyboardFunction
{
    private JPanel panel, parentPanel;
    private JButton personalInfoButton, sprintEvalButton, writeComplaint, writeSprintEval, viewComplaint, viewEmployees, logoutButton;
    private Employee emp;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    OpenScreen(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        Dimension dimension = new Dimension(300, 200);
        personalInfoButton = new JButton("<html><center>View<br>Personal Information</center></html>");
        personalInfoButton.setPreferredSize(dimension);
        personalInfoButton.setFont(font);
        personalInfoButton.addActionListener(e -> showCard("PersonalInfoScreen"));
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        buttonPanel.add(personalInfoButton, gridBag);

        sprintEvalButton = new JButton("Sprint Evaluation");
        sprintEvalButton.setPreferredSize(dimension);
        sprintEvalButton.setFont(font);
        sprintEvalButton.addActionListener(e -> showCard("SprintEvalScreen"));
        gridBag.gridx = 1;
        buttonPanel.add(sprintEvalButton, gridBag);

        writeComplaint = new JButton("<html><center>Write Complaint/<br>Update Satisfaction<html>");
        writeComplaint.setPreferredSize(dimension);
        writeComplaint.setFont(font);
        writeComplaint.addActionListener(e -> showCard("UpdateFeedback"));
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        buttonPanel.add(writeComplaint, gridBag);

        writeSprintEval = new JButton("Write Sprint Evaluation");
        //writeSprintEval.setBackground(Color.decode("#2A5490"));
        //writeSprintEval.setForeground(Color.WHITE);
        writeSprintEval.setPreferredSize(dimension);
        writeSprintEval.setFont(font);
        writeSprintEval.addActionListener(e -> showCard("WriteSprint"));
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        buttonPanel.add(writeSprintEval, gridBag);

        if (emp.getDepartment().equalsIgnoreCase("HR"))
        {
            viewEmployees = new JButton("Manage Employees");
            viewEmployees.setPreferredSize(dimension);
            viewEmployees.setFont(font);
            viewEmployees.addActionListener(e -> showCard("ManageEmps"));
            gridBag.gridx = 1;
            gridBag.gridy++;
            buttonPanel.add(viewEmployees, gridBag); 

            viewComplaint = new JButton("View Employee Feedback");
            viewComplaint.setPreferredSize(dimension);
            viewComplaint.setFont(font);
            viewComplaint.addActionListener(e -> repeat.showCard(parentPanel, "ViewFeedback"));
            gridBag.gridx = 0;
            gridBag.gridy = 3;
            buttonPanel.add(viewComplaint, gridBag);

        }

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> repeat.showCard(parentPanel, "LoginScreen"));
        gridBag.gridx = 0;
        gridBag.gridy = gridBag.gridy + 3;
        gridBag.gridwidth = 2;
        buttonPanel.add(logoutButton, gridBag);

        panel.add(buttonPanel, BorderLayout.CENTER);

        KeyboardFunction.bindEscapeKey(panel);

        return panel;
    }
    
    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}

