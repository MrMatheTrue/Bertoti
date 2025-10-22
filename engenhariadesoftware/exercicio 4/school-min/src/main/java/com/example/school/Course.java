package com.example.school;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    private final String id;
    private String name;
    private int workloadHours;
    private Teacher teacher;
    private final List<Student> students = new ArrayList<>();

    public Course(String name, int workloadHours) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.workloadHours = workloadHours;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getWorkloadHours() { return workloadHours; }
    public Teacher getTeacher() { return teacher; }
    public List<Student> getStudents() { return students; }

    public void setName(String name) { this.name = name; }
    public void setWorkloadHours(int workloadHours) { this.workloadHours = workloadHours; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public void addStudent(Student s) {
        if (s != null && !students.contains(s)) {
            students.add(s);
        }
    }

    @Override
    public String toString() {
        return "Course{id='" + id + "', name='" + name + "', workloadHours=" + workloadHours +
               ", teacher=" + (teacher != null ? teacher.getName() : "null") +
               ", students=" + students.size() + "}";
    }
}
