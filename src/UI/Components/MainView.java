package UI.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class MainView extends JPanel {

    private final int cornerRadius = 60;

    public MainView() {

//    Border borderLine = BorderFactory.createLineBorder(Color.black);
//        setBorder(borderLine);
//        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5)));
        setBounds(250,80,819,660);
        setLayout(null);
//        setBackground(new Color(214, 204, 194));
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
        path.quadTo(width, height, width - cornerRadius, height);
        path.lineTo(0, height);
        path.closePath();
        g2d.fill(path);

        g2d.dispose();
    }


}
