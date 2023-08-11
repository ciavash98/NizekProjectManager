package UI.ManageIssues;
import Logic.Boards.Board;
import Logic.Issues.*;
import Logic.Projects.Project;
import Logic.Users.Roles;
import Logic.Users.User;
import Logic.Users.UserController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddIssue extends JFrame {
    IssueStatus issueStatus;
    IssueController issueController = new IssueController();
    IssuePriority issuePriority;
    UserController userController = new UserController();
    Issue issue;
    public AddIssue(Project project, Board board) {

        JPanel mainPanel = new JPanel();
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel.setBounds(0,0,500,500);
        mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        add(mainPanel);

        JLabel titleLabel = new JLabel("Enter Title");
        titleLabel.setBounds(90, 25, 100, 20);
        mainPanel.add(titleLabel);

        JTextField titleField = new JTextField();
        titleField.setVisible(true);
        titleField.setBounds(160,20,200,30);
        mainPanel.add(titleField);

        JLabel priorityLabel = new JLabel("Set Priority :");
        priorityLabel.setBounds(20, 65, 100, 20);
        mainPanel.add(priorityLabel);

        JComboBox priorityComboBox = new JComboBox<>();
        priorityComboBox.setBounds(110,65,150,25);
        priorityComboBox.addItem(IssuePriority.HIGH);
        priorityComboBox.addItem(IssuePriority.MEDIUM);
        priorityComboBox.addItem(IssuePriority.LOW);
        mainPanel.add(priorityComboBox);

        JLabel devLabel = new JLabel("Assign:");
        devLabel.setBounds(280, 65, 100, 20);
        mainPanel.add(devLabel);

        JComboBox devComboBox = new JComboBox<>(userController.filter(Roles.DEVELOPER).values().toArray());
        devComboBox.setBounds(335,65,150,25);
        mainPanel.add(devComboBox);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(280, 95, 100, 20);
        mainPanel.add(typeLabel);

        JComboBox typeComboBox = new JComboBox<>();
        typeComboBox.addItem(IssueType.STORY);
        typeComboBox.addItem(IssueType.TASK);
        typeComboBox.addItem(IssueType.BUG);
        typeComboBox.setBounds(335,95,150,25);
        mainPanel.add(typeComboBox);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(20, 105, 100, 20);
        mainPanel.add(descriptionLabel);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBounds(20, 130, 460, 290);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        mainPanel.add(descriptionArea);

        JButton createIssue = new JButton("Create Issue");
        createIssue.setBounds(150,430,200,30);
        createIssue.setVisible(true);
        mainPanel.add(createIssue);
        createIssue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                IssuePriority priority = (IssuePriority) priorityComboBox.getSelectedItem();
                User user = (User) devComboBox.getSelectedItem();
                IssueType type = (IssueType) typeComboBox.getSelectedItem();
                issueController.addIssue(title,type, description, priority,project, user);
                setVisible(false);
            }
        });
    }
}