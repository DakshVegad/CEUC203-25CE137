import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Asha", 90); scores.put("Bharat", 96);
        scores.put("Chirag", 96); scores.put("Daksh", 88);
        scores.entrySet().stream()
            .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                    .reversed().thenComparing(Map.Entry::getKey))
            .limit(3)
            .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}