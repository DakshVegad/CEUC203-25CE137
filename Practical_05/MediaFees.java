abstract class MediaItem {
    protected String title;
    MediaItem(String title) { this.title = title; }
    abstract double lateFee(int daysLate);
}
class BookItem extends MediaItem {
    BookItem(String title) { super(title); }
    @Override double lateFee(int days) { return Math.max(0, days) * 2.0; }
}
class DVDItem extends MediaItem {
    DVDItem(String title) { super(title); }
    @Override double lateFee(int days) { return Math.max(0, days) * 5.0; }
}
class MagazineItem extends MediaItem {
    MagazineItem(String title) { super(title); }
    @Override double lateFee(int days) { return Math.max(0, days) * 1.0; }
}
public class MediaFees {
    public static void main(String[] args) {
        MediaItem[] items = {new BookItem("Java Basics"), new DVDItem("Nature Film"),
                             new MagazineItem("Science Monthly")};
        int[] days = {3, 2, 5};
        double total = 0;
        for (int i = 0; i < items.length; i++) {
            double fee = items[i].lateFee(days[i]);
            System.out.printf("%s: %.2f%n", items[i].title, fee);
            total += fee;
        }
        System.out.printf("Total late fees: %.2f%n", total);
    }
}