import java.util.ArrayList;
import java.util.HashMap;

public class SWSystemData {
    
    public static HashMap<String, ArrayList<SWSystem>> getAllSystems() {
        HashMap<String, ArrayList<SWSystem>> map = new HashMap<>();

        ArrayList<SWSystem> webList = new ArrayList<>();
        webList.add(createECommercePlatform()); 
        webList.add(createBankingPortal());     
        map.put("Web", webList);

        ArrayList<SWSystem> mobileList = new ArrayList<>();
        map.put("Mobile", mobileList);

        return map;
    }

    private static SWSystem createECommercePlatform() {
        SWSystem s = new SWSystem("ShopSphere", "Web", "3.2.1");

        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 25);
        funcSuit.addCriterion(new Criterion("Functional Completeness Ratio", 50, "higher", 0, 100, "%")); 
        funcSuit.addCriterion(new Criterion("Functional Correctness Ratio", 50, "higher", 0, 100, "%")); 
        s.addDimension(funcSuit);

        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 25);
        reliability.addCriterion(new Criterion("Availability Ratio", 50, "higher", 95, 100, "%")); 
        reliability.addCriterion(new Criterion("Defect Density", 50, "lower", 0, 20, "defect/KLOC")); 
        s.addDimension(reliability);

        QualityDimension perfEff = new QualityDimension("Performance Efficiency", "QC.PE", 25);
        perfEff.addCriterion(new Criterion("Response Time", 50, "lower", 100, 500, "ms"));
        perfEff.addCriterion(new Criterion("CPU Utilisation", 50, "lower", 0, 100, "%"));
        s.addDimension(perfEff);

        QualityDimension maintainability = new QualityDimension("Maintainability", "QC.MA", 25);
        maintainability.addCriterion(new Criterion("Test Coverage Ratio", 50, "higher", 50, 100, "%"));
        maintainability.addCriterion(new Criterion("Cyclomatic Complexity", 50, "lower", 1, 15, "score"));
        s.addDimension(maintainability);

        return s;
    }


    private static SWSystem createBankingPortal() {
        SWSystem s = new SWSystem("GlobalBank", "Web", "1.0.5");

        QualityDimension security = new QualityDimension("Security", "QC.SE", 30);
        security.addCriterion(new Criterion("Security Test Coverage", 50, "higher", 0, 100, "%"));
        security.addCriterion(new Criterion("Vulnerability Count", 50, "lower", 0, 50, "count"));
        s.addDimension(security);

        QualityDimension usability = new QualityDimension("Usability", "QC.US", 20);
        usability.addCriterion(new Criterion("Task Completion Rate", 60, "higher", 0, 100, "%"));
        usability.addCriterion(new Criterion("User Error Rate", 40, "lower", 0, 100, "%"));
        s.addDimension(usability);

        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 25);
        reliability.addCriterion(new Criterion("Availability Ratio", 100, "higher", 99, 100, "%"));
        s.addDimension(reliability);
        
        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 25);
        funcSuit.addCriterion(new Criterion("Functional Correctness Ratio", 100, "higher", 0, 100, "%"));
        s.addDimension(funcSuit);

        return s;
    }
}