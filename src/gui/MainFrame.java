package gui;

import model.AppSession;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final AppSession session;

    private final CardLayout cardLayout;
    private final JPanel     cardPanel;
    private final StepIndicator stepIndicator;

    private final Step1ProfilePanel  step1;
    private final Step2DefinePanel   step2;
    private final Step3PlanPanel     step3;
    private final Step4CollectPanel  step4;
    private final Step5AnalysePanel  step5;

    public MainFrame() {
        super("ISO/IEC 15939 — Measurement Process Simulator");
        session = new AppSession();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 640);
        setMinimumSize(new Dimension(760, 560));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(24, 24, 37));

        stepIndicator = new StepIndicator();
        add(stepIndicator, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel  = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(24, 24, 37));
        add(cardPanel, BorderLayout.CENTER);

        step1 = new Step1ProfilePanel(session, this);
        step2 = new Step2DefinePanel(session, this);
        step3 = new Step3PlanPanel(session, this);
        step4 = new Step4CollectPanel(session, this);
        step5 = new Step5AnalysePanel(session, this);

        cardPanel.add(step1, "1");
        cardPanel.add(step2, "2");
        cardPanel.add(step3, "3");
        cardPanel.add(step4, "4");
        cardPanel.add(step5, "5");

        cardLayout.show(cardPanel, "1");
        setVisible(true);
    }

    public void goToStep(int step) {
        switch (step) {
            case 3 -> step3.refresh();
            case 4 -> step4.refresh();
            case 5 -> step5.refresh();
        }
        cardLayout.show(cardPanel, String.valueOf(step));
        stepIndicator.setCurrentStep(step);
    }

    public void restart() {
        session.getProfile().setUsername(null);
        session.getProfile().setSchool(null);
        session.getProfile().setSessionName(null);
        session.setQualityType(null);
        session.setMode(null);
        session.setScenario(null);
        session.setSystem(null);

        dispose();
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
