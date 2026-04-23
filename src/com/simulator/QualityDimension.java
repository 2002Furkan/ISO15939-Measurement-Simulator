package com.simulator;

import java.util.ArrayList;

public class QualityDimension {
    String name;
    ArrayList<Metric> metrics;

    public QualityDimension(String name) {
        this.name = name;
        this.metrics = new ArrayList<>();
    }

    public void addMetric(Metric m) {
        metrics.add(m);
    }

    public double calculateDimensionScore() {
        double weightedSum = 0;
        double totalWeight = 0;
        for (Metric m : metrics) {
            weightedSum += (m.calculatedScore * m.coefficient);
            totalWeight += m.coefficient;
        }
        return totalWeight == 0 ? 0 : weightedSum / totalWeight;
    }
}
