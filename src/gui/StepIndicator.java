package gui;

import javax.swing.*;
import java.awt.*;


public class StepIndicator extends JPanel {

    private static final String[] STEP_NAMES = {
        "Profile", "Define", "Plan", "Collect", "Analyse"
    };

    private int currentStep; 

    public StepIndicator() {
        this.currentStep = 1;
        setPreferredSize(new Dimension(800, 60));
        setBackground(new Color(30, 30, 46));
    }

 
    public void setCurrentStep(int step) {
        this.currentStep = step;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int stepCount = STEP_NAMES.length;
        int stepW = w / stepCount;

        for (int i = 0; i < stepCount; i++) {
            int stepNum = i + 1;
            int cx = i * stepW + stepW / 2;
            int cy = h / 2;

          
            Color circleColor;
            Color textColor;
            if (stepNum < currentStep) {
                circleColor = new Color(80, 200, 120);   
                textColor   = Color.WHITE;
            } else if (stepNum == currentStep) {
                circleColor = new Color(99, 102, 241);   
                textColor   = Color.WHITE;
            } else {
                circleColor = new Color(70, 70, 90);    
                textColor   = new Color(160, 160, 180);
            }

           
            if (i > 0) {
                int prevCx = (i - 1) * stepW + stepW / 2;
                Color lineColor = (stepNum <= currentStep)
                        ? new Color(80, 200, 120) : new Color(70, 70, 90);
                g2.setColor(lineColor);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(prevCx + 16, cy, cx - 16, cy);
            }

           
            g2.setColor(circleColor);
            g2.fillOval(cx - 14, cy - 14, 28, 28);

         
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 12));
            String label = (stepNum < currentStep) ? "✓" : String.valueOf(stepNum);
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(label, cx - fm.stringWidth(label) / 2, cy + fm.getAscent() / 2 - 1);

           
            g2.setColor(textColor);
            Font nameFont = (stepNum == currentStep)
                    ? new Font("SansSerif", Font.BOLD, 11)
                    : new Font("SansSerif", Font.PLAIN, 11);
            g2.setFont(nameFont);
            FontMetrics fm2 = g2.getFontMetrics();
            g2.drawString(STEP_NAMES[i], cx - fm2.stringWidth(STEP_NAMES[i]) / 2, cy + 26);
        }
    }
}
