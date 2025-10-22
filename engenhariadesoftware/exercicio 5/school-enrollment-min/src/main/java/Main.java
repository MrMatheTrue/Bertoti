public class Main {
    public static void main(String[] args) {
        // Professor
        Teacher t = new Teacher("T1", "Ana", "ana@escola.com");

        // Curso com professor
        Course c = new Course("C1", "Algoritmos", 60, t);

        // Aluno
        Student s = new Student("S1", "João", "joao@aluno.com");

        // Matrícula (Student -> Course)
        Enrollment e = new Enrollment("E1", s, c);

        // Prints
        System.out.println("Aluno: " + s.getId() + " - " + s.getName() + " - " + s.getEmail());
        System.out.println("Curso: " + c.getName() + " (" + c.getWorkloadHours() + "h) | Professor: " + c.getTeacher().getName());
        System.out.println("Matrícula: " + e.getId() + " | Student=" + e.getStudent().getName() + " | Course=" + e.getCourse().getName());
    }
}
