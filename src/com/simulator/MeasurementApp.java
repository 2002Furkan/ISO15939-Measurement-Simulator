package com.simulator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeasurementApp extends JFrame {
    private DataManager dataManager;

    public MeasurementApp() {
        setTitle("ISO 15939 Measurement Simulator");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dataManager = new DataManager();

        JPanel stepPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] stepNames = {"Profile", "Define", "Plan", "Collect", "Analyse"};
        for (String s : stepNames) {
            stepPanel.add(new JLabel(s + " > "));
        }
        add(stepPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("Welcome to ISO 15939 Simulator. System Ready."));
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MeasurementApp().setVisible(true));
    }
}

class DataManager {
    private Map<String, ArrayList<QualityDimension>> scenarios = new HashMap<>();

    public DataManager() {

        ArrayList<QualityDimension> edu = new ArrayList<>();
        QualityDimension d1 = new QualityDimension("Learning Content");
        d1.addMetric(new Metric("Video Quality", 0.5, "inc", "Scale"));
        edu.add(d1);
        scenarios.put("Education", edu);
    }
}
