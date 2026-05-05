package gui;

import data.ScenarioData;
import model.AppSession;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;


public class Step2DefinePanel extends JPanel {

    private final AppSession session;
    private final MainFrame  mainFrame;

   
    private ButtonGroup typeGroup;
    private JRadioButton rbProduct, rbProcess;

    
    private ButtonGroup modeGroup;
    private JRadioButton rbHealth, rbEducation;

    
    private ButtonGroup scenarioGroup;
    private JPanel      scenarioPanel;

    public Step2DefinePanel(AppSession session, MainFrame mainFrame) {
        this.session   = session;
        this.mainFrame = mainFrame;
        buildUI();
    }

    private void buildUI() {
        setLayout(new BorderLayout(0, 10));
        setBackground(new Color(24, 24, 37));
        setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Step 2 — Define Quality Dimensions", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(99, 102, 241));
        title.setBorder(new EmptyBorder(10, 0, 20, 0));
        add(title, BorderLayout.NORTH);

       
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(new Color(24, 24, 37));

      
        JPanel typeCard = makeCard("2a  Quality Type");
        typeGroup  = new ButtonGroup();
        rbProduct  = makeRadio("Product Quality",
                "Software product characteristics: performance, security, usability, reliability");
        rbProcess  = makeRadio("Process Quality",
                "Development process characteristics: sprint efficiency, code quality, team collaboration");
        typeGroup.add(rbProduct);
        typeGroup.add(rbProcess);
        rbProduct.setSelected(true);
        typeCard.add(rbProduct);
        typeCard.add(Box.createVerticalStrut(6));
        typeCard.add(rbProcess);
        center.add(typeCard);
        center.add(Box.createVerticalStrut(12));

      
        JPanel modeCard = makeCard("2b  Mode");
        modeGroup   = new ButtonGroup();
        rbHealth    = makeRadio("Health",    "Health management system scenarios (ready-made dataset)");
        rbEducation = makeRadio("Education", "Education LMS system scenarios (ready-made dataset)");
        modeGroup.add(rbHealth);
        modeGroup.add(rbEducation);
        rbHealth.setSelected(true);
        rbHealth.addActionListener(e -> refreshScenarios());
        rbEducation.addActionListener(e -> refreshScenarios());
        modeCard.add(rbHealth);
        modeCard.add(Box.createVerticalStrut(6));
        modeCard.add(rbEducation);
        center.add(modeCard);
        center.add(Box.createVerticalStrut(12));

        
        JPanel scenarioCard = makeCard("2c  Scenario");
        scenarioGroup = new ButtonGroup();
        scenarioPanel = new JPanel();
        scenarioPanel.setLayout(new BoxLayout(scenarioPanel, BoxLayout.Y_AXIS));
        scenarioPanel.setBackground(new Color(38, 38, 58));
        scenarioCard.add(scenarioPanel);
        center.add(scenarioCard);

        refreshScenarios();

        JScrollPane scroll = new JScrollPane(center);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(24, 24, 37));
        add(scroll, BorderLayout.CENTER);

       
        add(buildButtonBar(), BorderLayout.SOUTH);
    }

   
    private void refreshScenarios() {
        String mode = rbHealth.isSelected() ? "Health" : "Education";
        ArrayList<String> scenarios = ScenarioData.getScenariosForMode(mode);

        scenarioPanel.removeAll();
        scenarioGroup = new ButtonGroup();

        for (String sc : scenarios) {
            JRadioButton rb = makeRadio(sc, "");
            scenarioGroup.add(rb);
            scenarioPanel.add(rb);
            scenarioPanel.add(Box.createVerticalStrut(4));
        }
        if (!scenarios.isEmpty()) {
         
            ((JRadioButton) scenarioPanel.getComponent(0)).setSelected(true);
        }
        scenarioPanel.revalidate();
        scenarioPanel.repaint();
    }

    private void onNext() {
      
        String mode = rbHealth.isSelected() ? "Health" : "Education";
        session.setQualityType(rbProduct.isSelected() ? "Product" : "Process");
        session.setMode(mode);

       
        String selectedScenario = null;
        for (Component comp : scenarioPanel.getComponents()) {
            if (comp instanceof JRadioButton rb && rb.isSelected()) {
                selectedScenario = rb.getText()
                    .replaceAll("<[^>]*>", "")  
                    .trim();
                break;
            }
        }
        if (selectedScenario == null || selectedScenario.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select a scenario to continue.",
                "Missing Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        session.setScenario(selectedScenario);
        session.setSystem(ScenarioData.getSystemForScenario(selectedScenario));
        mainFrame.goToStep(3);
    }

 

    private JPanel makeCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(38, 38, 58));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 110), 1), title);
        border.setTitleColor(new Color(160, 160, 210));
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 12));
        card.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(8, 12, 8, 12)));
        return card;
    }

    private JRadioButton makeRadio(String text, String tooltip) {
        JRadioButton rb = new JRadioButton("<html><b>" + text + "</b>"
            + (tooltip.isEmpty() ? "" : "<br><font color='#9090b0' size='3'>" + tooltip + "</font>")
            + "</html>");
        rb.setBackground(new Color(38, 38, 58));
        rb.setForeground(Color.WHITE);
        rb.setAlignmentX(Component.LEFT_ALIGNMENT);
        rb.setFocusPainted(false);
        return rb;
    }

    private JPanel buildButtonBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        bar.setBackground(new Color(24, 24, 37));

        JButton btnBack = new JButton("← Back");
        styleBtn(btnBack, new Color(70, 70, 90));
        btnBack.addActionListener(e -> mainFrame.goToStep(1));

        JButton btnNext = new JButton("Next →");
        styleBtn(btnNext, new Color(99, 102, 241));
        btnNext.addActionListener(e -> onNext());

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
