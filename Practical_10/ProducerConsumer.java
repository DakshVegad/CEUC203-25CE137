import java.util.ArrayDeque;
import java.util.Queue;

public class ProducerConsumer {
    static class Buffer {
        private final Queue<Integer> queue = new ArrayDeque<>();
        private final int capacity = 2;
        synchronized void put(int value) throws InterruptedException {
            while (queue.size() == capacity) wait();
            queue.add(value);
            System.out.println("Produced: " + value);
            notifyAll();
        }
        synchronized int take() throws InterruptedException {
            while (queue.isEmpty()) wait();
            int value = queue.remove();
            System.out.println("Consumed: " + value);
            notifyAll();
            return value;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Thread producer = new Thread(() -> {
            try { for (int i = 1; i <= 5; i++) { buffer.put(i); Thread.sleep(50); } }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "Producer");
        Thread consumer = new Thread(() -> {
            try { for (int i = 1; i <= 5; i++) { buffer.take(); Thread.sleep(100); } }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "Consumer");
        producer.start(); consumer.start();
        producer.join(); consumer.join();
        System.out.println("Producer-consumer finished.");
    }
}