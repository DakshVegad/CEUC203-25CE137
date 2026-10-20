public class PasswordChecker {
    public static String strength(String pw) {
        int passed = 0;
        if (pw.length() >= 8) passed++;
        if (pw.matches(".*[A-Z].*")) passed++;
        if (pw.matches(".*[0-9].*")) passed++;
        if (pw.matches(".*[^A-Za-z0-9].*")) passed++;
        if (passed <= 1) return "Weak";
        if (passed <= 3) return "Medium";
        return "Strong";
    }
    public static void main(String[] args) {
        String[] passwords = {"abc", "abcdefgh", "Abcdefgh", "Abcd1234!"};
        for (String pw : passwords) {
            System.out.println(pw + " -> " + strength(pw));
        }
    }
}