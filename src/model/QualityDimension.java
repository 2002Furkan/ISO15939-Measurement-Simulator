package model;

import java.util.ArrayList;

public class QualityDimension {
    private String name;
    private String isoCode;
    private double weight;  
    private ArrayList<Criterion> criteria;

    public QualityDimension(String name, String isoCode, double weight) {
        this.name = name;
        this.isoCode = isoCode;
        this.weight = weight;
        this.criteria = new ArrayList<>();
    }

    public void addCriterion(Criterion c) {
        criteria.add(c);
    }

  
    public double calculateDimensionScore() {
        double totalWeighted = 0;
        double totalWeight   = 0;
        for (Criterion c : criteria) {
            totalWeighted += c.calculateScore() * c.getWeight();
            totalWeight   += c.getWeight();
        }
        return totalWeight == 0 ? 0 : totalWeighted / totalWeight;
    }

  
    public double calculateGap() {
        return 5.0 - calculateDimensionScore();
    }

  
    public String getQualityLabel() {
        double s = calculateDimensionScore();
        if (s >= 4.5) return "Excellent Quality";
        if (s >= 3.5) return "Good Quality";
        if (s >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }

   
    public String getName()                   { return name; }
    public String getIsoCode()                { return isoCode; }
    public double getWeight()                 { return weight; }
    public ArrayList<Criterion> getCriteria() { return criteria; }
}
