package UI.Reports;
import javax.swing.*;
import java.awt.*;

public class RejectedIssues extends JFrame {

    public RejectedIssues(){

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,800);
        setBounds(0, 0, 800, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0,0,800,800);
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setVisible(true);
        add(mainPanel);

    }
}
