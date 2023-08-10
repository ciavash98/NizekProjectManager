package Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransparentButtonExample extends JFrame {
    public TransparentButtonExample() {
        setTitle("Transparent Button Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JButton transparentButton = new JButton("Transparent Button");
        transparentButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        transparentButton.setBackground(new Color(0, 0, 0, 0));
        transparentButton.setForeground(Color.WHITE);

        transparentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Button click action here
            }
        });

        mainPanel.add(transparentButton, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TransparentButtonExample example = new TransparentButtonExample();
            example.setVisible(true);
        });
    }
}
