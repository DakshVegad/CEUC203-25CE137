public class DeadlockDemo {
    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    private static void unsafeDemo() throws InterruptedException {
        Thread one = new Thread(() -> {
            synchronized (LOCK_A) {
                sleep(100);
                synchronized (LOCK_B) { System.out.println("Thread 1 got both locks"); }
            }
        }, "Unsafe-1");
        Thread two = new Thread(() -> {
            synchronized (LOCK_B) {
                sleep(100);
                synchronized (LOCK_A) { System.out.println("Thread 2 got both locks"); }
            }
        }, "Unsafe-2");
        one.setDaemon(true); two.setDaemon(true);
        one.start(); two.start();
        one.join(300);
        two.join(300);
        System.out.println("Unsafe version may deadlock; daemon threads prevent the demo from hanging.");
    }
    private static void safeDemo() throws InterruptedException {
        Thread one = new Thread(() -> transfer("Safe-1"), "Safe-1");
        Thread two = new Thread(() -> transfer("Safe-2"), "Safe-2");
        one.start(); two.start();
        one.join(); two.join();
        System.out.println("Fixed version completed.");
    }
    private static void transfer(String name) {
        // Always acquire locks in the same order: A, then B.
        synchronized (LOCK_A) {
            synchronized (LOCK_B) { System.out.println(name + " acquired locks in consistent order"); }
        }
    }
    private static void sleep(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    public static void main(String[] args) throws InterruptedException {
        unsafeDemo();
        safeDemo();
    }
}