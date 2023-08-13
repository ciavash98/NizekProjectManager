package UI.ManageBoards;
import Logic.Boards.Board;
import Logic.Boards.BoardController;
import Logic.Issues.Issue;
import Logic.Issues.IssueController;
import Logic.Issues.IssueStatus;
import Logic.Projects.Project;
import UI.ManageIssues.AddIssue;
import UI.ManageIssues.ShowIssueInfo;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;


public class KanbanBoard extends JFrame {

    BoardController boardController = new BoardController();
    IssueController issueController = new IssueController();
    ArrayList<Issue> issues;
    IssueTableModel customTableModel;

    public KanbanBoard(Project project, Board board) {

        super("Kanban Board - " + board.getName());
        issues = issueController.getAllIssues();
        setSize(800, 800);
        setLayout(null);
        setBounds(0, 0, 800, 800);
        setLocationRelativeTo(null);

        customTableModel = new IssueTableModel();
        customTableModel.setIssues(issues);
        JTable kanbanTable = new JTable(customTableModel);
        enableDragAndDrop(kanbanTable);
        kanbanTable.setBounds(0, 0, 800, 700);
        kanbanTable.setVisible(true);
        kanbanTable.setFocusable(false);
        kanbanTable.setRowHeight(150);
        kanbanTable.setGridColor(Color.gray);
        kanbanTable.setShowHorizontalLines(true);
        add(kanbanTable);
        kanbanTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = kanbanTable.rowAtPoint(e.getPoint());
                int column = kanbanTable.columnAtPoint(e.getPoint());

                if (row >= 0 && column >= 0) {
                    Issue issue = (Issue) kanbanTable.getValueAt(row, column);
                    new ShowIssueInfo(issue);
                }
            }
        });


        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setFont(jScrollPane.getFont().deriveFont(Font.BOLD));
        jScrollPane.getViewport().add(kanbanTable, null);
        jScrollPane.setBounds(0, 0, 800, 700);
        add(jScrollPane);


        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component component = super.getTableCellRendererComponent(table, value,
                        false, hasFocus,
                        row, column);
                component.setBackground(Color.WHITE);
                ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
                return component;
            }
        };
        kanbanTable.setDefaultRenderer(Object.class, cellRenderer);

        JButton addIssue = new JButton("+ Add New Issue");
        addIssue.setBounds(440, 710, 250, 50);
        addIssue.setVisible(true);
        add(addIssue);
        addIssue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddIssue(project, board);
            }
        });

        JButton existIssue = new JButton("Delete this board");
        existIssue.setBounds(110, 710, 250, 50);
        existIssue.setVisible(true);
        add(existIssue);
        existIssue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure to delete " + board.getName() + "?",
                        "Delete?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {

                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    boardController.removeSingleBoard(board.getId());
                    setVisible(false);
                } else {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }

            }
        });

        setVisible(true);
    }

    public void enableDragAndDrop(JTable table) {
        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(table, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();

                if (selectedRow != -1 && selectedColumn != -1) {
                    Transferable transferable = new TableRowTransferable(selectedRow, selectedColumn);
                    dragSource.startDrag(dge, DragSource.DefaultMoveDrop, transferable, null);
                }
            }
        });

        DropTarget dropTarget = new DropTarget(table, DnDConstants.ACTION_MOVE, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                dtde.acceptDrag(DnDConstants.ACTION_MOVE);
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {

            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {

            }

            @Override
            public void dragExit(DropTargetEvent dte) {

            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable transferable = dtde.getTransferable();
                    if (transferable.isDataFlavorSupported(TableRowTransferable.ROW_DATA_FLAVOR)) {
                        int targetColumn = table.columnAtPoint(dtde.getLocation());
                        int sourceRow = table.getSelectedRow();
                        int sourceColumn = table.getSelectedColumn();
                        IssueTableModel model = (IssueTableModel) table.getModel();
                        Issue issue = (Issue) model.getValueAt(sourceRow, sourceColumn);
                        if (issue != null) {
                            IssueStatus targetStatus = IssueStatus.values()[targetColumn];
                            issue.setIssueStatus(targetStatus);

                            issueController.saveIssue(issues);
                            customTableModel.setIssues(issues);
                            table.revalidate();
                            dtde.dropComplete(true);
                            repaint();
                        } else {
                            dtde.rejectDrop();
                        }
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dtde.rejectDrop();
                }
            }
        });
    }

    public class TableRowTransferable implements Transferable {
        public static final DataFlavor ROW_DATA_FLAVOR = new DataFlavor(Integer.class, "Row Index");
        public static final DataFlavor COLUMN_DATA_FLAVOR = new DataFlavor(Integer.class, "Column Index");

        private final int rowIndex;
        private final int columnIndex;

        public TableRowTransferable(int rowIndex, int columnIndex) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{ROW_DATA_FLAVOR, COLUMN_DATA_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(ROW_DATA_FLAVOR) || flavor.equals(COLUMN_DATA_FLAVOR);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (flavor.equals(ROW_DATA_FLAVOR)) {
                return rowIndex;
            } else if (flavor.equals(COLUMN_DATA_FLAVOR)) {
                return columnIndex;
            }
            throw new UnsupportedFlavorException(flavor);
        }
    }

    public class IssueTableModel extends AbstractTableModel {

        private final String[] columnNames = {"Todo", "In Progress", "QA", "Done"};
        private final ArrayList<ArrayList<Issue>> columns = new ArrayList<>();

        public IssueTableModel() {
            for (int i = 0; i < columnNames.length; i++) {
                columns.add(new ArrayList<>());
            }
        }

        public void setIssues(ArrayList<Issue> issues) {
            for (ArrayList<Issue> column : columns) {
                column.clear();
            }
            for (Issue issue : issues) {
                columns.get(issue.getIssueStatus().ordinal()).add(issue);
            }
            fireTableDataChanged();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return columns.stream().mapToInt(ArrayList::size).max().orElse(0);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ArrayList<Issue> column = columns.get(columnIndex);
            if (rowIndex >= column.size()) {
                return null;
            }
            return column.get(rowIndex);
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }
        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }

    }
}
