public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be negative");
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public CinemaShow(String title) { this(title, 100); }

    public boolean book(int n) {
        if (n <= 0 || n > seatsAvailable) return false;
        seatsAvailable -= n;
        totalBooked += n;
        return true;
    }

    public void cancel(int n) {
        if (n <= 0) return;
        seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }

    public int getSeatsAvailable() { return seatsAvailable; }
    public static int getTotalBooked() { return totalBooked; }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Java Premiere", 5);
        System.out.println("Book 3: " + show.book(3) + ", seats: " + show.getSeatsAvailable());
        System.out.println("Book 4: " + show.book(4) + ", seats: " + show.getSeatsAvailable());
        System.out.println("Book 2: " + show.book(2) + ", seats: " + show.getSeatsAvailable());
        show.cancel(1);
        System.out.println("After cancelling 1, seats: " + show.getSeatsAvailable());
        System.out.println("Total booked: " + getTotalBooked());
    }
}