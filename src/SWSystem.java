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

    public void addDimension(QualityDimension dimension) {
        dimensions.add(dimension); 
    }

    public double calculateOverallScore() {
        double totalWeightedScore = 0;
        double totalWeight = 0;
        for (QualityDimension qd : dimensions) {
            totalWeightedScore += (qd.calculateDimensionScore() * qd.getWeight());
            totalWeight += qd.getWeight();
        }
        return totalWeight == 0 ? 0 : totalWeightedScore / totalWeight; // [cite: 67]
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

    public void printReport() {
        System.out.println("===");
        System.out.println("SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
        System.out.printf("System: %s v%s (%s)%n", name, version, category);

        for (QualityDimension qd : dimensions) {
            System.out.printf("--- %s [%s] (Weight: %.0f) ---%n", qd.getName(), qd.getIsoCode(), qd.getWeight());
            for (Criterion c : qd.getCriteria()) {
                System.out.println(c.toString());
            }
            System.out.printf(">> Dimension Score: %.1f/5 [%s]%n", qd.calculateDimensionScore(), qd.getQualityLabel());
        }

        double overall = calculateOverallScore();
        String overallLabel = (overall >= 4.5) ? "Excellent Quality" : (overall >= 3.5) ? "Good Quality" : (overall >= 2.5) ? "Needs Improvement" : "Poor Quality";
        
        System.out.printf("OVERALL QUALITY SCORE: %.1f/5 [%s]%n", overall, overallLabel);
        
        QualityDimension weakest = findWeakestDimension();
        if (weakest != null) {
            System.out.println("GAP ANALYSIS (ISO/IEC 25010)");
            System.out.printf("Weakest Characteristic: %s [%s]%n", weakest.getName(), weakest.getIsoCode());
            System.out.printf("Score: %.1f/5 Gap: %.1f%n", weakest.calculateDimensionScore(), weakest.calculateGap());
            System.out.printf("Level: %s%n", weakest.getQualityLabel());
            System.out.println(">> This characteristic requires the most improvement.");
        }
    }

    public ArrayList<QualityDimension> getDimensions() { return dimensions; }
}