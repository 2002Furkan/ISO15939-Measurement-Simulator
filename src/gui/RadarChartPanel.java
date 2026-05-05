package gui;

import model.QualityDimension;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class RadarChartPanel extends JPanel {

    private ArrayList<QualityDimension> dimensions;

    public RadarChartPanel() {
        this.dimensions = new ArrayList<>();
        setBackground(new Color(30, 30, 46));
        setPreferredSize(new Dimension(340, 300));
    }

    public void setDimensions(ArrayList<QualityDimension> dims) {
        this.dimensions = dims;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dimensions == null || dimensions.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w  = getWidth();
        int h  = getHeight();
        int cx = w / 2;
        int cy = h / 2 + 10;
        int maxR = Math.min(w, h) / 2 - 50; 

        int n = dimensions.size();
        double[] angles = new double[n];
        for (int i = 0; i < n; i++) {
            angles[i] = Math.toRadians(-90 + 360.0 / n * i);
        }


        g2.setStroke(new BasicStroke(0.8f));
        for (int level = 1; level <= 5; level++) {
            double r = maxR * level / 5.0;
            g2.setColor(new Color(70, 70, 100));
            Path2D grid = new Path2D.Double();
            for (int i = 0; i < n; i++) {
                double x = cx + r * Math.cos(angles[i]);
                double y = cy + r * Math.sin(angles[i]);
                if (i == 0) grid.moveTo(x, y);
                else        grid.lineTo(x, y);
            }
            grid.closePath();
            g2.draw(grid);

            g2.setColor(new Color(120, 120, 150));
            g2.setFont(new Font("SansSerif", Font.PLAIN, 9));
            g2.drawString(String.valueOf(level),
                (int)(cx + r * Math.cos(angles[0])) + 3,
                (int)(cy + r * Math.sin(angles[0])) - 2);
        }

        g2.setColor(new Color(80, 80, 110));
        g2.setStroke(new BasicStroke(1f));
        for (int i = 0; i < n; i++) {
            g2.drawLine(cx, cy,
                (int)(cx + maxR * Math.cos(angles[i])),
                (int)(cy + maxR * Math.sin(angles[i])));
        }

        Path2D poly = new Path2D.Double();
        for (int i = 0; i < n; i++) {
            double score = dimensions.get(i).calculateDimensionScore();
            double r     = maxR * score / 5.0;
            double x     = cx + r * Math.cos(angles[i]);
            double y     = cy + r * Math.sin(angles[i]);
            if (i == 0) poly.moveTo(x, y);
            else        poly.lineTo(x, y);
        }
        poly.closePath();


        g2.setColor(new Color(99, 102, 241, 80));
        g2.fill(poly);

        g2.setColor(new Color(99, 102, 241));
        g2.setStroke(new BasicStroke(2f));
        g2.draw(poly);

        g2.setFont(new Font("SansSerif", Font.BOLD, 10));
        for (int i = 0; i < n; i++) {
            double score = dimensions.get(i).calculateDimensionScore();
            double r     = maxR * score / 5.0;
            int px = (int)(cx + r * Math.cos(angles[i]));
            int py = (int)(cy + r * Math.sin(angles[i]));

            g2.setColor(new Color(200, 200, 255));
            g2.fillOval(px - 4, py - 4, 8, 8);

            int lx = (int)(cx + (maxR + 18) * Math.cos(angles[i]));
            int ly = (int)(cy + (maxR + 18) * Math.sin(angles[i]));
            g2.setColor(new Color(180, 180, 220));
            g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
            String dimName = dimensions.get(i).getName();
            
            if (dimName.length() > 12) dimName = dimName.substring(0, 11) + "…";
            FontMetrics fm = g2.getFontMetrics();
            int tw = fm.stringWidth(dimName);
            g2.drawString(dimName, lx - tw / 2, ly + fm.getAscent() / 2);
        }

        g2.setColor(new Color(140, 140, 200));
        g2.setFont(new Font("SansSerif", Font.BOLD, 11));
        g2.drawString("Radar Chart", cx - 32, 16);
    }
}
