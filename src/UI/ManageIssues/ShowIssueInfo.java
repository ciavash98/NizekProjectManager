package UI.ManageIssues;

import Logic.Issues.Issue;
import Logic.Issues.IssueController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowIssueInfo extends JFrame {

    IssueController issueController = new IssueController();

    public ShowIssueInfo(Issue issue) {
        super("Issue Details - " + issue.getTitle());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        JLabel priority = new JLabel("Priority: " + issue.getIssuePriority());
        priority.setBounds(10,10,200,20);
        add(priority);

        JLabel developer = new JLabel("developer: " + issue.getAssignedUser());
        developer.setBounds(295,10,200,20);
        add(developer);

        JLabel type = new JLabel("Type: " + issue.getType());
        type.setBounds(160,10,200,20);
        add(type);

        JTextArea description = new JTextArea(issue.getDescription());
        description.setBounds(10, 40,380,180);
        add(description);

        JButton doneButton = new JButton("Done");
        doneButton.setBounds(145,235,100,20);
        doneButton.setVisible(true);
        add(doneButton);
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issueController.editIssue(issue.getId(), description.getText());
                setVisible(false);
            }
        });
    }
}
