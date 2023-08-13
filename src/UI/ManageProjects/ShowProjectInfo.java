package UI.ManageProjects;
import Logic.Boards.Board;
import Logic.Boards.BoardController;
import Logic.Projects.Project;
import Logic.Projects.ProjectController;
import Logic.Users.Roles;
import Logic.Users.User;
import Logic.Users.UserController;
import Logic.Users.UserInitKeyBy;
import UI.ManageBoards.AddBoard;
import UI.ManageBoards.KanbanBoard;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowProjectInfo extends JFrame implements TableModel {

        JTable userTable = new JTable();
        JPanel infoPanel = new JPanel();
        JPanel boardsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        BoardController boardController = new BoardController();
        ProjectController projectController = new ProjectController();
        UserController userController = new UserController();
        Project project;
        ArrayList<User> users = new ArrayList<>();

        private double margin = 23.0;
        private double x = margin;
        private int y = 35;
        private int buttonSize = 170;

    public ShowProjectInfo(Project project) {
        this.project = project;

        setTitle(project.getName());
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);

        userTable.setBounds(30,30,740,260);
        userTable.setVisible(true);
        infoPanel.add(userTable);
        userTable.setModel(this);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setFont(jScrollPane.getFont().deriveFont(Font.BOLD));
        jScrollPane.getViewport().add(userTable, null);
        jScrollPane.setBounds(30,30,740,260);
        infoPanel.add(jScrollPane);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) userTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        userTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        userTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        userTable.setRowHeight(25);

        boardsPanel.setBounds(0, 0, 800, 420);
        boardsPanel.setBackground(Color.LIGHT_GRAY);
        boardsPanel.setVisible(true);
        boardsPanel.setLayout(null);
        add(boardsPanel);

        buttonsPanel.setBounds(0, 420, 800, 40);
        buttonsPanel.setBackground(Color.LIGHT_GRAY);
        buttonsPanel.setVisible(true);
        buttonsPanel.setLayout(null);
        add(buttonsPanel);

        infoPanel.setBounds(0, 460, 800, 340);
        infoPanel.setBackground(Color.GRAY);
        infoPanel.setVisible(true);
        infoPanel.setLayout(null);
        add(infoPanel);

        JScrollPane boardsScrollPane = new JScrollPane(boardsPanel); //Add scroll if boards count is over than 8.
        boardsScrollPane.setBounds(0, 0, 800, 420);
        boardsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        boardsScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        add(boardsScrollPane);

        JButton editProject = new JButton("Edit Project");
        editProject.setBounds(620, 5, 150, 30);
        editProject.setFocusPainted(true);
        editProject.setBorderPainted(false);
        buttonsPanel.add(editProject);
        editProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddBoardPopup();
            }
        });


        JButton addNewBoard = new JButton("+ Add New Board");
        addNewBoard.setBounds(440, 5, 180, 30);
        addNewBoard.setFocusPainted(true);
        addNewBoard.setBorderPainted(false);
        buttonsPanel.add(addNewBoard);
        addNewBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBoard();
            }
        });


        JButton addUser = new JButton("+ Assign User");
        addUser.setBounds(300, 5, 150, 30);
        addUser.setFocusPainted(true);
        addUser.setBorderPainted(false);
        buttonsPanel.add(addUser);
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddUserPopup();
            }
        });

        ArrayList<Board> boards = new ArrayList<>();
        for (int boardId : project.getBoardList()) {
            boards.add(boardController.getBoardById(boardId));
        }

        for (Board b : boards) {
            if (x + buttonSize >= this.getWidth()) {
                y += buttonSize + margin;
                x = margin;
            }

            JButton boardsButton = new JButton(b.getName());
            boardsButton.setBounds((int) x , y, buttonSize, buttonSize);
            boardsPanel.add(boardsButton);
            boardsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new KanbanBoard(project,b);
                }
            });
            x += buttonSize + margin;
        }
        int preferredHeight = (int) (Math.ceil(boards.size() / 4.0) * (buttonSize + margin) + margin);
        boardsPanel.setPreferredSize(new Dimension(800, preferredHeight));


        ArrayList<Integer> pos = new ArrayList<>();

        for (int posId : project.getPosList()) {
            pos.add(posId);
        }
        for (Map.Entry<Object, User> entry : userController.getAllUsers(UserInitKeyBy.ID).entrySet()) {
            Object key = entry.getKey();
            User value = entry.getValue();
            for (int u: pos) {
                if (u == value.getId()){
                    users.add(value);
                }
            }
        }
        userTable.setModel(this);

        ArrayList<Integer> qas = new ArrayList<>();

        for (int qasId : project.getQasList()) {
            qas.add(qasId);
        }
        for (Map.Entry<Object, User> entry1 : userController.getAllUsers(UserInitKeyBy.ID).entrySet()) {
            Object key1 = entry1.getKey();
            User value1 = entry1.getValue();
            for (int u: qas) {
                if (u == value1.getId()){
                    users.add(value1);
                }
            }
        }
        userTable.setModel(this);
    }


    private void showAddBoardPopup() {
        JDialog popup = new JDialog(this, "Edit " + project.getName(), true);
        JPanel popupPanel = new JPanel();
        popupPanel.setLayout(null);

        JLabel editName = new JLabel("Edit name");
        editName.setBounds(50, 60, 100, 30);
        editName.setFont(new Font("Poppins", Font.BOLD, 15));
        popupPanel.add(editName);

        JTextField nameField = new JTextField(project.getName());
        nameField.setBounds(45, 90, 200, 40);
        nameField.setFont(new Font("Poppins", Font.BOLD, 17));
        popupPanel.add(nameField);

        JButton selectButton = new JButton("Done");
        selectButton.setBounds(45, 130, 200, 80);
        selectButton.setFont(new Font("Poppins", Font.BOLD, 17));
//        popupPanel.add(selectButton);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String newName = nameField.getText();
                projectController.editProjectName(project.getId(), newName);
                popup.dispose();
            }
        });
        popupPanel.add(selectButton, BorderLayout.SOUTH);

        JButton deleteButton = new JButton("❌ Delete");
        deleteButton.setBounds(45, 210, 200, 40);
        deleteButton.setFont(new Font("Poppins", Font.BOLD, 17));
        popupPanel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectController.removeProject(project.getId());
                popup.dispose();
                dispose();
            }
        });
        popupPanel.add(selectButton, BorderLayout.SOUTH);

        popup.setPreferredSize(new Dimension(300, 300));
        popup.getContentPane().add(popupPanel);
        popup.pack();
        popup.setLocationRelativeTo(this);
        popup.setVisible(true);
    }

    private void showAddUserPopup() {
        JDialog popup1 = new JDialog(this, "Assign User", true);
        JPanel popupPanel1 = new JPanel(new BorderLayout());


        HashMap<Object, User> users1 = userController.getAllUsers(UserInitKeyBy.ID);
        DefaultListModel<User> userListModel = new DefaultListModel<>();
        for (Map.Entry<Object, User> entry2 : users1.entrySet()) {
            Object key1 = entry2.getKey();
            User value1 = entry2.getValue();
            userListModel.addElement(value1);
            }

        JList<User> userList = new JList<>(userListModel);

        ListCellRenderer<? super User> renderer1 = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setPreferredSize(new Dimension(c.getPreferredSize().width, 25));
                return c;
            }
        };

        userList.setCellRenderer(renderer1);
        popupPanel1.add(new JScrollPane(userList), BorderLayout.CENTER);


        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                User selectedUser1 = userList.getSelectedValue();

                projectController.assign(project.getId(), selectedUser1.getId(), selectedUser1.getRole());
                popup1.dispose();
            }
        });
        popupPanel1.add(selectButton, BorderLayout.SOUTH);

        popup1.setPreferredSize(new Dimension(300, 300));
        popup1.getContentPane().add(popupPanel1);
        popup1.pack();
        popup1.setLocationRelativeTo(this);
        popup1.setVisible(true);
    }



    @Override
    public int getRowCount() {
        return project.getPosList().size() + project.getQasList().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Full Name";
            case 2 -> "Role";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < users.size()) {
            User user = users.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return user.getId();
                case 1:
                    return user.getName();
                case 2:
                    return user.getRole();
                default:
                    return "";
            }
        } else {
            return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
//    ArrayList<Integer> posList = new ArrayList<>();
//    ArrayList<Integer> qasList = new ArrayList<>();
//    ArrayList<Integer> devList = new ArrayList<>();
//                if (selectedUser1 != null) {
//                    for (User po : userList.getSelectedValuesList()) {
//                        if(po.getRole().equals(Roles.PRODUCT_OWNER))
//                            posList.add(po.getId());
//                    }
//                    for (User qa : userList.getSelectedValuesList()) {
//                        if(qa.getRole().equals(Roles.QUALITY_ASSURANCE)){
//                            qasList.add(qa.getId());
//                        }
//                    }
//                    for (User dev : userList.getSelectedValuesList()) {
//                        if(dev.getRole().equals(Roles.DEVELOPER)){
//                            qasList.add(dev.getId());
//                        }
//                    }
//                    project.setPosList(posList);
//                    project.setQasList(qasList);
//                }