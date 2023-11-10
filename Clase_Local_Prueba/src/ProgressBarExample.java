import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarExample extends JFrame {
    private JProgressBar progressBar;
    private JButton startButton;

    public ProgressBarExample() {
        super("ProgressBar Example");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        startButton = new JButton("Iniciar Tarea");

        // Agregamos un ActionListener al botón
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Deshabilitamos el botón para evitar múltiples clics
                startButton.setEnabled(false);

                // Iniciamos una tarea ficticia en un hilo separado
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        performTask();
                        // Habilitamos el botón después de completar la tarea
                        startButton.setEnabled(true);
                    }
                }).start();
            }
        });

        // Creamos un panel y agregamos los componentes
        JPanel panel = new JPanel();
        panel.add(progressBar);
        panel.add(startButton);

        // Agregamos el panel a la ventana
        add(panel);

        setVisible(true);
    }

    private void performTask() {
        // Simulamos una tarea que lleva tiempo completarse
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50); // Simulamos una pausa en la tarea
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Actualizamos el valor de la barra de progreso en el hilo de la interfaz de usuario
            final int progressValue = i;  // Variable final o efectivamente final
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progressBar.setValue(progressValue);
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProgressBarExample();
            }
        });
    }
}
