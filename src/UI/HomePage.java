package UI;
import UI.Components.MainView;
import UI.Components.SideBarView;
import UI.Components.ToolBarView;
import UI.ManageProjects.ManageProjects;
import UI.ManageUser.ManageUsers;
import UI.Reports.Reports;
import UI.Setting.Setting;
import Logic.Users.UserController;
import Utils.PanelsGradient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                }
                currentShowingPanel = manageProjects;

                add(manageProjects);
                mainView.setVisible(false);
            }
        });

//        sideBar.addManageBoardsButtonAction(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ManageBoards manageBoards = new ManageBoards();
//                if (currentShowingPanel != null) {
//                    currentShowingPanel.setVisible(false);
//                }
//                currentShowingPanel = manageBoards;
//
//                add(manageBoards);
//                mainView.setVisible(false);
//            }
//        });

        sideBar.addManageUserButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUsers manageUsers = new ManageUsers(userController);
                if (currentShowingPanel != null) {
                    currentShowingPanel.setVisible(false);
                }
                currentShowingPanel = manageUsers;

                add(manageUsers);
                mainView.setVisible(false);
            }
        });

        sideBar.addManageReportsButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reports reports = new Reports();
                if (currentShowingPanel != null) {
                    currentShowingPanel.setVisible(false);
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
                }
                currentShowingPanel = setting;

                add(setting);
                mainView.setVisible(false);
            }
        });

        ToolBarView toolBar = new ToolBarView();
        add(toolBar);
    }
}