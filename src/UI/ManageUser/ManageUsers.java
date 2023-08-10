package UI.ManageUser;
import Logic.Users.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ManageUsers extends JPanel implements TableModel {
    ChangePassAndRemove changePassAndRemove;
    HashMap<Object, User> users;
    Border borderLine = BorderFactory.createLineBorder(Color.black);
    JTable usersTable = new JTable();
    JComboBox filterComboBox = new JComboBox();
    UserController userController;

    public class ButtonRenderer extends JButton implements TableCellRenderer{

        public ButtonRenderer(String edit){
            setOpaque(true);
            setText(edit);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;

        public ButtonEditor() {
            button = new JButton("Selected");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changePassAndRemove = new ChangePassAndRemove(new UserController(), usersTable.getSelectedRow());
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }
    }

    public ManageUsers(UserController userController) {
        this.userController = userController;
        users = userController.getAllUsers(UserInitKeyBy.ID);
        setBackground(Color.WHITE);
        setBounds(250, 50, 850, 750);
        setBorder(borderLine);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3)));
        setLayout(null);
        setVisible(true);

        usersTable.setModel(this);
        usersTable.setBounds(20, 70, 810, 560);
        usersTable.setLayout(null);
        usersTable.setVisible(true);
        usersTable.setFocusable(false);
        usersTable.setGridColor(Color.gray);
        usersTable.getTableHeader().setBackground(new Color(200, 200, 200));
        usersTable.setRowHeight(30);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setFont(jScrollPane.getFont().deriveFont(Font.BOLD));
        jScrollPane.getViewport().add(usersTable, null);
        jScrollPane.setBounds(20, 70, 810, 560);
        add(jScrollPane);

        DefaultTableCellRenderer cellRenderer1 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component component = super.getTableCellRendererComponent(table, value,
                        false, hasFocus,
                        row, column);
                component.setBackground(Color.WHITE); // Set your desired background color
                return component;
            }
        };

        usersTable.setDefaultRenderer(Object.class, cellRenderer1);


        JComboBox roleComboBox = new JComboBox();
        roleComboBox.addItem(Roles.PRODUCT_OWNER);
        roleComboBox.addItem(Roles.QUALITY_ASSURANCE);
        roleComboBox.addItem(Roles.DEVELOPER);

        TableColumn roleColumn = usersTable.getColumnModel().getColumn(3);
        TableColumn idColumn = usersTable.getColumnModel().getColumn(0);
        idColumn.setPreferredWidth(1);
        roleColumn.setCellEditor(new DefaultCellEditor(roleComboBox));
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        usersTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        usersTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer1);
        filterComboBox.setBounds(16, 20, 300, 40);
        add(filterComboBox);
        filterComboBox.setVisible(true);
        filterComboBox.addItem(Roles.PRODUCT_OWNER);
        filterComboBox.addItem(Roles.QUALITY_ASSURANCE);
        filterComboBox.addItem(Roles.DEVELOPER);
        filterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Roles selectedRole = Roles.findByName(filterComboBox.getSelectedItem().toString());

                if (selectedRole != null) {
                    users = userController.filter(selectedRole);
                    usersTable.updateUI();
                }
            }
        });

        JButton addNewUser = new JButton("+ Add New User");
        addNewUser.setBounds((getWidth() / 2) - (820 / 2), 650, 820, 45);
        addNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUser();
            }
        });
        add(addNewUser);
        TableColumn editColumn = usersTable.getColumnModel().getColumn(4);
        editColumn.setCellEditor(new ButtonEditor());
        editColumn.setCellRenderer(new ButtonRenderer("Change Pass/Remove"));
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Full Name";
            case 2 -> "Email";
            case 3 -> "Role";
            case 4 -> "Edit";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 4 -> JButton.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> false;
            default -> true;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         return switch (columnIndex) {
             case 0 -> users.get(rowIndex).getId();
             case 1 -> users.get(rowIndex).getName();
             case 2 -> users.get(rowIndex).getEmail();
             case 3 -> users.get(rowIndex).getRole();
             case 4 -> users.get(rowIndex).getPass();
             default -> "";
         };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 1: {
                user.setName(aValue.toString());
                users.put(rowIndex, user);
                break;
            }
            case 2: {
                user.setEmail(aValue.toString());
                users.put(rowIndex, user);
                break;
            }
            case 3: {
                user.setRole(Roles.findByName(aValue.toString()));
                users.put(rowIndex, user);
                break;
            }
        }
        UserRepository.saveUsers(users);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}