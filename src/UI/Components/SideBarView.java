package UI.Components;
import Logic.Users.UserController;
import UI.HomePage;
import UI.LoginPage;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.io.IOException;


public class SideBarView extends JPanel {

    String userName;
    String userRole;

    String loadManageProjectIcon = "src/icons/projectManager.png";
    String loadManageUserIcon = "src/icons/userManager.png";
    String loadReportIcon = "src/icons/reports.png";
    String loadSettingIcon = "src/icons/setting.png";
    String loadUserIcon = "src/icons/user.png";
    String loadLogoutIcon = "src/icons/logout.png";
    String loadHomeIcon = "src/icons/home.png";
    String loadNIcon = "src/icons/n.png";


    ImageIcon projectIcon = new ImageIcon(loadManageProjectIcon);
    Image project = projectIcon.getImage();
    Image scaleProject = project.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setProjectIcon = new ImageIcon(scaleProject);

    ImageIcon userIcon = new ImageIcon(loadManageUserIcon);
    Image user = userIcon.getImage();
    Image scaleUser = user.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setUsersIcon = new ImageIcon(scaleUser);

    ImageIcon reportIcon = new ImageIcon(loadReportIcon);
    Image report = reportIcon.getImage();
    Image scaleReport = report.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setReportIcon = new ImageIcon(scaleReport);

    ImageIcon settingIcon = new ImageIcon(loadSettingIcon);
    Image setting = settingIcon.getImage();
    Image scaleSetting = setting.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setSettingIcon = new ImageIcon(scaleSetting);

    ImageIcon logoutIcon = new ImageIcon(loadLogoutIcon);
    Image logout = logoutIcon.getImage();
    Image scaleLogout = logout.getScaledInstance(27, 27,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setLogoutIcon = new ImageIcon(scaleLogout);

    ImageIcon homeIcon = new ImageIcon(loadHomeIcon);
    Image home = homeIcon.getImage();
    Image scaleHome = home.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setHomeIcon = new ImageIcon(scaleHome);

    ImageIcon nIcon = new ImageIcon(loadNIcon);
    Image n = nIcon.getImage();
    Image scaleN = n.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setNIcon = new ImageIcon(scaleN);

    ImageIcon usIcon = new ImageIcon(loadUserIcon);
    Image u = usIcon.getImage();
    Image scaleu = u.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon setUserIcon = new ImageIcon(scaleu);

    JButton manageProjectsButton = new JButton(setProjectIcon);
    JButton manageUsersButton = new JButton(setUsersIcon);
    JButton reportsButton = new JButton(setReportIcon);
    JButton settingButton = new JButton(setSettingIcon);

    UserController userController;

    public SideBarView(String userName, String userRole) throws IOException {
        this.userName = userName;
        this.userRole = userRole;
        setBounds(30,30,220,728);
        setLayout(null);
        setVisible(true);

        JLabel roleOfUser = new JLabel(userRole);
        roleOfUser.setBounds((getWidth() - roleOfUser.getPreferredSize().width) / 3, 100, 160, 20);
        roleOfUser.setFont(new Font("Poppins", Font.BOLD, 18));
        add(roleOfUser);

        JLabel nameOfUser = new JLabel(userName);
        nameOfUser.setBounds((getWidth() - nameOfUser.getPreferredSize().width) / 3, 130, 140, 20);
        nameOfUser.setAlignmentX(CENTER_ALIGNMENT);
        nameOfUser.setFont(new Font("Poppins", Font.BOLD, 15));
        add(nameOfUser);

        manageProjectsButton.setLayout(null);
        manageProjectsButton.setBounds((getWidth() / 2) - (200 / 2), 200, 300, 80);
        manageProjectsButton.setHorizontalAlignment(SwingConstants.LEFT);
        manageProjectsButton.setFocusPainted(false);
        manageProjectsButton.setBorderPainted(false);
        manageProjectsButton.setToolTipText("Show/Edit projects");

        JLabel projectsHint = new JLabel("Manage Projects");
        projectsHint.setBounds((getWidth() / 2) - (115 / 2) + 25, 235, 115, 17);
        projectsHint.setFont(new Font("Poppins", Font.BOLD, 13));
        projectsHint.setHorizontalAlignment(SwingConstants.CENTER);
        projectsHint.setVisible(false);
        add(projectsHint);
        add(manageProjectsButton);
        manageProjectsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                projectsHint.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                projectsHint.setVisible(false);
            }
        });

        manageUsersButton.setLayout(null);
        manageUsersButton.setBounds((getWidth() / 2) - (200 / 2), 300, 200, 80);
        manageUsersButton.setHorizontalAlignment(SwingConstants.LEFT);
        manageUsersButton.setFocusPainted(false);
        manageUsersButton.setBorderPainted(false);
        manageUsersButton.setToolTipText("Show/Edit users");


        JLabel usersHint = new JLabel("Manage Users");
        usersHint.setFont(new Font("Poppins", Font.BOLD, 13));
        usersHint.setBounds((getWidth() / 2) - (200 / 2) + 70, 335, 105, 17);
        usersHint.setHorizontalAlignment(SwingConstants.CENTER);
        usersHint.setVisible(false);
        add(usersHint);
        add(manageUsersButton);
        manageUsersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                usersHint.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                usersHint.setVisible(false);
            }
        });


        reportsButton.setLayout(null);
        reportsButton.setBounds((getWidth() / 2) - (200 / 2), 400, 200, 80);
        reportsButton.setHorizontalAlignment(SwingConstants.LEFT);
        reportsButton.setFocusPainted(false);
        reportsButton.setBorderPainted(false);
        reportsButton.setToolTipText("Show users/projects reports");


        JLabel reportsHint = new JLabel("Reports");
        reportsHint.setBounds((getWidth() / 2) - (200 / 2) + 70, 435, 60, 17);
        reportsHint.setFont(new Font("Poppins", Font.BOLD, 13));
        reportsHint.setHorizontalAlignment(SwingConstants.CENTER);
        reportsHint.setVisible(false);
        add(reportsHint);
        add(reportsButton);
        reportsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                reportsHint.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                reportsHint.setVisible(false);
            }
        });


        settingButton.setLayout(null);
        settingButton.setBounds((getWidth() / 2) - (200 / 2), 500, 200, 80);
        settingButton.setHorizontalAlignment(SwingConstants.LEFT);
        settingButton.setFocusPainted(false);
        settingButton.setBorderPainted(false);
        settingButton.setToolTipText("Setting");

        JLabel settingHint = new JLabel("Setting");
        settingHint.setFont(new Font("Poppins", Font.BOLD, 13));
        settingHint.setBounds((getWidth() / 2) - (200 / 2) + 70, 535, 60, 17);
        settingHint.setHorizontalAlignment(SwingConstants.CENTER);
        settingHint.setVisible(false);
        add(settingHint);
        add(settingButton);
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingHint.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingHint.setVisible(false);
            }
        });

        JButton logoutButton = new JButton(setLogoutIcon);
        logoutButton.setLayout(null);
        logoutButton.setBounds(27, 640, 30, 70);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                setVisible(false);
                userController.user = null;
                repaint();
            }
        });
        JLabel logoutHint = new JLabel("Logout");
        logoutHint.setFont(new Font("Poppins", Font.BOLD, 13));
        logoutHint.setBounds(15, 630, 55, 17);
        logoutHint.setHorizontalAlignment(SwingConstants.CENTER);
        logoutHint.setVisible(false);
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoutHint.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutHint.setVisible(false);
            }
        });
        add(logoutHint);
        add(logoutButton);

        JButton homePageButton = new JButton(setHomeIcon);
        homePageButton.setLayout(null);
        homePageButton.setBounds(60, 640, 70, 70);
        homePageButton.setFocusPainted(false);
        homePageButton.setBorderPainted(false);
        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HomePage homePage = new HomePage(userController);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
                repaint();
            }
        });

        JLabel homeHint = new JLabel("Home");
        homeHint.setBounds(70, 630, 55, 17);
        homeHint.setFont(new Font("Poppins", Font.BOLD, 13));
        homeHint.setHorizontalAlignment(SwingConstants.CENTER);
        homeHint.setVisible(false);
        homePageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                homeHint.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                homeHint.setVisible(false);
            }
        });
        add(homeHint);
        add(homePageButton);

        JButton nButton = new JButton(setNIcon);
        nButton.setLayout(null);
        nButton.setBounds(135, 650, 100, 50);
        nButton.setFocusPainted(false);
        nButton.setBorderPainted(false);
        nButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://nizek.com"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(nButton);

        JButton userButton = new JButton(setUserIcon);
        userButton.setLayout(null);
        userButton.setBounds((getWidth() / 2) - (40 / 2), 35, 40, 50);
        userButton.setFocusPainted(false);
        userButton.setBorderPainted(false);
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(userButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        Color startColor = new Color(140, 165, 186);
        Color endColor = new Color(189, 219, 218);
        int startX = 0;
        int startY = 0;
        int endX = getWidth();
        int endY = getHeight();

        GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);

        int cornerRadius = 60;
        int width = getWidth();
        int height = getHeight();

        GeneralPath roundedRectangle = new GeneralPath();
        roundedRectangle.moveTo(2 + cornerRadius, 2);
        roundedRectangle.lineTo(width - 3, 2);
        roundedRectangle.lineTo(width - 3, height - 20);
        roundedRectangle.lineTo(2 + cornerRadius, height - 20);
        roundedRectangle.quadTo(2, height - 20, 2, height - 20 - cornerRadius);
        roundedRectangle.lineTo(2, 2 + cornerRadius);
        roundedRectangle.quadTo(2, 2, 2 + cornerRadius, 2);
        roundedRectangle.closePath();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(gradient);
        g2d.fill(roundedRectangle);

        float strokeWidth = 4.0f;
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(Color.BLACK);
        g2d.draw(roundedRectangle);

        g2d.dispose();
    }

    public void addManageProjectButtonAction(ActionListener e) {
        manageProjectsButton.addActionListener(e);
    }

    public void addManageUserButtonAction(ActionListener e) {
        manageUsersButton.addActionListener(e);
    }

    public void addManageReportsButtonAction(ActionListener e) {
        reportsButton.addActionListener(e);
    }

    public void addSettingButtonAction(ActionListener e) {
        settingButton.addActionListener(e);
    }

}
