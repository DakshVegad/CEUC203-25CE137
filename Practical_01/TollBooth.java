import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0, bikes = 0, cars = 0, trucks = 0;

        while (true) {
            System.out.print("Vehicle number (or done): ");
            String number = sc.nextLine().trim();
            if (number.equalsIgnoreCase("done")) break;

            System.out.print("Vehicle type (bike/car/truck): ");
            String type = sc.nextLine().trim().toLowerCase();
            int toll = switch (type) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> -1;
            };
            if (toll < 0) {
                System.out.println("Invalid vehicle type; vehicle not counted.");
                continue;
            }

            Vehicle vehicle = new Vehicle(number, type);
            total += toll;
            switch (vehicle.type()) {
                case "bike" -> bikes++;
                case "car" -> cars++;
                case "truck" -> trucks++;
            }
            System.out.println("Recorded: " + vehicle + ", toll: " + toll);
        }

        String frequent = "none";
        int highest = 0;
        if (bikes > highest) { highest = bikes; frequent = "bike"; }
        if (cars > highest) { highest = cars; frequent = "car"; }
        if (trucks > highest) { highest = trucks; frequent = "truck"; }
        System.out.println("Total toll: " + total);
        System.out.println("Most frequent: " + frequent);
        sc.close();
    }
}