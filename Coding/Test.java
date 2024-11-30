import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test implements ActionListener, KeyboardFunction
{
    private JFrame frame;
    private JPanel mainPanel, loginPanel, centerPanel, topPanel; // Container for all screens
    private CardLayout cardLayout = new CardLayout();
    private JTextField user;
    private JPasswordField password;
    private JLabel messageLabel, userLabel, passwordLabel;
    private JButton loginButton, closeButton;
    private static Employee loggedInEmployee;
    private OpenScreen open;

    public Test() 
    {
        // Initialize frame
        frame = new JFrame("Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        // Use CardLayout to manage different screens
        mainPanel = new JPanel(cardLayout);
        frame.add(mainPanel);

        // Add screens to CardLayout
        mainPanel.add(createLoginScreen(), "LoginScreen");
        mainPanel.add(new OpenScreen(mainPanel).createPanel(), "OpenScreen");
        
        frame.setVisible(true);
    }

    private JPanel createLoginScreen() 
    {
        loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(Color.WHITE);

        topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        closeButton = new JButton("X");
        closeButton.setPreferredSize(new Dimension(50, 30));
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.setForeground(Color.RED);
        closeButton.setFocusPainted(false);
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e -> System.exit(0));
        topPanel.add(closeButton, BorderLayout.WEST);

        loginPanel.add(topPanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.anchor = GridBagConstraints.EAST;
        centerPanel.add(userLabel, gridBag);

        user = new JTextField(15);
        user.setFont(new Font("Arial", Font.PLAIN, 20));
        user.setPreferredSize(new Dimension(200, 30));
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        gridBag.anchor = GridBagConstraints.WEST;
        centerPanel.add(user, gridBag);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.anchor = GridBagConstraints.EAST;
        centerPanel.add(passwordLabel, gridBag);

        password = new JPasswordField(15);
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setPreferredSize(new Dimension(200, 30));
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        gridBag.anchor = GridBagConstraints.WEST;
        centerPanel.add(password, gridBag);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 2;
        gridBag.anchor = GridBagConstraints.CENTER;
        centerPanel.add(messageLabel, gridBag);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        gridBag.gridy = 4;
        centerPanel.add(loginButton, gridBag);

        loginPanel.add(centerPanel, BorderLayout.CENTER);

        KeyboardFunction.bindEscapeKey(loginPanel);
        
        return loginPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == loginButton) 
        {
            HR hr = new HR();
            loggedInEmployee = HRAppTest.login(hr, user.getText());
            if (loggedInEmployee != null) 
            {
                open.setOpenScreenEmployee(loggedInEmployee);
                mainPanel.add(new PersonalInformationGUI(mainPanel, loggedInEmployee).createPanel(), "PersonalInfoScreen");
                mainPanel.add(new SprintEvalGUI(mainPanel, loggedInEmployee).createPanel(), "SprintEvalScreen");
                mainPanel.add(new EmployeesViewGUI(mainPanel, loggedInEmployee).createPanel(), "EmployeesView");
                cardLayout.show(mainPanel, "OpenScreen");
                user.setText("");
                password.setText("");
            } 
            else 
            {
                messageLabel.setText("Incorrect username or password. Please try again.");
                password.setText("");
            }
        }
    }

    public static void main(String[] args) 
    {
        new Test();
    }
}
