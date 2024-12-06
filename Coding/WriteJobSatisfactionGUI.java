import java.awt.*;
import javax.swing.*;

public class WriteJobSatisfactionGUI 
{

    private Employee emp;
    private JPanel parentPanel, panel, formPanel;
    private JLabel titleLabel, feedbackLabel, suggestionLabel, ratingLabel;
    private JTextArea feedbackArea, suggestionArea;
    private StarRatingPanel starPanel;
    private JButton backButton, saveButton;
    private RepeatFormat repeat = new RepeatFormat();
    private Font font = repeat.getTextFont();

    public WriteJobSatisfactionGUI(JPanel parentPanel, Employee emp) 
    {
        this.parentPanel = parentPanel;
        this.emp = emp;
    }

    public JPanel createPanel() {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#2a5490")); // Alice blue

        // Title label
        titleLabel = new JLabel("Job Satisfaction Survey", SwingConstants.CENTER);
        titleLabel.setFont(repeat.getTitleFont());
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        // GridBagLayout for form content
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        // Feedback field
        feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(feedbackLabel, gridBag);

        feedbackArea = new JTextArea(3, 20);
        feedbackArea.setFont(font);
        JScrollPane feedbackScroll = new JScrollPane(feedbackArea);
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        formPanel.add(feedbackScroll, gridBag);

        // Suggestions field
        suggestionLabel = new JLabel("Suggestions:");
        suggestionLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        formPanel.add(suggestionLabel, gridBag);

        suggestionArea = new JTextArea(3, 20);
        suggestionArea.setFont(font);
        JScrollPane suggestionScroll = new JScrollPane(suggestionArea);
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        formPanel.add(suggestionScroll, gridBag);

        // Star rating panel
        ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(font);
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        formPanel.add(ratingLabel, gridBag);

        starPanel = new StarRatingPanel();
        gridBag.gridx = 0;
        gridBag.gridy = 5;
        formPanel.add(starPanel, gridBag);

        // Save button
        saveButton = new JButton("Save");
        saveButton.setFont(font);
        saveButton.setBackground(Color.decode("#2A5490"));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> {
            try {
                String feedback = feedbackArea.getText();
                String suggestion = suggestionArea.getText();
                int rating = starPanel.getRating();
                String employeeId = emp.getId(); // Example employee ID // replace with this emp.getId();

                // Validate input
                if (feedback.isEmpty() || suggestion.isEmpty()) {
                    throw new IllegalArgumentException("Feedback and suggestions cannot be empty.");
                }

                // Save job satisfaction data
                JobSatisfaction jobSatisfaction = new JobSatisfaction(employeeId, suggestion, feedback, rating);
                jobSatisfaction.saveToFile();

                JOptionPane.showMessageDialog(panel, "Job satisfaction saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gridBag.gridx = 0;
        gridBag.gridy = 6;
        formPanel.add(saveButton, gridBag);

        panel.add(formPanel, BorderLayout.CENTER);

      // Back button with Sea Fun styling
      backButton = new JButton("Back");
      backButton.setFont(font);
      backButton.setBackground(Color.decode("#2A5490"));  // Sea Fun button color
      backButton.setForeground(Color.WHITE);  // White text
      backButton.setFocusPainted(false);
      backButton.addActionListener(e -> showCard("UpdateFeedback"));
      panel.add(backButton, BorderLayout.SOUTH);

    

      return panel;
    }

    // Star rating panel
    class StarRatingPanel extends JPanel {
        private int rating = 0;
        private final int STAR_COUNT = 5;

        private static final Color GOLD_COLOR = new Color(255, 215, 0);
        private static final Color GRAY_COLOR = Color.GRAY;

        public StarRatingPanel() {
            setPreferredSize(new Dimension(250, 50));
            setLayout(new FlowLayout());
            setBackground(Color.WHITE);
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    handleStarClick(evt);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (int i = 0; i < STAR_COUNT; i++) {
                int x = 40 * i + 10;
                int y = 10;
                if (i < rating) {
                    g2d.setColor(GOLD_COLOR);
                } else {
                    g2d.setColor(GRAY_COLOR);
                }
                drawStar(g2d, x, y);
            }
        }

        private void drawStar(Graphics2D g2d, int x, int y) {
            int[] xPoints = {x + 20, x + 25, x + 40, x + 30, x + 35, x + 20, x + 5, x + 10, x, x + 15};
            int[] yPoints = {y, y + 15, y + 15, y + 25, y + 40, y + 30, y + 40, y + 25, y + 15, y + 15};
            g2d.fillPolygon(xPoints, yPoints, 10);
        }

        private void handleStarClick(java.awt.event.MouseEvent evt) {
            int clickedStar = (evt.getX() - 10) / 40;
            if (clickedStar >= 0 && clickedStar < STAR_COUNT) {
                rating = clickedStar + 1;
                repaint();
            }
        }

        public int getRating() {
            return rating;
        }
    }

    private void showCard(String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}
