package app.model;

public class Task {
    private final String name;
    private boolean completed;
    public Task(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Task name cannot be blank");
        this.name = name;
    }
    public String getName() { return name; }
    public boolean isCompleted() { return completed; }
    public void complete() { completed = true; }
}