import java.util.ArrayList;
import java.util.List;

class Task {
    private final String name;
    private boolean completed;
    Task(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Task name cannot be blank");
        this.name = name;
    }
    String getName() { return name; }
    boolean isCompleted() { return completed; }
    void complete() { completed = true; }
}
class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    public void addTask(String name) { tasks.add(new Task(name)); }
    public int count() { return tasks.size(); }
    public void completeTask(int index) {
        if (index < 0 || index >= tasks.size()) throw new IllegalArgumentException("Invalid task index");
        tasks.get(index).complete();
    }
    public List<Task> getTasks() { return List.copyOf(tasks); }
}
public class LayeredApp {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        service.addTask("Complete Java practical");
        service.addTask("Review inheritance");
        service.completeTask(0);
        System.out.println("Task count: " + service.count());
        for (Task task : service.getTasks())
            System.out.println(task.getName() + " | completed=" + task.isCompleted());
    }
}