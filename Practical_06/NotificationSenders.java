@FunctionalInterface interface Notifier {
    void send(String message);
}
interface Urgent { }
class EmailNotifier implements Notifier {
    public void send(String message) { System.out.println("EMAIL: " + message); }
}
class SmsNotifier implements Notifier, Urgent {
    public void send(String message) { System.out.println("SMS: " + message); }
}
public class NotificationSenders {
    public static void main(String[] args) {
        Notifier[] senders = {
            new EmailNotifier(),
            new SmsNotifier(),
            message -> System.out.println("PUSH: " + message)
        };
        String message = "Practical submission reminder";
        for (Notifier sender : senders) sender.send(message);
        System.out.println("Urgent senders:");
        for (Notifier sender : senders) {
            if (sender instanceof Urgent) sender.send("URGENT: " + message);
        }
    }
}