package UI;
import UI.Components.MainView;
import UI.Components.SideBarView;
import UI.Components.ToolBarView;
import UI.ManageProjects.ManageProjects;
import UI.ManageUser.ManageUsers;
import UI.Reports.ManageReports;
import UI.Reports.ReportController;
import UI.Setting.Setting;
import Logic.Users.UserController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class HomePage extends JFrame {
    UserController userController;
    JPanel currentShowingPanel;
    JPanel mainPanel = new JPanel();

    public HomePage(UserController userController) throws IOException {
        this.userController = userController;

        setTitle("NGI's Project Manager");
        setFont(new Font("Poppins", Font.BOLD, 13));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        mainPanel.setBounds(0, 0, 250, 800);
        mainPanel.setLayout(null);
        add(mainPanel);

        MainView mainView = new MainView();
        add(mainView);

        SideBarView sideBar = new SideBarView(userController.user.getName(), userController.user.getRole().toString());
        mainPanel.add(sideBar);

        sideBar.addManageProjectButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageProjects manageProjects = new ManageProjects();
                if (currentShowingPanel != null) {
                    currentShowingPanel.setVisible(false);
                    new ToolBarView("Manage Projects");
                }
                currentShowingPanel = manageProjects;

                add(manageProjects);
                mainView.setVisible(false);
            }
        });

        sideBar.addManageUserButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUsers manageUsers = new ManageUsers(userController);
                if (currentShowingPanel != null) {
                    currentShowingPanel.setVisible(false);
                    new ToolBarView("Manage Users");
                }
                currentShowingPanel = manageUsers;

                add(manageUsers);
                mainView.setVisible(false);
            }
        });

        sideBar.addManageReportsButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageReports reports = new ManageReports(new ReportController());
                if (currentShowingPanel != null) {
                    currentShowingPanel.setVisible(false);
                    new ToolBarView("Reports");
                }
                currentShowingPanel = reports;

                add(reports);
                mainView.setVisible(false);
            }
        });

        sideBar.addSettingButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setting setting = new Setting();
                if (currentShowingPanel != null) {
                    currentShowingPanel.setVisible(false);
                    new ToolBarView("Setting");
                }
                currentShowingPanel = setting;

                add(setting);
                mainView.setVisible(false);
            }
        });

        ToolBarView toolBar = new ToolBarView("Home");
        add(toolBar);
    }
}