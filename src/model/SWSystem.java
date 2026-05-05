package model;

import java.util.ArrayList;


public class SWSystem {
    private String name;
    private String category;
    private String version;
    private ArrayList<QualityDimension> dimensions;

    public SWSystem(String name, String category, String version) {
        this.name = name;
        this.category = category;
        this.version = version;
        this.dimensions = new ArrayList<>();
    }

    public void addDimension(QualityDimension d) {
        dimensions.add(d);
    }

   
    public double calculateOverallScore() {
        double totalWeighted = 0;
        double totalWeight   = 0;
        for (QualityDimension qd : dimensions) {
            totalWeighted += qd.calculateDimensionScore() * qd.getWeight();
            totalWeight   += qd.getWeight();
        }
        return totalWeight == 0 ? 0 : totalWeighted / totalWeight;
    }

   
    public QualityDimension findWeakestDimension() {
        if (dimensions.isEmpty()) return null;
        QualityDimension weakest = dimensions.get(0);
        for (QualityDimension qd : dimensions) {
            if (qd.calculateDimensionScore() < weakest.calculateDimensionScore()) {
                weakest = qd;
            }
        }
        return weakest;
    }

  
    public String getName()                          { return name; }
    public String getCategory()                      { return category; }
    public String getVersion()                       { return version; }
    public ArrayList<QualityDimension> getDimensions() { return dimensions; }
}
