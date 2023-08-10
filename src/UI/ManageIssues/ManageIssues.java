package UI.ManageIssues;
import javax.swing.JTextArea;
import javax.swing.*;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ManageIssues extends JFrame {
    public ManageIssues() {

        JPanel mainPanel = new JPanel();

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel.setBounds(0, 0, 500, 500);
        mainPanel.setLayout(null);
        add(mainPanel);

        JLabel issueTitle = new JLabel("Issue Title");
        issueTitle.setBounds(200, 20, 100, 20);
        mainPanel.add(issueTitle);

        JLabel priorityLabel = new JLabel("Priority :");
        priorityLabel.setBounds(15, 60, 100, 20);
        mainPanel.add(priorityLabel);

        JTextArea textArea = new JTextArea("Description");
        textArea.setBounds(15, 100, 460, 300);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        mainPanel.add(textArea);

        JButton doneButton = new JButton("Done");
        doneButton.setBounds(190, 420, 100, 30);
        mainPanel.add(doneButton);

        setVisible(true);
    }

}
