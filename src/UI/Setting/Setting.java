package UI.Setting;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Setting extends JPanel {

    Border borderLine = BorderFactory.createLineBorder(Color.black);

    public Setting() {

        setBackground(Color.WHITE);
        setBounds(250,50,850,750);
        setBorder(borderLine);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3)));
        setLayout(null);
        setVisible(true);

    }
}
