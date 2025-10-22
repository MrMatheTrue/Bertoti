public class Course {
    private String id;
    private String name;
    private int workloadHours;
    private Teacher teacher;

    public Course(String id, String name, int workloadHours, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.workloadHours = workloadHours;
        this.teacher = teacher;
    }

    public String getId()          { return id; }
    public String getName()        { return name; }
    public int getWorkloadHours()  { return workloadHours; }
    public Teacher getTeacher()    { return teacher; }
}
