package UI.Reports;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;

public class DoneIssues extends JFrame implements TableModel {

    JPanel mainPanel = new JPanel();
    JPanel tablePanel = new JPanel();
    JTable usersTable = new JTable();

    public DoneIssues(){

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

        tablePanel.setLayout(null);
        tablePanel.setBounds(0,600,800,200);
        tablePanel.setBackground(Color.DARK_GRAY);
        tablePanel.setVisible(true);
        mainPanel.add(tablePanel);

        usersTable.setBounds(10,10,780,150);
        usersTable.setVisible(true);
        tablePanel.add(usersTable);
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
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
