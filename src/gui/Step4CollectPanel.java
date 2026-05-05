package gui;

import model.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Step4CollectPanel extends JPanel {

    private final AppSession session;
    private final MainFrame  mainFrame;
    private JPanel contentPanel;

    public Step4CollectPanel(AppSession session, MainFrame mainFrame) {
        this.session   = session;
        this.mainFrame = mainFrame;
        buildUI();
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(24, 24, 37));

        JLabel title = new JLabel("Step 4 — Collect Data", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(99, 102, 241));
        title.setBorder(new EmptyBorder(20, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(24, 24, 37));
        contentPanel.setBorder(new EmptyBorder(0, 30, 10, 30));

        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(24, 24, 37));
        add(scroll, BorderLayout.CENTER);
        add(buildButtonBar(), BorderLayout.SOUTH);
    }

    public void refresh() {
        contentPanel.removeAll();
        SWSystem sys = session.getSystem();
        if (sys == null) return;

        for (QualityDimension qd : sys.getDimensions()) {
            JLabel dimLabel = new JLabel(qd.getName() + "  [" + qd.getIsoCode() + "]");
            dimLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            dimLabel.setForeground(new Color(140, 200, 255));
            dimLabel.setBorder(new EmptyBorder(14, 4, 4, 0));
            dimLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPanel.add(dimLabel);

            String[] cols = {"Metric", "Direction", "Range", "Value", "Score (1–5)", "Coeff / Unit"};
            DefaultTableModel model = new DefaultTableModel(cols, 0) {
                public boolean isCellEditable(int r, int c) { return false; }
            };

            for (Criterion c : qd.getCriteria()) {
                double score = c.calculateScore();
                model.addRow(new Object[]{
                    c.getName(),
                    c.getDirectionLabel(),
                    c.getRangeText(),
                    String.format("%.1f", c.getMeasuredValue()),
                    String.format("%.1f", score),
                    (int) c.getWeight() + " / " + c.getUnit()
                });
            }

            JTable table = styledTable(model);
            table.setAlignmentX(Component.LEFT_ALIGNMENT);
            JScrollPane tp = new JScrollPane(table);
            tp.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 90), 1));
            tp.setAlignmentX(Component.LEFT_ALIGNMENT);
            tp.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                table.getRowHeight() * (model.getRowCount() + 1) + 5));
            contentPanel.add(tp);
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JTable styledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setBackground(new Color(38, 38, 58));
        table.setForeground(new Color(220, 220, 240));
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setRowHeight(28);
        table.setGridColor(new Color(60, 60, 80));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(80, 80, 120));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(50, 50, 80));
        header.setForeground(new Color(180, 180, 220));
        header.setFont(new Font("SansSerif", Font.BOLD, 12));

      
        int scoreCol = 4;
        table.getColumnModel().getColumn(scoreCol).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                setHorizontalAlignment(SwingConstants.CENTER);
                try {
                    double s = Double.parseDouble(val.toString());
                    if (s >= 4.5)      setForeground(new Color(80, 220, 120));
                    else if (s >= 3.5) setForeground(new Color(160, 210, 80));
                    else if (s >= 2.5) setForeground(new Color(240, 180, 50));
                    else               setForeground(new Color(240, 80, 80));
                } catch (Exception ex) {
                    setForeground(Color.WHITE);
                }
                return this;
            }
        });

       
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
            if (i != scoreCol)
                table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        return table;
    }

    private JPanel buildButtonBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        bar.setBackground(new Color(24, 24, 37));

        JButton btnBack = new JButton("← Back");
        styleBtn(btnBack, new Color(70, 70, 90));
        btnBack.addActionListener(e -> mainFrame.goToStep(3));

        JButton btnNext = new JButton("Analyse →");
        styleBtn(btnNext, new Color(99, 102, 241));
        btnNext.addActionListener(e -> mainFrame.goToStep(5));

        bar.add(btnBack);
        bar.add(btnNext);
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
