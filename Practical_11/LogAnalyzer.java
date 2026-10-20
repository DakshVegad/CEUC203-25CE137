import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path folder = Path.of("sample-logs");
        Files.createDirectories(folder);
        Files.write(folder.resolve("app1.log"), List.of("INFO started", "ERROR disk", "INFO done"));
        Files.write(folder.resolve("app2.log"), List.of("INFO ready", "ERROR network"));

        String keyword = "ERROR";
        int totalLines = 0, matches = 0;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(folder, "*.log")) {
            for (Path file : files) {
                List<String> lines = Files.readAllLines(file);
                int fileMatches = 0;
                for (String line : lines) {
                    totalLines++;
                    if (line.contains(keyword)) { matches++; fileMatches++; }
                }
                BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
                System.out.println(file.getFileName() + " size=" + attrs.size() +
                                   " bytes, lines=" + lines.size() + ", " + keyword + "=" + fileMatches);
            }
        }
        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing " + keyword + ": " + matches);
    }
}