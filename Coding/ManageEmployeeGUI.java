import java.awt.*;
import javax.swing.*;

public class ManageEmployeeGUI {
    private JPanel parentPanel, panel, managePanel;
    private JLabel titleLabel;
    private JButton addEmpButton, viewButton, backButton;
    private Employee emp;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    //Constructor
    ManageEmployeeGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    //Create Manage Employee Panel
    public JPanel createPanel() 
    {
        //Make panel to return
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Create Title label
        titleLabel = new JLabel("Manage Employees", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        titleLabel.setForeground(Color.BLACK);
        panel.add(titleLabel, BorderLayout.NORTH);

        //Make manage panel to add the buttons to
        managePanel = new JPanel(new GridBagLayout());
        managePanel.setBackground(Color.WHITE);
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        Dimension dimension = new Dimension(300, 200);

        // View Employees button
        viewButton = new JButton("View Employees");
        viewButton.setFont(font);
        viewButton.setPreferredSize(dimension);
        viewButton.setBackground(Color.decode("#2A5490"));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFocusPainted(false);
        viewButton.addActionListener(e -> repeat.showCard(parentPanel, "EmployeesView"));
        managePanel.add(viewButton, gridBag);

        // Add Employee button
        gridBag.gridx = 1;
        addEmpButton = new JButton("Add Employee");
        addEmpButton.setFont(font);
        addEmpButton.setPreferredSize(dimension);
        addEmpButton.setBackground(Color.decode("#2A5490"));
        addEmpButton.setForeground(Color.WHITE);
        addEmpButton.setFocusPainted(false);
        addEmpButton.addActionListener(e -> repeat.showCard(parentPanel, "AddEmp"));
        managePanel.add(addEmpButton, gridBag);

        // Back button
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(managePanel, BorderLayout.CENTER);

        return panel;
    }
}
