import java.util.UUID;

public class Instructor {
    private final UUID id;
    private final String fullName;
    private final String email;

    public Instructor(UUID id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}
