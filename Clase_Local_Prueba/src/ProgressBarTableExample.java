import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarTableExample extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ProgressBarTableExample() {
        super("ProgressBar in JTable Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[][]{
                {"Item 1", 0},
                {"Item 2", 0},
                {"Item 3", 0},
        }, new String[]{"Nombre", "Progreso"});

        table = new JTable(tableModel);

        // Configurar el renderizador para la columna de progreso
        table.getColumnModel().getColumn(1).setCellRenderer(new ProgressRenderer());

        JButton startButton = new JButton("Iniciar Tareas");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTasks();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);

        add(panel);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startTasks() {
        Timer timer = new Timer(100, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 5;
                if (progress <= 100) {
                    // Actualizar el valor de la celda de la barra de progreso en la columna 1
                    tableModel.setValueAt(progress, 0, 1);
                    tableModel.setValueAt(progress, 1, 1);
                    tableModel.setValueAt(progress, 2, 1);
                } else {
                    // Detener el temporizador después de alcanzar el 100%
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        // Iniciar el temporizador
        timer.start();
    }

    // Renderizador personalizado para la columna de progreso
    class ProgressRenderer extends DefaultTableCellRenderer {
        private JProgressBar progressBar = new JProgressBar();

        public ProgressRenderer() {
            progressBar.setStringPainted(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Integer) {
                int progress = (Integer) value;
                progressBar.setValue(progress);
                progressBar.setString(progress + "%");
            }

            return progressBar;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProgressBarTableExample();
            }
        });
    }
}
