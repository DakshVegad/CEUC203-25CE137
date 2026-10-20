import java.util.Scanner;

class DivideByZeroException extends Exception {
    DivideByZeroException(String message) { super(message); }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        while (!success) {
            try {
                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine().trim());
                System.out.print("Enter operator (+, -, *, /): ");
                String op = sc.nextLine().trim();
                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine().trim());
                if (op.equals("/") && b == 0) throw new DivideByZeroException("Cannot divide by zero.");
                double result = switch (op) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    default -> throw new IllegalArgumentException("Invalid operator.");
                };
                System.out.println("Result: " + result);
                success = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please try again.");
            } catch (DivideByZeroException ex) {
                System.out.println(ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } finally {
                System.out.println("Calculation attempt completed.");
            }
        }
        sc.close();
    }
}