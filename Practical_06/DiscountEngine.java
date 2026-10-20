import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class DiscountEngine {
    public static void main(String[] args) {
        List<Double> prices = List.of(100.0, 250.0, 500.0);
        DoubleUnaryOperator noDiscount = price -> price;
        DoubleUnaryOperator tenPercent = price -> price * 0.90;
        DoubleUnaryOperator fixed50 = price -> Math.max(0, price - 50);

        Scanner sc = new Scanner(System.in);
        System.out.print("Choose discount (1=none, 2=10%, 3=minus 50): ");
        int choice = sc.nextInt();
        DoubleUnaryOperator rule = switch (choice) {
            case 1 -> noDiscount;
            case 2 -> tenPercent;
            case 3 -> fixed50;
            default -> null;
        };
        if (rule == null) System.out.println("Invalid choice");
        else for (double price : prices)
            System.out.printf("%.2f -> %.2f%n", price, rule.applyAsDouble(price));
        sc.close();
    }
}