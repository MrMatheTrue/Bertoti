package com.example.school;

import java.util.UUID;

public class Teacher {
    private final String id;
    private final String name;
    private final String email;

    public Teacher(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Teacher{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}
