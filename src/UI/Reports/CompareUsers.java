package UI.Reports;
import javax.swing.*;
import java.awt.*;

public class CompareUsers extends JFrame {

    public CompareUsers(){

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
