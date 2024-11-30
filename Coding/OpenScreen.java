import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class OpenScreen implements ActionListener, KeyboardFunction
{
    private JPanel panel, parentPanel;
    private JButton personalInfoButton, sprintEvalButton, writeComplaint, writeSprintEval, viewComplaint, viewEmployees, logoutButton;
    private Employee emp;

    OpenScreen(JPanel parentPanel) 
    {
        this.parentPanel = parentPanel;
    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        personalInfoButton = new JButton("<html><center>View<br>Personal Information</center></html>");
        personalInfoButton.setPreferredSize(new Dimension(200, 100));
        personalInfoButton.setFont(new Font("Arial", Font.BOLD, 20));
        personalInfoButton.addActionListener(e -> showCard("PersonalInfoScreen"));
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        buttonPanel.add(personalInfoButton, gridBag);

        sprintEvalButton = new JButton("Sprint Evaluation");
        sprintEvalButton.setPreferredSize(new Dimension(200, 100));
        sprintEvalButton.setFont(new Font("Arial", Font.BOLD, 20));
        sprintEvalButton.addActionListener(e -> showCard("SprintEvalScreen"));
        gridBag.gridx = 1;
        buttonPanel.add(sprintEvalButton, gridBag);

        writeComplaint = new JButton("Write Complaint");
        writeComplaint.setPreferredSize(new Dimension(200, 100));
        writeComplaint.setFont(new Font("Arial", Font.BOLD, 20));
        writeComplaint.addActionListener(e -> writeComplaint.setText("You have clicked this button!"));
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        buttonPanel.add(writeComplaint, gridBag);

        if (emp.getJobTitle().equalsIgnoreCase("Supervisor"))
        {
            writeSprintEval = new JButton("Write Sprint Evaluation");
            writeSprintEval.setPreferredSize(new Dimension(200, 100));
            writeSprintEval.setFont(new Font("Arial", Font.BOLD, 20));
            writeSprintEval.addActionListener(e -> writeSprintEval.setText("You have clicked this button!"));
            gridBag.gridx = 1;
            gridBag.gridy = 2;
            buttonPanel.add(writeSprintEval, gridBag);
        }

        if (emp.getDepartment().equalsIgnoreCase("HR"))
        {
            viewComplaint = new JButton("View Complaints");
            viewComplaint.setPreferredSize(new Dimension(200, 100));
            viewComplaint.setFont(new Font("Arial", Font.BOLD, 20));
            viewComplaint.addActionListener(e -> viewComplaint.setText("You have clicked this button!"));
            gridBag.gridx = 0;
            gridBag.gridy = 3;
            buttonPanel.add(viewComplaint, gridBag);
    
            viewEmployees = new JButton("View Employees");
            viewEmployees.setPreferredSize(new Dimension(200, 100));
            viewEmployees.setFont(new Font("Arial", Font.BOLD, 20));
            viewEmployees.addActionListener(e -> showCard("EmployeesView"));
            gridBag.gridx = 1;
            gridBag.gridy = 3;
            buttonPanel.add(viewEmployees, gridBag); 
        }

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> showCard("LoginScreen"));
        gridBag.gridx = 0;
        gridBag.gridy = gridBag.gridy + 3;
        gridBag.gridwidth = 2;
        buttonPanel.add(logoutButton, gridBag);

        panel.add(buttonPanel, BorderLayout.CENTER);

        KeyboardFunction.bindEscapeKey(panel);

        return panel;
    }

    public void setOpenScreenEmployee(Employee emp)
    {
        this.emp = emp;
    }
    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}

