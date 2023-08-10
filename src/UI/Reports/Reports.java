package UI.Reports;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Reports extends JPanel {

    Border borderLine = BorderFactory.createLineBorder(Color.black);

    public Reports(){

        setBackground(Color.WHITE);
        setBounds(250,50,850,750);
        setBorder(borderLine);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3)));
        setLayout(null);
        setVisible(true);



    }
}
