import java.io.*;

class StudentRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int roll;
    transient String temporaryNote;
    StudentRecord(String name, int roll, String note) {
        this.name = name; this.roll = roll; this.temporaryNote = note;
    }
    @Override public String toString() {
        return name + " (roll " + roll + ", temporaryNote=" + temporaryNote + ")";
    }
}
public class SaveLoad {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StudentRecord[] students = {
            new StudentRecord("Daksh", 25, "not persisted"),
            new StudentRecord("Riya", 26, "temporary")
        };
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(students);
        }
        StudentRecord[] loaded;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat"))) {
            loaded = (StudentRecord[]) in.readObject();
        }
        for (StudentRecord student : loaded) System.out.println(student);
        System.out.println("Saved and loaded students.dat");
    }
}