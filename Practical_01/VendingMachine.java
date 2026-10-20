import java.util.Scanner;

public class VendingMachine {
    enum Coin { ONE, TWO, FIVE, TEN }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int price = 15, total = 0;

        while (total < price) {
            System.out.print("Enter coin (ONE, TWO, FIVE, TEN): ");
            String input = sc.nextLine().trim().toUpperCase();
            Coin coin;
            try {
                coin = Coin.valueOf(input);
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid coin. Try again.");
                continue;
            }

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };
            total += value;
            System.out.println("Total paid: " + total);
        }

        System.out.println("Paid. Change: " + (total - price));
        sc.close();
    }
}