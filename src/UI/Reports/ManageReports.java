package UI.Reports;
import Logic.Users.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import java.awt.Color;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.awt.geom.Path2D;

public class ManageReports extends JPanel {

    private final int cornerRadius = 60;
    ReportController reportController = new ReportController();
    JPanel listPanel = new JPanel();
    DefaultListModel<String> listModel = new DefaultListModel<>();
    HashMap<Object, User> users;
    JFreeChart chart;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    ChartPanel chartPanel;
    public ManageReports(ReportController reportController){

        setBounds(248, 80, 820, 660);
        setLayout(null);
        setVisible(true);

        this.reportController = reportController;
        users = reportController.getDevelopers();

        HashMap<Object, User> users1 = reportController.getDevelopers();
        DefaultListModel<User> userListModel = new DefaultListModel<>();
        for (Map.Entry<Object, User> entry2 : users1.entrySet()) {
            Object key1 = entry2.getKey();
            User value1 = entry2.getValue();
            userListModel.addElement(value1);
        }
        JList<User> usersList = new JList<>(userListModel);

        usersList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                List<User> selectedUsers = usersList.getSelectedValuesList();
                dataset.clear();
                for (User user : selectedUsers) {
                    int countOfDoneIssues = reportController.userDoneIssues(user.getId());
                    int countOfRejectedIssues = reportController.userRejectedIssues(user.getId());
                    dataset.addValue(countOfDoneIssues, "Done Issues", user.getName());
                    dataset.addValue(countOfRejectedIssues,"Rejected Issues",user.getName());
                    if (selectedUsers.size() == 1) {
                        chart.setTitle("Reports for " + user.getName());
                    } else {
                        chart.setTitle("Reports for " + selectedUsers.size() + " Users");
                    }
                }
            }
        });

        chart = ChartFactory.createBarChart(
                "Please choose a developer",
                "User",
                "Done Issues Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chartPanel = new ChartPanel(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.GRAY);
        renderer.setSeriesPaint(1, Color.RED);
        chartPanel.setBounds(30, 30, 760, 400);
        chartPanel.setVisible(true);
        chartPanel.setLayout(null);
        add(chartPanel);
        setLayout(null);

        listPanel.setLayout(null);
        listPanel.setBounds(30,440,200,200);
        listPanel.setBackground(Color.WHITE);
        listPanel.setVisible(true);
        add(listPanel);

        usersList.setBounds(0,10,780,200);
        usersList.setVisible(true);
        listPanel.add(usersList);

        for (Map.Entry<Object, User> entry : users.entrySet()) {
            User user = entry.getValue();

            listModel.addElement(user.getName());
        }

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
