package UI.Reports;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

public class ManageReports extends JPanel {

    private final int cornerRadius = 60;

    public ManageReports(){

        setBounds(248, 80, 820, 660);
        setLayout(null);
        setVisible(true);

        JButton doneIssuesButton = new JButton("Done Issues");
        doneIssuesButton.setBounds(28,28,170,170);
        doneIssuesButton.setLayout(null);
        add(doneIssuesButton);
        doneIssuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DoneIssues();
            }
        });

        JButton rejectedIssuesButton = new JButton("Rejected Issues");
        rejectedIssuesButton.setBounds(226,28,170,170);
        rejectedIssuesButton.setLayout(null);
        add(rejectedIssuesButton);
        rejectedIssuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RejectedIssues();
            }
        });

        JButton compareButton = new JButton("Compare Users");
        compareButton.setBounds(424,28,170,170);
        compareButton.setLayout(null);
        add(compareButton);
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CompareUsers();
            }
        });

        JButton button4 = new JButton();
        button4.setBounds(622,28,170,170);
        button4.setLayout(null);
        add(button4);
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
