// JUnit 5 test file. Add JUnit Jupiter to the IDE/project test dependencies to run this file.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LayeredAppTest {
    @Test
    void addingTaskIncreasesCount() {
        TaskService service = new TaskService();
        service.addTask("Study Java");
        assertEquals(1, service.count());
    }

    @Test
    void invalidTaskNameThrows() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.addTask(" "));
    }

    @Test
    void completingTaskUpdatesState() {
        TaskService service = new TaskService();
        service.addTask("Submit assignment");
        service.completeTask(0);
        assertTrue(service.getTasks().get(0).isCompleted());
    }
}