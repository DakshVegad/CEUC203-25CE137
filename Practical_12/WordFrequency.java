import java.util.*;
import java.util.stream.Collectors;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is powerful and Java is popular. Java supports object oriented programming.";
        Map<String, Integer> counts = new HashMap<>();
        for (String word : paragraph.toLowerCase().replaceAll("[^a-z\\s]", "").split("\\s+")) {
            if (!word.isBlank()) counts.merge(word, 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(counts.entrySet());
        sorted.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                   .thenComparing(Map.Entry.comparingByKey()));
        sorted.forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}