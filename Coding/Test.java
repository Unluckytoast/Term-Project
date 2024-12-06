import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test implements ActionListener, KeyboardFunction 
{
    private JFrame frame;
    private JPanel mainPanel, loginPanel, centerPanel, topPanel; // Container for all screens
    private CardLayout cardLayout;
    private JTextField user;
    private JPasswordField password;
    private JLabel messageLabel, userLabel, passwordLabel, logoLabel;
    private JButton loginButton, closeButton, fullScreenButton; // Added button for full screen
    private static Employee loggedInEmployee;
    private OpenScreen open;
    private PersonalInformationGUI person;
    private SprintEvalGUI sprint;
    private ManageEmployeeGUI manage;
    private WriteSprintEvalGUI writeSprint;
    private ViewComplaintGUI complaint;
    private EmployeesViewGUI view;
    private EditEmployeeGUI editEmp;
    private AddEmployeeGUI addEmp;
    private AddEmployeeGUI addEmp2;
    private WriteComplaintGUI writeComp;
    private ViewJobSatisfactionGUI viewSatis;
    private WriteJobSatisfactionGUI writeSatis;
    private UpdateFeedbackGUI updateFeedback;
    private ViewFeedbackGUI viewFeedback;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();
    private boolean isFullScreen = false;

    // Constructor
    public Test() 
    {
        // Initialize frame
        frame = new JFrame("Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);  // Remove window borders
        frame.setSize(800, 800);     // Set initial size

        // Add action to toggle full-screen mode
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11) { // Press F11 to toggle full screen
                    toggleFullScreen();
                }
            }
        });

        // Use CardLayout to manage different screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add screens to CardLayout
        mainPanel.add(createLoginScreen(), "LoginScreen");

        frame.add(mainPanel);

        cardLayout.show(mainPanel, "LoginScreen");

        frame.setVisible(true);
    }

    private JPanel createLoginScreen() 
    {
        loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(Color.WHITE); // Set background to white for Sea Fun theme

        topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        //Create a close button in the top corner and add it to the panel
        closeButton = new JButton("X");
        closeButton.setPreferredSize(new Dimension(50, 30));
        closeButton.setFont(font);
        closeButton.setForeground(Color.RED);
        closeButton.setFocusPainted(false);
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e -> System.exit(0));
        topPanel.add(closeButton, BorderLayout.WEST);

        // Create Full-Screen Button
        fullScreenButton = new JButton("+");
        fullScreenButton.setPreferredSize(new Dimension(100, 30));
        fullScreenButton.setFont(font);
        fullScreenButton.setBackground(Color.decode("#2A5490"));
        fullScreenButton.setForeground(Color.WHITE);
        fullScreenButton.setFocusPainted(false);
        fullScreenButton.addActionListener(e -> toggleFullScreen());
        topPanel.add(fullScreenButton, BorderLayout.EAST); // Add to the right of close button

        loginPanel.add(topPanel, BorderLayout.NORTH);

        // Create the center panel to hold the logo and login fields
        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.decode("#2A5490")); // Sea Fun theme background color
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Add the logo in the center panel
        logoLabel = new JLabel(new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH)));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2;  // Make the logo span across both columns
        centerPanel.add(logoLabel, gridBag);

        // Username label
        userLabel = new JLabel("Username:");
        userLabel.setFont(font);
        userLabel.setForeground(Color.WHITE); // White text
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        centerPanel.add(userLabel, gridBag);

        // Username text field
        user = new JTextField(15);
        user.setFont(font);
        user.setPreferredSize(new Dimension(200, 30));
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        centerPanel.add(user, gridBag);

        // Blue color block under the username
        JPanel userBlueBlock = new JPanel();
        userBlueBlock.setBackground(Color.decode("#2A5490"));
        userBlueBlock.setPreferredSize(new Dimension(200, 5)); // Set the height of the blue block
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        centerPanel.add(userBlueBlock, gridBag);

        // Password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE); // White text
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        centerPanel.add(passwordLabel, gridBag);

        // Password field
        password = new JPasswordField(15);
        password.setFont(font);
        password.setPreferredSize(new Dimension(200, 30));
        gridBag.gridx = 1;
        gridBag.gridy = 3;
        centerPanel.add(password, gridBag);

        // Blue color block under the password
        JPanel passwordBlueBlock = new JPanel();
        passwordBlueBlock.setBackground(Color.decode("#2A5490"));
        passwordBlueBlock.setPreferredSize(new Dimension(200, 5)); // Set the height of the blue block
        gridBag.gridx = 1;
        gridBag.gridy = 4;
        centerPanel.add(passwordBlueBlock, gridBag);

        // Message label for error feedback
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        gridBag.gridx = 0;
        gridBag.gridy = 5;
        gridBag.gridwidth = 2;
        centerPanel.add(messageLabel, gridBag);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(font);
        loginButton.setBackground(Color.WHITE); // Sea Fun button color
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        gridBag.gridy = 6;
        centerPanel.add(loginButton, gridBag);

        loginPanel.add(centerPanel, BorderLayout.CENTER);

        KeyboardFunction.bindEscapeKey(loginPanel);

        return loginPanel;
    }

    //Method that gets action performed, In this 
    //instance it checks for the login button being pressed
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == loginButton) 
        {
            HR hr = new HR();
            loggedInEmployee = HRAppTest.login(hr, user.getText());
            //Checks to see if the user id entered is a legit id
            //If it is, it will let them have access to the rest of the panels
            if (loggedInEmployee != null) 
            {
                //Creates new objects that take in the mainPanel and the new instance of
                //the logged in employee to be used later in the respective classes
                open = new OpenScreen(mainPanel, loggedInEmployee);
                person = new PersonalInformationGUI(mainPanel, loggedInEmployee);
                sprint = new SprintEvalGUI(mainPanel, loggedInEmployee);
                manage = new ManageEmployeeGUI(mainPanel, loggedInEmployee);
                writeSprint = new WriteSprintEvalGUI(mainPanel, loggedInEmployee);
                complaint = new ViewComplaintGUI(mainPanel);
                view = new EmployeesViewGUI(mainPanel, null, hr);
                addEmp = new AddEmployeeGUI(mainPanel, loggedInEmployee);
                addEmp2 = new AddEmployeeGUI(mainPanel, null);
                editEmp = new EditEmployeeGUI(mainPanel, loggedInEmployee);
                writeComp = new WriteComplaintGUI(mainPanel, loggedInEmployee);
                viewSatis = new ViewJobSatisfactionGUI(mainPanel, loggedInEmployee);
                writeSatis = new WriteJobSatisfactionGUI(mainPanel, loggedInEmployee);
                updateFeedback = new UpdateFeedbackGUI(mainPanel);
                viewFeedback = new ViewFeedbackGUI(mainPanel);

                //Uses all of the prior objects to call on all of the 
                //createPanel methods and adds them to the main panel so
                //that the user can switch between all of them
                mainPanel.add(open.createPanel(), "OpenScreen");
                mainPanel.add(person.createPanel(), "PersonalInfoScreen");
                mainPanel.add(sprint.createPanel(), "SprintEvalScreen");
                mainPanel.add(manage.createPanel(), "ManageEmps");
                mainPanel.add(writeSprint.createPanel(), "WriteSprint");
                mainPanel.add(complaint.createPanel(), "ViewComplaint");
                mainPanel.add(view.createPanel(), "EmployeesView");
                mainPanel.add(editEmp.createPanel(!false), "EditEmp");
                mainPanel.add(addEmp.createPanel(), "AddEmp");
                mainPanel.add(writeComp.createPanel(), "WriteComplaint");
                mainPanel.add(viewSatis.createPanel(), "ViewSatisfaction");
                mainPanel.add(writeSatis.createPanel(), "WriteSatisfaction");
                mainPanel.add(updateFeedback.createPanel(), "UpdateFeedback");
                mainPanel.add(viewFeedback.createPanel(), "ViewFeedback");

                frame.revalidate();
                frame.repaint();
                cardLayout.show(mainPanel, "OpenScreen");
                user.setText("");
                password.setText("");
            } 
            else 
            {
                //Gives error message to user to reenter login credentials
                messageLabel.setText("Invalid credentials");
            }
        }
    }

    //Method to toggle full screen
    private void toggleFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (isFullScreen) 
        {
            // Restore to the original size
            frame.setSize(800, 800);
            // Center the window
            frame.setLocationRelativeTo(null);
            // Exit full screen
            gd.setFullScreenWindow(null);
        } 
        else 
        {
            // Enter full screen
            gd.setFullScreenWindow(frame);
        }

        isFullScreen = !isFullScreen;
    }

    public static void main(String[] args)
    {
        //Start the program
        SwingUtilities.invokeLater(() -> new Test());
    }
}
