import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WriteSprintEvalGUI 
{
    private JPanel parentPanel, formPanel, panel;
    private JLabel titleLabel, evaluationLabel, ratingLabel;
    private JTextArea evaluationArea;
    private StarRatingPanel starPanel;
    private JButton backButton;
    private Employee emp;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    //Constructor
    WriteSprintEvalGUI(JPanel parentPanel, Employee emp)
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }
    
    //Create Write Sprint Evaluation Panel
    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);
        panel.setForeground(Color.white);

        // Title label
        titleLabel = new JLabel("Sprint Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        titleLabel.setForeground(new Color(60, 60, 60));
        panel.add(titleLabel, BorderLayout.NORTH);

        // GridBagLayout for form content
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE); 

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Add a text area for the evaluation content
        evaluationLabel = new JLabel("Evaluation:");
        evaluationLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(evaluationLabel, gridBag);

        evaluationArea = new JTextArea(3, 20);
        evaluationArea.setFont(font);
        JScrollPane evaluationScroll = new JScrollPane(evaluationArea);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        formPanel.add(evaluationScroll, gridBag);

        // Add rating panel (with stars)
        ratingLabel = new JLabel("Rating (1-5):");
        ratingLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        formPanel.add(ratingLabel, gridBag);

        starPanel = new StarRatingPanel();
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        formPanel.add(starPanel, gridBag);

        // Button to save the evaluation
        JButton saveButton = new JButton("Save Evaluation");
        saveButton.setFont(font);
        saveButton.setBackground(Color.decode("#2a5490")); 
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    String evaluationText = evaluationArea.getText();
                    int rating = starPanel.getRating();
                    String supervisorId = emp.getId();
                 

                    // Save the evaluation
                    SprintEval eval = new SprintEval(supervisorId,evaluationText, rating);
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
        backButton.setFont(font);
        backButton.setBackground(Color.decode("#2A5490"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> repeat.showCard(parentPanel, "OpenScreen"));
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
        // Gold
        private static final Color GOLD_COLOR = new Color(255, 215, 0);
        // Empty stars
        private static final Color GRAY_COLOR = Color.GRAY;

        public StarRatingPanel() 
        {
            setPreferredSize(new Dimension(250, 50));
            setLayout(new FlowLayout());
            setBackground(Color.WHITE);
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
                // Calculate X position for each star
                int x = 40 * i + 10;
                // Fixed Y position
                int y = 10;
                if (i < rating) 
                {
                    // Fill color for selected stars (gold)
                    g2d.setColor(GOLD_COLOR);
                } 
                else 
                {
                    // Empty color for unselected stars
                    g2d.setColor(GRAY_COLOR);
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
            // Determine which star was clicked based on X position
            int clickedStar = (evt.getX() - 10) / 40;
            if (clickedStar >= 0 && clickedStar < STAR_COUNT) 
            {
                // Set rating based on the clicked star (1-5)
                rating = clickedStar + 1;
                // Repaint to update star display
                repaint();
            }
        }

        // Method to get the current rating (based on filled stars)
        public int getRating() 
        {
            return rating;
        }

    }
}
