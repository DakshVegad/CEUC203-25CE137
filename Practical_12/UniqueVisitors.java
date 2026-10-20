import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.List;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> logins = Arrays.asList("daksh", "riya", "daksh", "arjun", "riya", "meera");
        HashSet<String> unique = new HashSet<>(logins);
        LinkedHashSet<String> firstSeen = new LinkedHashSet<>(logins);
        System.out.println("Unique count: " + unique.size());
        System.out.println("First three distinct visitors:");
        firstSeen.stream().limit(3).forEach(System.out::println);
    }
}