package app.service;

import app.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    public void addTask(String name) { tasks.add(new Task(name)); }
    public int count() { return tasks.size(); }
    public List<Task> getTasks() { return List.copyOf(tasks); }
}