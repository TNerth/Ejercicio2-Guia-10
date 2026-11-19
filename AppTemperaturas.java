package Ejercicio2;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppTemperaturas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("temperaturas de la semana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620, 480);
        frame.setLayout(new BorderLayout(10, 10));

        String[] dias = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};
        String[] valoresIniciales = {"18", "21", "19", "23", "20", "22", "17"};
        JTextField[] campos = new JTextField[dias.length];

        JPanel panelCampos = new JPanel(new GridLayout(2, 7, 8, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        for (String dia : dias) {
            panelCampos.add(new JLabel(dia));
        }

        for (int i = 0; i < dias.length; i++) {
            campos[i] = new JTextField(valoresIniciales[i], 5);
            panelCampos.add(campos[i]);
        }

        JButton btnMostrar = new JButton("mostrar grafico");
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnMostrar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelCampos, BorderLayout.CENTER);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        PanelGraficoTemperaturas panelGrafico = new PanelGraficoTemperaturas();

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] temps = new double[dias.length];
                try {
                    for (int i = 0; i < dias.length; i++) {
                        temps[i] = Double.parseDouble(campos[i].getText());
                    }
                    panelGrafico.setTemperaturas(temps);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "ingrese temperaturas numericas valdas en todos los dias");
                }
            }
        });

        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(panelGrafico, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}