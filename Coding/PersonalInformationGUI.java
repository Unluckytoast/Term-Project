import java.awt.*;
import java.util.List;
import javax.swing.*;

class PersonalInformationGUI 
{
    private JPanel panel, parentPanel, jobListPanel, jobPanel, buttonPanel;
    private JLabel titleLabel, jobLabel;
    private JButton backButton, editButton, deleteButton;
    private List<String> pastJobs;
    private JobHistory jobHistory = new JobHistory();

    PersonalInformationGUI(JPanel parentPanel) 
    {
        this.parentPanel = parentPanel;
    }

    public JPanel createPanel() 
    {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        titleLabel = new JLabel("Personal Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        jobListPanel = new JPanel(new GridBagLayout());
        jobListPanel.setBackground(Color.WHITE);
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);

        pastJobs = jobHistory.viewPastJob("j0c1e4q");
        // Add job panels dynamically
        int row = 0;
        for (String job : pastJobs) 
        {
            gridBag.gridx = 0;
            gridBag.gridy = row;
            jobListPanel.add(createJobPanel(job), gridBag);
            row++;
        }

        // Wrap the job list in a scroll pane
        JScrollPane scrollPane = new JScrollPane(jobListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> showCard("OpenScreen"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createJobPanel(String job) 
    {
        jobPanel = new JPanel(new GridBagLayout());
        jobPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jobPanel.setPreferredSize(new Dimension(500, 50));
        jobPanel.setBackground(Color.ORANGE);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        jobLabel = new JLabel(job);
        jobLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        jobPanel.add(jobLabel, gridBag);

        buttonPanel = new JPanel(new FlowLayout());
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        editButton.addActionListener(e -> {
            // Action for edit button
            JOptionPane.showMessageDialog(jobPanel, "Edit job: " + job);
        });

        deleteButton.addActionListener(e -> {
            // Action for delete button
            int response = JOptionPane.showConfirmDialog(jobPanel, "Are you sure you want to delete this job?");
            if (response == JOptionPane.YES_OPTION) {
                jobListPanel.remove(jobPanel);
                jobListPanel.updateUI();
                jobListPanel.revalidate();
                jobListPanel.repaint();
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    
        // Add buttonPanel to the jobPanel
        gridBag.gridx = 1;
        gridBag.gridy = 0;
        jobPanel.add(buttonPanel, gridBag);

        return jobPanel;
    }

    private void showCard(String card) 
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }
}

