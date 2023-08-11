package UI.Components;

import UI.HomePage;
import Utils.PanelsGradient;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

public class ToolBarView extends JPanel {
    HomePage homePage;
    private final int cornerRadius = 60;

    public ToolBarView(String panelName) {

        setBounds(250, 30, 819, 50);
        setOpaque(false);
        setLayout(null);
        setVisible(true);

        JLabel currentPanel = new JLabel(panelName);
        currentPanel.setBounds((getWidth()/2) - (100 / 2), 20,100, 20);
        currentPanel.setFont(new Font("Poppins", Font.BOLD, 20));
        add(currentPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(65, 90, 119), 0, height, new Color(119, 141, 169));
        g2d.setPaint(gradientPaint);

        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(0, height);
        path.lineTo(width, height);
        path.lineTo(width, cornerRadius);
        path.quadTo(width, 0, width - cornerRadius, 0);
        path.lineTo(cornerRadius, 0);
        path.closePath();
        g2d.fill(path);

        g2d.dispose();
    }

}
