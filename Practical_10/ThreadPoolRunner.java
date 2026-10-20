import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " running on " + Thread.currentThread().getName());
                try { Thread.sleep(150); }
                catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
            });
        }
        pool.shutdown();
        if (!pool.awaitTermination(5, TimeUnit.SECONDS)) pool.shutdownNow();
        System.out.println("All tasks completed.");
    }
}