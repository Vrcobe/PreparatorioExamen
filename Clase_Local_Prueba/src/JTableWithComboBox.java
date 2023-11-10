import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class JTableWithComboBox {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable with JComboBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel(new Object[][]{
                {"Row 1", "Option 1"},
                {"Row 2", "Option 2"},
                {"Row 3", "Option 3"},
                {"Row 4", "Option 1"}
        }, new String[]{"Column 1", "Column 2"});

        JTable table = new JTable(model);

        // Create a JComboBox to use as a cell editor
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Option 1");
        comboBox.addItem("Option 2");
        comboBox.addItem("Option 3");

        // Create a DefaultCellEditor with the JComboBox
        TableCellEditor editor = new DefaultCellEditor(comboBox);

        // Set the cell editor for the specific column (Column 2 in this example)
        TableColumn column = table.getColumnModel().getColumn(1);
        column.setCellEditor(editor);

        // Add an ActionListener to the JComboBox to handle selections
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedOption = (String) combo.getSelectedItem();
                System.out.println("Selected: " + selectedOption);
            }
        });

        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setVisible(true);
    }
}
