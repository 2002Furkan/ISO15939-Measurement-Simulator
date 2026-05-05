package data;

import model.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioData {

    public static ArrayList<String> getScenariosForMode(String mode) {
        ArrayList<String> list = new ArrayList<>();
        if ("Health".equals(mode)) {
            list.add("Scenario A — ClinicSoft");
            list.add("Scenario B — MediTrack");
        } else if ("Education".equals(mode)) {
            list.add("Scenario C — Team Alpha");
            list.add("Scenario D — Team Beta");
        }
        return list;
    }


    public static SWSystem getSystemForScenario(String scenario) {
        switch (scenario) {
            case "Scenario A — ClinicSoft": return createClinicSoft();
            case "Scenario B — MediTrack":  return createMediTrack();
            case "Scenario C — Team Alpha": return createTeamAlpha();
            case "Scenario D — Team Beta":  return createTeamBeta();
            default: return null;
        }
    }

    private static SWSystem createClinicSoft() {
        SWSystem s = new SWSystem("ClinicSoft", "Health", "2.1.0");

        QualityDimension security = new QualityDimension("Security", "QC.SE", 30);
        Criterion c1 = new Criterion("Security Test Coverage", 50, "higher", 0, 100, "%");
        c1.setMeasuredValue(82);
        Criterion c2 = new Criterion("Vulnerability Count", 50, "lower", 0, 50, "count");
        c2.setMeasuredValue(4);
        security.addCriterion(c1);
        security.addCriterion(c2);
        s.addDimension(security);

        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 25);
        Criterion c3 = new Criterion("Availability Ratio", 50, "higher", 99, 100, "%");
        c3.setMeasuredValue(99.7);
        Criterion c4 = new Criterion("MTTR", 50, "lower", 0, 120, "min");
        c4.setMeasuredValue(15);
        reliability.addCriterion(c3);
        reliability.addCriterion(c4);
        s.addDimension(reliability);

        QualityDimension usability = new QualityDimension("Usability", "QC.US", 20);
        Criterion c5 = new Criterion("SUS Score", 50, "higher", 0, 100, "points");
        c5.setMeasuredValue(78);
        Criterion c6 = new Criterion("Task Completion Rate", 50, "higher", 0, 100, "%");
        c6.setMeasuredValue(91);
        usability.addCriterion(c5);
        usability.addCriterion(c6);
        s.addDimension(usability);

        QualityDimension perf = new QualityDimension("Performance Efficiency", "QC.PE", 25);
        Criterion c7 = new Criterion("Response Time", 50, "lower", 100, 2000, "ms");
        c7.setMeasuredValue(320);
        Criterion c8 = new Criterion("CPU Utilisation", 50, "lower", 0, 100, "%");
        c8.setMeasuredValue(42);
        perf.addCriterion(c7);
        perf.addCriterion(c8);
        s.addDimension(perf);

        return s;
    }


    private static SWSystem createMediTrack() {
        SWSystem s = new SWSystem("MediTrack", "Health", "1.4.2");

        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 25);
        Criterion c1 = new Criterion("Functional Completeness", 50, "higher", 0, 100, "%");
        c1.setMeasuredValue(88);
        Criterion c2 = new Criterion("Functional Correctness", 50, "higher", 0, 100, "%");
        c2.setMeasuredValue(93);
        funcSuit.addCriterion(c1);
        funcSuit.addCriterion(c2);
        s.addDimension(funcSuit);

        QualityDimension maintainability = new QualityDimension("Maintainability", "QC.MA", 25);
        Criterion c3 = new Criterion("Test Coverage Ratio", 50, "higher", 50, 100, "%");
        c3.setMeasuredValue(74);
        Criterion c4 = new Criterion("Cyclomatic Complexity", 50, "lower", 1, 15, "score");
        c4.setMeasuredValue(6);
        maintainability.addCriterion(c3);
        maintainability.addCriterion(c4);
        s.addDimension(maintainability);

        QualityDimension security = new QualityDimension("Security", "QC.SE", 30);
        Criterion c5 = new Criterion("Security Test Coverage", 50, "higher", 0, 100, "%");
        c5.setMeasuredValue(65);
        Criterion c6 = new Criterion("Vulnerability Count", 50, "lower", 0, 50, "count");
        c6.setMeasuredValue(11);
        security.addCriterion(c5);
        security.addCriterion(c6);
        s.addDimension(security);

        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 20);
        Criterion c7 = new Criterion("Availability Ratio", 50, "higher", 99, 100, "%");
        c7.setMeasuredValue(99.4);
        Criterion c8 = new Criterion("Defect Density", 50, "lower", 0, 20, "defect/KLOC");
        c8.setMeasuredValue(3.2);
        reliability.addCriterion(c7);
        reliability.addCriterion(c8);
        s.addDimension(reliability);

        return s;
    }

    private static SWSystem createTeamAlpha() {
        SWSystem s = new SWSystem("EduLMS Alpha", "Education", "3.0.1");

        QualityDimension usability = new QualityDimension("Usability", "QC.US", 25);
        Criterion c1 = new Criterion("SUS Score", 50, "higher", 0, 100, "points");
        c1.setMeasuredValue(89);
        Criterion c2 = new Criterion("Onboarding Time", 50, "lower", 0, 60, "min");
        c2.setMeasuredValue(5);
        usability.addCriterion(c1);
        usability.addCriterion(c2);
        s.addDimension(usability);

        QualityDimension perf = new QualityDimension("Performance Efficiency", "QC.PE", 20);
        Criterion c3 = new Criterion("Video Start Time", 50, "lower", 0, 15, "sec");
        c3.setMeasuredValue(2);
        Criterion c4 = new Criterion("Concurrent Exams", 50, "higher", 0, 600, "users");
        c4.setMeasuredValue(520);
        perf.addCriterion(c3);
        perf.addCriterion(c4);
        s.addDimension(perf);

        QualityDimension accessibility = new QualityDimension("Accessibility", "QC.AC", 20);
        Criterion c5 = new Criterion("WCAG Compliance", 50, "higher", 0, 100, "%");
        c5.setMeasuredValue(91);
        Criterion c6 = new Criterion("Screen Reader Score", 50, "higher", 0, 100, "%");
        c6.setMeasuredValue(85);
        accessibility.addCriterion(c5);
        accessibility.addCriterion(c6);
        s.addDimension(accessibility);

        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 20);
        Criterion c7 = new Criterion("Uptime", 50, "higher", 95, 100, "%");
        c7.setMeasuredValue(99.5);
        Criterion c8 = new Criterion("MTTR", 50, "lower", 0, 120, "min");
        c8.setMeasuredValue(8);
        reliability.addCriterion(c7);
        reliability.addCriterion(c8);
        s.addDimension(reliability);

        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 15);
        Criterion c9 = new Criterion("Feature Completion", 50, "higher", 0, 100, "%");
        c9.setMeasuredValue(95);
        Criterion c10 = new Criterion("Assignment Submit Rate", 50, "higher", 0, 100, "%");
        c10.setMeasuredValue(88);
        funcSuit.addCriterion(c9);
        funcSuit.addCriterion(c10);
        s.addDimension(funcSuit);

        return s;
    }


    private static SWSystem createTeamBeta() {
        SWSystem s = new SWSystem("EduLMS Beta", "Education", "2.8.0");

        QualityDimension usability = new QualityDimension("Usability", "QC.US", 25);
        Criterion c1 = new Criterion("SUS Score", 50, "higher", 0, 100, "points");
        c1.setMeasuredValue(72);
        Criterion c2 = new Criterion("Onboarding Time", 50, "lower", 0, 60, "min");
        c2.setMeasuredValue(18);
        usability.addCriterion(c1);
        usability.addCriterion(c2);
        s.addDimension(usability);

        QualityDimension perf = new QualityDimension("Performance Efficiency", "QC.PE", 20);
        Criterion c3 = new Criterion("Video Start Time", 50, "lower", 0, 15, "sec");
        c3.setMeasuredValue(7);
        Criterion c4 = new Criterion("Concurrent Exams", 50, "higher", 0, 600, "users");
        c4.setMeasuredValue(310);
        perf.addCriterion(c3);
        perf.addCriterion(c4);
        s.addDimension(perf);

        QualityDimension accessibility = new QualityDimension("Accessibility", "QC.AC", 20);
        Criterion c5 = new Criterion("WCAG Compliance", 50, "higher", 0, 100, "%");
        c5.setMeasuredValue(61);
        Criterion c6 = new Criterion("Screen Reader Score", 50, "higher", 0, 100, "%");
        c6.setMeasuredValue(55);
        accessibility.addCriterion(c5);
        accessibility.addCriterion(c6);
        s.addDimension(accessibility);

        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 20);
        Criterion c7 = new Criterion("Uptime", 50, "higher", 95, 100, "%");
        c7.setMeasuredValue(98.1);
        Criterion c8 = new Criterion("MTTR", 50, "lower", 0, 120, "min");
        c8.setMeasuredValue(35);
        reliability.addCriterion(c7);
        reliability.addCriterion(c8);
        s.addDimension(reliability);

        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 15);
        Criterion c9 = new Criterion("Feature Completion", 50, "higher", 0, 100, "%");
        c9.setMeasuredValue(78);
        Criterion c10 = new Criterion("Assignment Submit Rate", 50, "higher", 0, 100, "%");
        c10.setMeasuredValue(69);
        funcSuit.addCriterion(c9);
        funcSuit.addCriterion(c10);
        s.addDimension(funcSuit);

        return s;
    }
}
