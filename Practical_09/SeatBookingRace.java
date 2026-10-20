import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatBookingRace {
    static class Booking {
        private int seatsLeft = 5;
        private int successful = 0;
        synchronized boolean book() {
            if (seatsLeft <= 0) return false;
            seatsLeft--;
            successful++;
            return true;
        }
        synchronized int getSeatsLeft() { return seatsLeft; }
        synchronized int getSuccessful() { return successful; }
    }
    public static void main(String[] args) throws InterruptedException {
        Booking booking = new Booking();
        AtomicInteger successes = new AtomicInteger();
        CountDownLatch start = new CountDownLatch(1);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try { start.await(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
                if (booking.book()) successes.incrementAndGet();
            }, "Customer-" + (i + 1));
            threads[i].start();
        }
        start.countDown();
        for (Thread thread : threads) thread.join();
        System.out.println("Successful bookings: " + successes.get());
        System.out.println("Seats left: " + booking.getSeatsLeft());
        System.out.println("Never more than 5 bookings can succeed.");
    }
}