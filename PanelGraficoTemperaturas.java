package Ejercicio2;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelGraficoTemperaturas extends JPanel {
    private String[] dias = {"lun", "mar", "mie", "jue", "vie", "sab", "dom"};
    private double[] temperaturas = {18, 21, 19, 23, 20, 22, 17};
    private boolean mostrar = false;

    public void setTemperaturas(double[] nuevasTemperaturas) {
        this.temperaturas = nuevasTemperaturas;
        this.mostrar = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margenIzq = 55;
        int margenDer = 35;
        int margenSup = 35;
        int margenInf = 45;

        int anchoUtil = getWidth() - margenIzq - margenDer;
        int altoUtil = getHeight() - margenSup - margenInf;

        g2d.setColor(Color.BLACK);
        g2d.drawLine(margenIzq, margenSup, margenIzq, margenSup + altoUtil);
        g2d.drawLine(margenIzq, margenSup + altoUtil, margenIzq + anchoUtil, margenSup + altoUtil);
        g2d.drawString("temp", 12, margenSup + 5);

        if (!mostrar) {
            g2d.setColor(Color.GRAY);
            g2d.drawString("ingrese las temperaturas y presione mostrar grafico", margenIzq + 30, margenSup + altoUtil / 2);
            return;
        }

        double minTemp = temperaturas[0];
        double maxTemp = temperaturas[0];
        for (double t : temperaturas) {
            if (t < minTemp) {
                minTemp = t;
            }
            if (t > maxTemp) {
                maxTemp = t;
            }
        }

        if (maxTemp == minTemp) {
            maxTemp += 5;
            minTemp -= 5;
        } else {
            double relleno = (maxTemp - minTemp) * 0.15;
            maxTemp += relleno;
            minTemp -= relleno;
        }

        int[] puntosX = new int[dias.length];
        int[] puntosY = new int[dias.length];
        int pasoX = anchoUtil / (dias.length - 1);

        for (int i = 0; i < dias.length; i++) {
            puntosX[i] = margenIzq + i * pasoX;
            double proporcion = (temperaturas[i] - minTemp) / (maxTemp - minTemp);
            puntosY[i] = margenSup + altoUtil - (int) (proporcion * altoUtil);
        }

        g2d.setColor(new Color(40, 110, 200));
        g2d.setStroke(new BasicStroke(2.5f));
        for (int i = 0; i < dias.length - 1; i++) {
            g2d.drawLine(puntosX[i], puntosY[i], puntosX[i + 1], puntosY[i + 1]);
        }

        for (int i = 0; i < dias.length; i++) {
            g2d.setColor(Color.RED);
            g2d.fillOval(puntosX[i] - 4, puntosY[i] - 4, 8, 8);

            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(temperaturas[i]), puntosX[i] - 12, puntosY[i] - 10);
            g2d.drawString(dias[i], puntosX[i] - 10, margenSup + altoUtil + 20);
        }
    }
}