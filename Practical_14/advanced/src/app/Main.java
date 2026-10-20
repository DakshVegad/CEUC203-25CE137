package app;

import app.service.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        service.addTask("Complete Java practical");
        service.addTask("Review generics");
        System.out.println("Task count: " + service.count());
    }
}