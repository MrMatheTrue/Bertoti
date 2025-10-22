package com.example.school;

import java.util.UUID;

public class App {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Ada Lovelace", "ada@school.edu");
        Student s1 = new Student("Grace Hopper", "grace@school.edu");
        Student s2 = new Student("Linus Torvalds", "linus@school.edu");

        Course course = new Course("Estruturas de Dados", 60);
        course.setTeacher(teacher);
        course.addStudent(s1);
        course.addStudent(s2);

        System.out.println("=== DEMO ===");
        System.out.println("Professor: " + teacher);
        System.out.println("Alunos: " + s1 + " | " + s2);
        System.out.println("Curso: " + course);
        System.out.println("Relação simples criada em memória, sem banco, só para rodar e ver.");
    }
}
