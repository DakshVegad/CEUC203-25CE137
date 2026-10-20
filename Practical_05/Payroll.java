abstract class Employee {
    protected String name, id;
    Employee(String name, String id) { this.name = name; this.id = id; }
    abstract double monthlySalary();
}
class FullTime extends Employee {
    private final double salary;
    FullTime(String n, String id, double salary) { super(n, id); this.salary = salary; }
    @Override double monthlySalary() { return salary; }
}
class PartTime extends Employee {
    private final int hours;
    private final double rate;
    PartTime(String n, String id, int hours, double rate) {
        super(n, id); this.hours = hours; this.rate = rate;
    }
    @Override double monthlySalary() { return hours * rate; }
}
class Intern extends Employee {
    private final double stipend;
    Intern(String n, String id, double stipend) { super(n, id); this.stipend = stipend; }
    @Override double monthlySalary() { return stipend; }
}
public class Payroll {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Asha", "E01", 50000),
            new PartTime("Bharat", "E02", 60, 300),
            new Intern("Chirag", "E03", 8000)
        };
        double total = 0;
        for (Employee e : employees) {
            double salary = e.monthlySalary();
            System.out.printf("%s (%s): %.2f%n", e.name, e.id, salary);
            if (e instanceof Intern) System.out.println("  Note: intern stipend");
            total += salary;
        }
        System.out.printf("Total payroll: %.2f%n", total);
    }
}