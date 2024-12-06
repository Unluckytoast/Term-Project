import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class EmployeeViewGUI 
{
    private JPanel parentPanel, panel, empViewPanel, searchBarPanel;
    private JLabel titleLabel;
    private JButton backButton, searchButton;
    private Employee emp;
    private GridBagConstraints gridBag = new GridBagConstraints();
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    EmployeeViewGUI(JPanel parentPanel, Employee emp)
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel()
    {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        //empViewPanel = new JPanel(new GridBagLayout());
        gridBag.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("View Employees", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2;
        panel.add(titleLabel, gridBag);

        searchBarPanel = createSearchBar();
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        panel.add(searchBarPanel, gridBag);

        // Scroll Pane for complaint content
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.gridwidth = 2; // Spanning across 2 columns
        gridBag.weightx = 1.0; // Allow resizing horizontally
        gridBag.weighty = 1.0; // Allow resizing vertically
        gridBag.fill = GridBagConstraints.BOTH; // Stretch in both directions
        panel.add(scrollPane, gridBag);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));
        gridBag.gridx = 1;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.weightx = 0;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.LINE_END;

        panel.add(backButton, gridBag);

        return panel;
    }

    public JPanel createSearchBar() 
    {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.LIGHT_GRAY);

        // Search field
        JTextField searchField = new JTextField(20);

        // Dropdown for suggestions
        JComboBox<String> suggestionsDropdown = new JComboBox<>();
        suggestionsDropdown.setEditable(false);
        suggestionsDropdown.setVisible(false);

        // Mock data for suggestions //CHANGE
        List<String> mockData = new ArrayList<>();
        mockData.add("Complaint 1");
        mockData.add("Complaint 2");
        mockData.add("Customer Service Issue");
        mockData.add("Technical Support Query");
        mockData.add("Comtent");


        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        suggestionsDropdown.setModel(model);

        // Search button
        searchButton = new JButton("Search");

        // Add components to the panel
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(suggestionsDropdown); // Add dropdown to the panel
        searchPanel.add(searchButton);
        searchPanel.add(searchButton);

        // Action listener to handle dynamic suggestions
        searchField.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e) 
            {
                String query = searchField.getText().trim().toLowerCase();
                model.removeAllElements(); // Clear the dropdown model
                boolean found = false;
                for (String item : mockData) 
                {
                    if (item.toLowerCase().contains(query)) 
                    {
                        model.addElement(item); // Add matching items
                        found = true;
                    }
                }
                suggestionsDropdown.setVisible(found); // Show if matches exist
            }
        });
        

        // Action listener for dropdown selection
        suggestionsDropdown.addActionListener(e -> 
        {
            // Ensure it's a user-initiated selection and not programmatic changes
            if (suggestionsDropdown.isPopupVisible() && suggestionsDropdown.getSelectedItem() != null) 
            { 
                searchField.setText(suggestionsDropdown.getSelectedItem().toString()); // Set selected item to search field
                suggestionsDropdown.setVisible(false); // Hide dropdown after selection
            }
        });

        // Add action listener to handle search
        ActionListener searchAction = e -> 
        {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) 
            {
                JOptionPane.showMessageDialog(panel, "You searched for: " + query);
            } 
            else 
            {
                JOptionPane.showMessageDialog(panel, "Search query cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        // Attach the action listener to the button and field
        searchButton.addActionListener(searchAction);
        searchField.addActionListener(searchAction);

        return searchPanel;
    }

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
