package models;

public class Program {
    private int programId;
    private String programName;
    private String programDescription;
    private String duration;
    private String category;

    public Program(String programName, String programDescription, String duration, String category) {
        programId++;
        this.programName = programName;
        this.programDescription = programDescription;
        this.duration = duration;
        this.category = category;
    }

    public void setProgramName(String newProgramName) {
        this.programName = newProgramName;
    }

    public void setProgramDescription(String newProgramDescription) {
        this.programDescription = newProgramDescription;
    }

    public void setDuration(String newDuration)
    {
        this.duration = newDuration;
    }

    public void setCategory(String newCategory)
    {
        this.category = newCategory;
    }

    public String getProgramName()
    {
        return this.programName;
    }

    public String getProgramDescription()
    {
        return this.programDescription;
    }

    public String getDuration()
    {
        return this.duration;
    }

    public String getCategory()
    {
        return this.category;
    }
}
