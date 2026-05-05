
ISO/IEC 15939 Measurement Process Simulator


STUDENT NAME   : FURKAN AKYOL
STUDENT ID     : 202428204
COURSE         : Software Project II



src/
 ├── Main.java                  (Entry point)
 ├── model/
 │    ├── AppSession.java       (Oturum verileri)
 │    ├── UserProfile.java      (Kullanıcı profili)
 │    ├── SWSystem.java         (Yazılım sistemi)
 │    ├── QualityDimension.java (Kalite boyutu)
 │    └── Criterion.java        (Metrik/kriter)
 ├── data/
 │    └── ScenarioData.java     (Hard-coded senaryo verileri)
 └── gui/
      ├── MainFrame.java        (Ana pencere / CardLayout controller)
      ├── StepIndicator.java    (Üst adım göstergesi)
      ├── Step1ProfilePanel.java
      ├── Step2DefinePanel.java
      ├── Step3PlanPanel.java
      ├── Step4CollectPanel.java
      ├── Step5AnalysePanel.java
      └── RadarChartPanel.java  (BONUS: Radar chart)


Windows:
  cd src
  javac -d ../out model\*.java data\*.java gui\*.java Main.java


  cd out
  java Main


✓ Step 1 — Profile   : Username, School, Session Name + validation
✓ Step 2 — Define    : Quality Type / Mode / Scenario selection (RadioButton)
✓ Step 3 — Plan      : Read-only metric table with all dimensions
✓ Step 4 — Collect   : Measured values + auto score calculation (1–5)
✓ Step 5 — Analyse   : JProgressBar scores + Gap Analysis
★ BONUS              : Radar (Spider) Chart via Graphics2D


Health Mode:
  - Scenario A — ClinicSoft  (Security, Reliability, Usability, Performance)
  - Scenario B — MediTrack   (Functional Suitability, Maintainability, Security, Reliability)

Education Mode:
  - Scenario C — Team Alpha  (Usability, Performance, Accessibility, Reliability, Func. Suitability)
  - Scenario D — Team Beta   (Same dimensions, different measured values)


- No external libraries used (pure Java SE)
- Compatible with Java SE 17+
- Eclipse: File > Import > Existing Projects, set src as source folder


 Analyse Results Screen

![Analyse Screen](images/analyse.png)
