package gui;

import model.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;


public class Step5AnalysePanel extends JPanel {

    private final AppSession session;
    private final MainFrame  mainFrame;

    private JPanel dimBarsPanel;
    private RadarChartPanel radarPanel;
    private JPanel gapPanel;

    public Step5AnalysePanel(AppSession session, MainFrame mainFrame) {
        this.session   = session;
        this.mainFrame = mainFrame;
        buildUI();
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(24, 24, 37));

        JLabel title = new JLabel("Step 5 — Analyse Results", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(99, 102, 241));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

       
        JPanel middle = new JPanel(new BorderLayout(20, 0));
        middle.setBackground(new Color(24, 24, 37));
        middle.setBorder(new EmptyBorder(0, 25, 0, 25));

        JPanel leftCol = new JPanel();
        leftCol.setLayout(new BoxLayout(leftCol, BoxLayout.Y_AXIS));
        leftCol.setBackground(new Color(24, 24, 37));

       
        dimBarsPanel = new JPanel();
        dimBarsPanel.setLayout(new BoxLayout(dimBarsPanel, BoxLayout.Y_AXIS));
        dimBarsPanel.setBackground(new Color(38, 38, 58));
        dimBarsPanel.setBorder(makeTitle("5a  Dimension Scores"));
        leftCol.add(dimBarsPanel);
        leftCol.add(Box.createVerticalStrut(12));

       
        gapPanel = new JPanel();
        gapPanel.setLayout(new BoxLayout(gapPanel, BoxLayout.Y_AXIS));
        gapPanel.setBackground(new Color(38, 38, 58));
        gapPanel.setBorder(makeTitle("5c  Gap Analysis"));
        leftCol.add(gapPanel);

        middle.add(leftCol, BorderLayout.CENTER);

      
        JPanel radarWrapper = new JPanel(new BorderLayout());
        radarWrapper.setBackground(new Color(38, 38, 58));
        radarWrapper.setBorder(makeTitle("5b  Radar Chart  ★ Bonus"));
        radarWrapper.setPreferredSize(new Dimension(360, 350));
        radarPanel = new RadarChartPanel();
        radarWrapper.add(radarPanel, BorderLayout.CENTER);
        middle.add(radarWrapper, BorderLayout.EAST);

        JScrollPane scroll = new JScrollPane(middle);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(24, 24, 37));
        add(scroll, BorderLayout.CENTER);
        add(buildButtonBar(), BorderLayout.SOUTH);
    }

   
    public void refresh() {
        SWSystem sys = session.getSystem();
        if (sys == null) return;

        ArrayList<QualityDimension> dims = sys.getDimensions();

    
        dimBarsPanel.removeAll();
        dimBarsPanel.add(Box.createVerticalStrut(8));

        for (QualityDimension qd : dims) {
            double score = qd.calculateDimensionScore();
            JLabel nameLabel = new JLabel(
                String.format("  %s [%s]  —  %.2f / 5.0  (%s)",
                    qd.getName(), qd.getIsoCode(), score, qd.getQualityLabel()));
            nameLabel.setForeground(new Color(200, 200, 230));
            nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            dimBarsPanel.add(nameLabel);

            JProgressBar bar = new JProgressBar(0, 100);
            bar.setValue((int)(score / 5.0 * 100));
            bar.setStringPainted(false);
            bar.setForeground(scoreColor(score));
            bar.setBackground(new Color(55, 55, 75));
            bar.setBorder(new EmptyBorder(0, 8, 0, 8));
            bar.setPreferredSize(new Dimension(Integer.MAX_VALUE, 14));
            bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 14));
            bar.setAlignmentX(Component.LEFT_ALIGNMENT);
            dimBarsPanel.add(bar);
            dimBarsPanel.add(Box.createVerticalStrut(10));
        }

     
        double overall = sys.calculateOverallScore();
        String overallLabel = getOverallLabel(overall);
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(80, 80, 110));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        dimBarsPanel.add(sep);
        dimBarsPanel.add(Box.createVerticalStrut(6));

        JLabel overallLbl = new JLabel(
            String.format("  OVERALL: %.2f / 5.0  [%s]", overall, overallLabel));
        overallLbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        overallLbl.setForeground(scoreColor(overall));
        overallLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        dimBarsPanel.add(overallLbl);
        dimBarsPanel.add(Box.createVerticalStrut(8));

    
        radarPanel.setDimensions(dims);

     
        gapPanel.removeAll();
        gapPanel.add(Box.createVerticalStrut(8));

        QualityDimension weakest = sys.findWeakestDimension();
        if (weakest != null) {
            addGapRow(gapPanel, "Weakest Dimension:",
                weakest.getName() + " [" + weakest.getIsoCode() + "]",
                new Color(240, 120, 80));
            addGapRow(gapPanel, "Score:",
                String.format("%.2f / 5.0", weakest.calculateDimensionScore()),
                new Color(220, 200, 100));
            addGapRow(gapPanel, "Gap:",
                String.format("%.2f", weakest.calculateGap()),
                new Color(240, 100, 100));
            addGapRow(gapPanel, "Level:",
                weakest.getQualityLabel(), new Color(180, 160, 255));

            gapPanel.add(Box.createVerticalStrut(6));
            JLabel advice = new JLabel(
                "<html><i>This dimension has the lowest score<br>and requires the most improvement.</i></html>");
            advice.setFont(new Font("SansSerif", Font.ITALIC, 12));
            advice.setForeground(new Color(200, 160, 100));
            advice.setBorder(new EmptyBorder(4, 12, 4, 12));
            advice.setAlignmentX(Component.LEFT_ALIGNMENT);
            gapPanel.add(advice);
        }
        gapPanel.add(Box.createVerticalStrut(8));

        dimBarsPanel.revalidate();
        dimBarsPanel.repaint();
        gapPanel.revalidate();
        gapPanel.repaint();
        revalidate();
        repaint();
    }

    private void addGapRow(JPanel panel, String key, String value, Color valueColor) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 2));
        row.setBackground(new Color(38, 38, 58));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        JLabel kLbl = new JLabel(key);
        kLbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        kLbl.setForeground(new Color(160, 160, 200));

        JLabel vLbl = new JLabel(value);
        vLbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        vLbl.setForeground(valueColor);

        row.add(kLbl);
        row.add(vLbl);
        panel.add(row);
    }

    private Color scoreColor(double s) {
        if (s >= 4.5) return new Color(80, 220, 120);
        if (s >= 3.5) return new Color(160, 210, 80);
        if (s >= 2.5) return new Color(240, 180, 50);
        return new Color(240, 80, 80);
    }

    private String getOverallLabel(double s) {
        if (s >= 4.5) return "Excellent Quality";
        if (s >= 3.5) return "Good Quality";
        if (s >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }

    private javax.swing.border.Border makeTitle(String text) {
        TitledBorder b = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 110), 1), text);
        b.setTitleColor(new Color(160, 160, 220));
        b.setTitleFont(new Font("SansSerif", Font.BOLD, 12));
        return BorderFactory.createCompoundBorder(b, new EmptyBorder(6, 10, 6, 10));
    }

    private JPanel buildButtonBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        bar.setBackground(new Color(24, 24, 37));

        JButton btnBack = new JButton("← Back");
        styleBtn(btnBack, new Color(70, 70, 90));
        btnBack.addActionListener(e -> mainFrame.goToStep(4));

        JButton btnRestart = new JButton("↺ Restart");
        styleBtn(btnRestart, new Color(50, 140, 100));
        btnRestart.addActionListener(e -> mainFrame.restart());

        bar.add(btnBack);
        bar.add(btnRestart);
        return bar;
    }

    private void styleBtn(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
