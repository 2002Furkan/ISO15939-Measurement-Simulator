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

    public void addCriterion(Criterion criterion) {
        criteria.add(criterion); 
    }

    public double calculateDimensionScore() {
        double totalWeightedScore = 0;
        double totalWeight = 0;
        for (Criterion c : criteria) {
            totalWeightedScore += (c.calculateScore() * c.getWeight());
            totalWeight += c.getWeight();
        }
        return totalWeight == 0 ? 0 : totalWeightedScore / totalWeight; 
    }

    public String getQualityLabel() {
        double score = calculateDimensionScore();
        if (score >= 4.5) return "Excellent Quality";
        if (score >= 3.5) return "Good Quality";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor Quality"; 
    }

    public double calculateGap() {
        return 5.0 - calculateDimensionScore(); 
    }

    public String getName() { return name; }
    public String getIsoCode() { return isoCode; }
    public double getWeight() { return weight; }
    public ArrayList<Criterion> getCriteria() { return criteria; }
}