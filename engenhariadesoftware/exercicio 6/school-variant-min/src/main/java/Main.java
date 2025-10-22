import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Instructor
        Instructor inst = new Instructor(UUID.randomUUID(), "Ana", "ana@escola.com");

        // Subject with instructor (using short hours and Level enum to diferenciar)
        Subject sub = new Subject(UUID.randomUUID(), "Algoritmos", (short) 60, Level.BEGINNER, inst);

        // Learner
        Learner l = new Learner(UUID.randomUUID(), "João", "joao@aluno.com");

        // Prints
        System.out.println("Learner: " + l.getId() + " - " + l.getFullName() + " - " + l.getEmail());
        System.out.println("Instructor: " + sub.getInstructor().getFullName());
        System.out.println("Subject: " + sub.getTitle() + " (" + sub.getHours() + "h, " + sub.getLevel() + ")");
    }
}
