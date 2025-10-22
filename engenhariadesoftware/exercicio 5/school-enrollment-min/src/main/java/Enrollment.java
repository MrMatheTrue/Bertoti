public class Enrollment {
    private String id;
    private Student student;
    private Course course;

    public Enrollment(String id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }

    public String getId()      { return id; }
    public Student getStudent(){ return student; }
    public Course getCourse()  { return course; }
}
