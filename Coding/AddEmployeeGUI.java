import java.awt.*;
import javax.swing.*;

public class AddEmployeeGUI 
{
    private JPanel parentPanel, panel, addEmpPanel;
    private Employee emp, newEmployee;
    private JLabel titleLabel, nameLabel, raceLabel, ageLabel, addressLabel, contactInfoLabel;
    private Demographics newDemo;
    private JTextField nameText, raceText, ageText, addressText, contactInfoText;
    private JButton backButton;
    
    public AddEmployeeGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel()
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.YELLOW);

        Font font = new Font("Georgia", Font.BOLD, 20);
        titleLabel = new JLabel("Manage Employees", SwingConstants.CENTER);
        titleLabel.setFont(font);
        panel.add(titleLabel, BorderLayout.NORTH);

        addEmpPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        Dimension dimension = new Dimension(200, 30);
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        addEmpPanel.add(nameLabel, gridBag);

        nameText = new JTextField(15);
        nameText.setFont(font);
        nameText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(nameText, gridBag);

        raceLabel = new JLabel("Race:");
        raceLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        addEmpPanel.add(raceLabel, gridBag);

        raceText = new JTextField(15);
        raceText.setFont(font);
        raceText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(raceText, gridBag);

        ageLabel = new JLabel("Age:");
        ageLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        addEmpPanel.add(ageLabel, gridBag);

        ageText = new JTextField(15);
        ageText.setFont(font);
        ageText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(ageText, gridBag);

        addressLabel = new JLabel("Address");
        addressLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        addEmpPanel.add(addressLabel, gridBag);

        addressText = new JTextField(15);
        addressText.setFont(font);
        addressText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(addressText, gridBag);

        contactInfoLabel = new JLabel("Contact Information:");
        contactInfoLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        addEmpPanel.add(contactInfoLabel, gridBag);

        contactInfoText = new JTextField(15);
        contactInfoText.setFont(font);
        contactInfoText.setPreferredSize(dimension);
        gridBag.gridx = 1;
        addEmpPanel.add(contactInfoText, gridBag);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));

        panel.add(backButton, BorderLayout.SOUTH);
        panel.add(addEmpPanel, BorderLayout.CENTER);

        return panel;
    }

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
