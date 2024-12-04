import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WriteSprintEvalGUI 
{
    private JPanel parentPanel, formPanel, panel;
    private JLabel titleLabel, evaluationLabel, ratingLabel;
    private SprintEval sprint;
    private JButton backButton;
    private Employee emp;

    WriteSprintEvalGUI(JPanel parentPanel, Employee emp)
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }
    
    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // Alice blue (light blue)

        // Title label
        titleLabel = new JLabel("Sprint Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 60, 60));  // Dark gray color for the title
        panel.add(titleLabel, BorderLayout.NORTH);

        // GridBagLayout for form content
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255)); // Same background color

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Add a text area for the evaluation content
        evaluationLabel = new JLabel("Evaluation:");
        evaluationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(evaluationLabel, gridBag);

        JTextArea evaluationArea = new JTextArea(3, 20);
        evaluationArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane evaluationScroll = new JScrollPane(evaluationArea);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        formPanel.add(evaluationScroll, gridBag);

        // Add rating panel (with stars)
        ratingLabel = new JLabel("Rating (1-5):");
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        formPanel.add(ratingLabel, gridBag);

        StarRatingPanel starPanel = new StarRatingPanel();
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        formPanel.add(starPanel, gridBag);

        // Button to save the evaluation
        JButton saveButton = new JButton("Save Evaluation");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setBackground(new Color(255, 215, 0));  // Gold background for save button
        saveButton.setForeground(Color.BLACK);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    String evaluationText = evaluationArea.getText();
                    int rating = starPanel.getRating();
                    String supervisorId = emp.getId(); // Example value
                    String employeeId = evaluationArea.getText(); // Example value

                    // Save the evaluation
                    SprintEval eval = new SprintEval(supervisorId, employeeId, evaluationText, rating);
                    eval.saveEvaluation();

                    JOptionPane.showMessageDialog(panel, "Evaluation saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    evaluationArea.setText("");
                    starPanel.repaint();
                } 
                catch (IllegalArgumentException ex) 
                {
                    JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Invalid Rating", JOptionPane.ERROR_MESSAGE);
                } 
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(panel, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gridBag.gridx = 0;
        gridBag.gridy = 4;
        formPanel.add(saveButton, gridBag);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(211, 211, 211)); // Light gray for back button
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    // Custom JPanel to handle the star rating
    class StarRatingPanel extends JPanel 
    {
        private int rating = 0;
        private final int STAR_COUNT = 5;

        // Gold color for filled stars, gray for unfilled
        private static final Color GOLD_COLOR = new Color(255, 215, 0);  // Gold
        private static final Color GRAY_COLOR = Color.GRAY;              // Empty stars

        public StarRatingPanel() 
        {
            setPreferredSize(new Dimension(250, 50));
            setLayout(new FlowLayout());
            setBackground(new Color(240, 248, 255));  // Light blue background
            addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) 
                {
                    handleStarClick(evt);
                }
            });
        }

        // Paint method to draw the stars
        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent(g);

            // Drawing stars
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (int i = 0; i < STAR_COUNT; i++) 
            {
                int x = 40 * i + 10;  // Calculate X position for each star
                int y = 10;  // Fixed Y position
                if (i < rating) 
                {
                    g2d.setColor(GOLD_COLOR);  // Fill color for selected stars (gold)
                } else 
                {
                    g2d.setColor(GRAY_COLOR);    // Empty color for unselected stars
                }
                drawStar(g2d, x, y);
            }
        }

        // Method to draw a star at given position
        private void drawStar(Graphics2D g2d, int x, int y) 
        {
            int[] xPoints = {x + 20, x + 25, x + 40, x + 30, x + 35, x + 20, x + 5, x + 10, x, x + 15};
            int[] yPoints = {y, y + 15, y + 15, y + 25, y + 40, y + 30, y + 40, y + 25, y + 15, y + 15};
            g2d.fillPolygon(xPoints, yPoints, 10);
        }

        // Handle mouse click to set the rating
        private void handleStarClick(java.awt.event.MouseEvent evt) 
        {
            int clickedStar = (evt.getX() - 10) / 40;  // Determine which star was clicked based on X position
            if (clickedStar >= 0 && clickedStar < STAR_COUNT) 
            {
                rating = clickedStar + 1;  // Set rating based on the clicked star (1-5)
                repaint();  // Repaint to update star display
            }
        }

        // Method to get the current rating (based on filled stars)
        public int getRating() 
        {
            return rating;
        }

    }
    
    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
