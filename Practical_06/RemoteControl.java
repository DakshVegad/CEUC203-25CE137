interface Switchable {
    void on();
    void off();
    default void toggle(boolean currentlyOn) {
        if (currentlyOn) off(); else on();
    }
}
class Fan implements Switchable {
    public void on() { System.out.println("Fan is ON"); }
    public void off() { System.out.println("Fan is OFF"); }
}
class Light implements Switchable {
    public void on() { System.out.println("Light is ON"); }
    public void off() { System.out.println("Light is OFF"); }
}
@FunctionalInterface interface DevicePermission {
    boolean maySwitchOn(String device, int hour);
}
public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = {new Fan(), new Light()};
        for (Switchable device : devices) {
            device.on();
            device.toggle(true);
        }
        DevicePermission anonymous = new DevicePermission() {
            @Override public boolean maySwitchOn(String device, int hour) {
                return hour >= 6 && hour < 23;
            }
        };
        DevicePermission lambda = (device, hour) -> hour >= 6 && hour < 23;
        System.out.println("Anonymous allows at 20: " + anonymous.maySwitchOn("Fan", 20));
        System.out.println("Lambda allows at 2: " + lambda.maySwitchOn("Light", 2));
    }
}