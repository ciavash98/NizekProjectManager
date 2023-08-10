package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PanelsGradient {

    public static void applyGradientBackground(JPanel panel, Color startColor, Color endColor) {
        panel.setOpaque(false); // Make sure the panel is transparent
        panel.setLayout(new BorderLayout()); // Use a layout manager to ensure components are displayed correctly

        panel.add(new GradientPanel(startColor, endColor), BorderLayout.CENTER);
    }

    private static class GradientPanel extends JPanel {
        private Color startColor;
        private Color endColor;

        public GradientPanel(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int width = getWidth();
            int height = getHeight();

            GradientPaint gradientPaint = new GradientPaint(
                    new Point2D.Float(0, 0), startColor,
                    new Point2D.Float(width, height), endColor);

            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
