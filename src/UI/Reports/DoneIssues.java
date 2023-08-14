package UI.Reports;
import Logic.Users.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DoneIssues extends JFrame{

    ReportController reportController = new ReportController();

    JPanel mainPanel = new JPanel();
    JPanel listPanel = new JPanel();
    DefaultListModel<String> listModel = new DefaultListModel<>();
    HashMap<Object, User> users;
    JFreeChart chart;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    ChartPanel chartPanel;

    public DoneIssues(ReportController reportController){
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
                    dataset.addValue(countOfDoneIssues, "Done Issues", user.getName());
                    if (selectedUsers.size() == 1) {
                        chart.setTitle("Displaying Done Issues for " + user.getName());
                    } else {
                        chart.setTitle("Displaying Done Issues for " + selectedUsers.size() + " Users");
                    }
                }
            }
        });

        chart = ChartFactory.createBarChart(
                "Done Issues per User",
                "User",
                "Done Issues Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(10, 10, 780, 400);
        chartPanel.setVisible(true);
        chartPanel.setLayout(null);
        mainPanel.add(chartPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,800);
        setBounds(0, 0, 800, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        mainPanel.setLayout(null);
        mainPanel.setBounds(0,0,800,800);
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setVisible(true);
        add(mainPanel);

        listPanel.setLayout(null);
        listPanel.setBounds(0,600,800,200);
        listPanel.setBackground(Color.DARK_GRAY);
        listPanel.setVisible(true);
        mainPanel.add(listPanel);

        usersList.setBounds(10,10,780,150);
        usersList.setVisible(true);
        listPanel.add(usersList);


        for (Map.Entry<Object, User> entry : users.entrySet()) {
            User user = entry.getValue();

                listModel.addElement(user.getName());

        }


    }
}
