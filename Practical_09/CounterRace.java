public class CounterRace {
    static class Counter {
        int value = 0;
        void incrementUnsafe() { value++; }
        synchronized void incrementSafe() { value++; }
    }
    public static void main(String[] args) throws InterruptedException {
        final int threads = 8, increments = 100_000;
        Counter unsafe = new Counter();
        Thread[] group = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            group[i] = new Thread(() -> {
                for (int j = 0; j < increments; j++) unsafe.incrementUnsafe();
            });
            group[i].start();
        }
        for (Thread t : group) t.join();
        System.out.println("Unsafe count: " + unsafe.value + " (expected " + (threads * increments) + ")");

        Counter safe = new Counter();
        for (int i = 0; i < threads; i++) {
            group[i] = new Thread(() -> {
                for (int j = 0; j < increments; j++) safe.incrementSafe();
            });
            group[i].start();
        }
        for (Thread t : group) t.join();
        System.out.println("Synchronized count: " + safe.value);
    }
}