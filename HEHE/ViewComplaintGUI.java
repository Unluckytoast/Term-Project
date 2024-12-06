import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ViewComplaintGUI implements ActionListener 
{
    private JPanel parentPanel, panel, searchBarPanel;
    private JLabel titleLabel;
    private JButton backButton, searchButton, selectButton;
    private Employee emp;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    ViewComplaintGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() {
        panel = new JPanel(new GridBagLayout()); // Changed to GridBagLayout
        panel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10); // Padding between components
        gridBag.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        titleLabel = new JLabel("View Complaints", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2; // Spanning across 2 columns
        panel.add(titleLabel, gridBag);

        // Add search bar
        searchBarPanel = createSearchBar();
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2; // Spanning across 2 columns
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

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("ViewFeedback"));
        gridBag.gridx = 1; // Position in the second column
        gridBag.gridy = 3;
        gridBag.gridwidth = 1; // Single column
        gridBag.weightx = 0; // No resizing for the button
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.LINE_END; // Align to the right
        panel.add(backButton, gridBag);

        return panel;
    }

    private JPanel createSearchBar() 
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
        selectButton = new JButton("Select");

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

        // Action listener for the "Select" button
        selectButton.addActionListener(e -> 
        {
            String selectedItem = (String) suggestionsDropdown.getSelectedItem();
            if (selectedItem != null) 
            {
                searchField.setText(selectedItem); // Set selected item to search field
                suggestionsDropdown.setVisible(false); // Hide dropdown after selection
            } 
            else 
            {
            JOptionPane.showMessageDialog(panel, "Please select an item from the dropdown", "Error", JOptionPane.ERROR_MESSAGE);
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

    @Override
    public void actionPerformed(ActionEvent e) {}
}
