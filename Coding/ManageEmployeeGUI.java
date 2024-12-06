import java.awt.*;
import javax.swing.*;

public class ManageEmployeeGUI {
    private JPanel parentPanel, panel, managePanel;
    private JLabel titleLabel;
    private JButton addEmpButton, viewButton, backButton;
    private Employee emp;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    ManageEmployeeGUI(JPanel parentPanel, Employee emp) {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // White background for the Sea Fun theme

        // Title label with custom font and white text
        titleLabel = new JLabel("Manage Employees", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        titleLabel.setForeground(Color.BLACK); // Black text for visibility
        panel.add(titleLabel, BorderLayout.NORTH);

        managePanel = new JPanel(new GridBagLayout());
        managePanel.setBackground(Color.WHITE); // White background for the manage panel
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        Dimension dimension = new Dimension(300, 200); // Dimension for buttons

        // View Employees button with Sea Fun theme color
        viewButton = new JButton("View Employees");
        viewButton.setFont(font);
        viewButton.setPreferredSize(dimension);
        viewButton.setBackground(Color.decode("#2A5490")); // Sea Fun button color
        viewButton.setForeground(Color.WHITE); // White text
        viewButton.setFocusPainted(false); // Remove focus highlight
        viewButton.addActionListener(e -> showCard("EmployeesView"));
        managePanel.add(viewButton, gridBag);

        // Add Employee button with Sea Fun theme color
        gridBag.gridx = 1;
        addEmpButton = new JButton("Add Employee");
        addEmpButton.setFont(font);
        addEmpButton.setPreferredSize(dimension);
        addEmpButton.setBackground(Color.decode("#2A5490")); // Sea Fun button color
        addEmpButton.setForeground(Color.WHITE); // White text
        addEmpButton.setFocusPainted(false); // Remove focus highlight
        addEmpButton.addActionListener(e -> showCard("AddEmp"));
        managePanel.add(addEmpButton, gridBag);

        // Back button with Sea Fun theme color
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490")); // Sea Fun button color
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false); // Remove focus highlight
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(managePanel, BorderLayout.CENTER);

        return panel;
    }

    private void showCard(String card) {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }

    public void setViewEmployees(Employee emp) {
        this.emp = emp;
    }
}
