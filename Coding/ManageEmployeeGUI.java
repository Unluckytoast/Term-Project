import java.awt.*;
import javax.swing.*;

public class ManageEmployeeGUI 
{
    private JPanel parentPanel, panel, managePanel;
    private JLabel titleLabel;
    private JButton addEmpButton, viewButton, backButton;
    private Employee emp;

    ManageEmployeeGUI(JPanel parentPanel, Employee emp)
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel()
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        Font font = new Font("Georgia", Font.BOLD, 20);
        titleLabel = new JLabel("Manage Employees", SwingConstants.CENTER);
        titleLabel.setFont(font);
        panel.add(titleLabel, BorderLayout.NORTH);

        managePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);
        gridBag.gridx = 0;
        gridBag.gridy = 0;

        Dimension dimension = new Dimension(300, 200);
        viewButton = new JButton("View Employees");
        viewButton.setFont(font);
        viewButton.setPreferredSize(dimension);
        viewButton.addActionListener(e -> showCard("EmployeesView"));
        managePanel.add(viewButton, gridBag);

        gridBag.gridx = 1;
        addEmpButton = new JButton("Add Employee");
        addEmpButton.setFont(font);
        addEmpButton.setPreferredSize(dimension);
        addEmpButton.addActionListener(e -> showCard("AddEmp"));

        managePanel.add(addEmpButton, gridBag);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));

        panel.add(backButton, BorderLayout.SOUTH);
        panel.add(managePanel, BorderLayout.CENTER);

        return panel;
    }

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }

    public void setViewEmployees(Employee emp) 
    {
        this.emp = emp;
    }
}
