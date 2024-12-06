import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class OpenScreen implements ActionListener, KeyboardFunction
{
    private JPanel panel, parentPanel, buttonPanel;
    private JButton personalInfoButton, sprintEvalButton, writeComplaint, writeSprintEval, viewComplaint, viewEmployees, logoutButton;
    private Employee emp;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    //Constructor
    OpenScreen(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    //Create Opening Screen Panel
    public JPanel createPanel() 
    {
        //Make panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // White background for the panel

        //Make button panel to add to panel
        buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        Dimension dimension = new Dimension(300, 200);

        // Personal Info Button
        personalInfoButton = new JButton("<html><center>View<br>Personal Information</center></html>");
        personalInfoButton.setPreferredSize(dimension);
        personalInfoButton.setFont(font);
        personalInfoButton.setBackground(Color.decode("#2A5490"));
        personalInfoButton.setForeground(Color.WHITE); // White text
        personalInfoButton.addActionListener(e -> repeat.showCard(parentPanel, "PersonalInfoScreen"));
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        buttonPanel.add(personalInfoButton, gridBag);

        // Sprint Evaluation Button
        sprintEvalButton = new JButton("Sprint Evaluation");
        sprintEvalButton.setPreferredSize(dimension);
        sprintEvalButton.setFont(font);
        sprintEvalButton.setBackground(Color.decode("#2A5490"));
        sprintEvalButton.setForeground(Color.WHITE); // White text
        sprintEvalButton.addActionListener(e -> repeat.showCard(parentPanel, "SprintEvalScreen"));
        gridBag.gridx = 1;
        buttonPanel.add(sprintEvalButton, gridBag);

        // Write Complaint / Update Satisfaction Button
        writeComplaint = new JButton("<html><center>Write Complaint/<br>Update Satisfaction</center></html>");
        writeComplaint.setPreferredSize(dimension);
        writeComplaint.setFont(font);
        writeComplaint.setBackground(Color.decode("#2A5490"));
        writeComplaint.setForeground(Color.WHITE); // White text
        writeComplaint.addActionListener(e -> repeat.showCard(parentPanel, "UpdateFeedback"));
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        buttonPanel.add(writeComplaint, gridBag);

        // Write Sprint Evaluation Button
        writeSprintEval = new JButton("Write Sprint Evaluation");
        writeSprintEval.setPreferredSize(dimension);
        writeSprintEval.setFont(font);
        writeSprintEval.setBackground(Color.decode("#2A5490"));
        writeSprintEval.setForeground(Color.WHITE); // White text
        writeSprintEval.addActionListener(e -> repeat.showCard(parentPanel, "WriteSprint"));
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        buttonPanel.add(writeSprintEval, gridBag);

        // HR Specific Buttons
        if (emp.getDepartment().equalsIgnoreCase("HR"))
        {
            // Manage Employees Button
            viewEmployees = new JButton("Manage Employees");
            viewEmployees.setPreferredSize(dimension);
            viewEmployees.setFont(font);
            viewEmployees.setBackground(Color.decode("#2A5490"));
            viewEmployees.setForeground(Color.WHITE); // White text
            viewEmployees.addActionListener(e -> repeat.showCard(parentPanel, "ManageEmps"));
            gridBag.gridx = 1;
            gridBag.gridy++;
            buttonPanel.add(viewEmployees, gridBag); 

            // View Employee Feedback Button
            viewComplaint = new JButton("View Employee Feedback");
            viewComplaint.setPreferredSize(dimension);
            viewComplaint.setFont(font);
            viewComplaint.setBackground(Color.decode("#2A5490"));
            viewComplaint.setForeground(Color.WHITE); // White text
            viewComplaint.addActionListener(e -> repeat.showCard(parentPanel, "ViewFeedback"));
            gridBag.gridx = 0;
            gridBag.gridy = 3;
            buttonPanel.add(viewComplaint, gridBag);
        }

        // Logout Button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(font);
        logoutButton.setBackground(Color.decode("#2A5490"));
        logoutButton.setForeground(Color.WHITE); // White text
        logoutButton.addActionListener(e -> repeat.showCard(parentPanel, "LoginScreen"));
        gridBag.gridx = 0;
        gridBag.gridy = gridBag.gridy + 3;
        gridBag.gridwidth = 2;
        buttonPanel.add(logoutButton, gridBag);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Binds escape key functionality for navigation
        KeyboardFunction.bindEscapeKey(panel);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
