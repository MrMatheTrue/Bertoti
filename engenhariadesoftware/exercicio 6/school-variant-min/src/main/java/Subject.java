import java.util.UUID;

public class Subject {
    private final UUID id;
    private final String title;
    private final short hours;
    private final Level level;
    private final Instructor instructor;

    public Subject(UUID id, String title, short hours, Level level, Instructor instructor) {
        this.id = id;
        this.title = title;
        this.hours = hours;
        this.level = level;
        this.instructor = instructor;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public short getHours() { return hours; }
    public Level getLevel() { return level; }
    public Instructor getInstructor() { return instructor; }
}
