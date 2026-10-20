public class Thermostat {
    private String location;
    private int temperature;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    public Thermostat(String location, int startTemp) {
        this.location = location;
        this.temperature = (startTemp >= MIN && startTemp <= MAX) ? startTemp : 22;
        activeCount++;
    }

    public Thermostat(String location) {
        this(location, 22);
    }

    public void raise() {
        if (temperature < MAX) temperature++;
        else System.out.println("Already at maximum (30)");
    }

    public void lower() {
        if (temperature > MIN) temperature--;
        else System.out.println("Already at minimum (16)");
    }

    public int getTemperature() { return temperature; }
    public static int getActiveCount() { return activeCount; }

    public static void main(String[] args) {
        Thermostat room = new Thermostat("Lab", 28);
        Thermostat office = new Thermostat("Office");
        for (int i = 0; i < 10; i++) {
            room.raise();
            System.out.println(room.location + " temperature: " + room.getTemperature());
        }
        for (int i = 0; i < 20; i++) {
            room.lower();
            System.out.println(room.location + " temperature: " + room.getTemperature());
        }
        System.out.println("Active thermostats: " + getActiveCount());
    }
}