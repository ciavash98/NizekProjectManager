package UI.Setting;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Path2D;

public class Setting extends JPanel {

    private final int cornerRadius = 60;

    public Setting() {

        setBounds(248, 80, 820, 660);
        setLayout(null);
        setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(224, 225, 221), 0, height, new Color(205, 213, 224));
        g2d.setPaint(gradientPaint);

        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height - cornerRadius);
        path.quadTo(width, height, width - cornerRadius , height);
        path.lineTo(0, height);
        path.closePath();
        g2d.fill(path);

        g2d.dispose();
    }
}
