import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

public class DirectoryReport {
    public static void main(String[] args) throws IOException {
        Path root = Path.of(".");
        Path report = Path.of("directory-report.txt");
        StringBuilder text = new StringBuilder();
        try (var paths = Files.walk(root, 3)) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                try {
                    BasicFileAttributes a = Files.readAttributes(path, BasicFileAttributes.class);
                    text.append(path).append(" | ").append(a.size()).append(" bytes | ")
                        .append(Instant.ofEpochMilli(a.lastModifiedTime().toMillis())).append(System.lineSeparator());
                } catch (IOException e) {
                    text.append(path).append(" | ERROR: ").append(e.getMessage()).append(System.lineSeparator());
                }
            });
        }
        Files.writeString(report, text.toString());
        System.out.println("Report written to " + report.toAbsolutePath());
        System.out.println("Entries: " + text.toString().lines().count());
    }
}