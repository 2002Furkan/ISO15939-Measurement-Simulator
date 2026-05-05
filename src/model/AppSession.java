package model;

public class AppSession {
    private UserProfile profile;
    private String qualityType;  
    private String mode;         
    private String scenario;     
    private SWSystem system;     

    public AppSession() {
        this.profile = new UserProfile();
    }

   
    public UserProfile getProfile()            { return profile; }
    public String getQualityType()             { return qualityType; }
    public void setQualityType(String v)       { this.qualityType = v; }
    public String getMode()                    { return mode; }
    public void setMode(String v)              { this.mode = v; }
    public String getScenario()                { return scenario; }
    public void setScenario(String v)          { this.scenario = v; }
    public SWSystem getSystem()                { return system; }
    public void setSystem(SWSystem s)          { this.system = s; }
}
