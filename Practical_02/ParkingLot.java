public class ParkingLot {
    private int twoWheelers = 0;
    private int fourWheelers = 0;
    private final int twoCap;
    private final int fourCap;
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        if (twoCap < 0 || fourCap < 0) throw new IllegalArgumentException("Capacity cannot be negative");
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    public boolean park(String type) {
        if ("two".equalsIgnoreCase(type)) {
            if (twoWheelers >= twoCap) { System.out.println("Full"); return false; }
            twoWheelers++; revenue += 20; return true;
        } else if ("four".equalsIgnoreCase(type)) {
            if (fourWheelers >= fourCap) { System.out.println("Full"); return false; }
            fourWheelers++; revenue += 40; return true;
        }
        System.out.println("Invalid type");
        return false;
    }

    public void leave(String type) {
        if ("two".equalsIgnoreCase(type) && twoWheelers > 0) twoWheelers--;
        else if ("four".equalsIgnoreCase(type) && fourWheelers > 0) fourWheelers--;
    }

    public static long getRevenue() { return revenue; }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 1);
        System.out.println("Park two-wheeler: " + lot.park("two"));
        System.out.println("Park two-wheeler: " + lot.park("two"));
        System.out.println("Park two-wheeler: " + lot.park("two"));
        System.out.println("Park four-wheeler: " + lot.park("four"));
        System.out.println("Park four-wheeler: " + lot.park("four"));
        lot.leave("two");
        System.out.println("Occupancy: two=" + lot.twoWheelers + ", four=" + lot.fourWheelers);
        System.out.println("Revenue: " + getRevenue());
    }
}