import java.util.Scanner;

public class ChatFilter {
    public static void main(String[] args) {
        String[] lines = {
            "10:05 alice Hello there",
            "10:06 bob I am fine",
            "malformed",
            "10:07 cara Goodbye"
        };
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine().toLowerCase();
        StringBuilder report = new StringBuilder();
        int matches = 0;

        for (String line : lines) {
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) continue;
            if (parts[2].toLowerCase().contains(keyword)) {
                matches++;
                report.append(parts[0]).append(" ").append(parts[1]).append(": ")
                      .append(parts[2]).append(System.lineSeparator());
            }
        }
        System.out.println("Matches: " + matches);
        System.out.print(report);
        sc.close();
    }
}