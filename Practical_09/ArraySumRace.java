import java.util.concurrent.atomic.AtomicLong;

public class ArraySumRace {
    public static void main(String[] args) throws InterruptedException {
        int[] values = new int[1_000_000];
        java.util.Arrays.fill(values, 1);
        int threadsCount = 8;
        long[] unsafeParts = new long[threadsCount];
        long[] safeParts = new long[threadsCount];
        Thread[] threads = new Thread[threadsCount];
        for (int t = 0; t < threadsCount; t++) {
            final int index = t;
            final int from = t * values.length / threadsCount;
            final int to = (t + 1) * values.length / threadsCount;
            threads[t] = new Thread(() -> {
                long local = 0;
                for (int i = from; i < to; i++) local += values[i];
                unsafeParts[index] = local;
                safeParts[index] = local;
            });
            threads[t].start();
        }
        for (Thread thread : threads) thread.join();
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < threadsCount; i++) { sum1 += unsafeParts[i]; sum2 += safeParts[i]; }
        AtomicLong atomicTotal = new AtomicLong();
        for (long part : safeParts) atomicTotal.addAndGet(part);
        System.out.println("Partitioned sum: " + sum1);
        System.out.println("AtomicLong total: " + atomicTotal.get());
        System.out.println("Expected total: " + values.length);
    }
}