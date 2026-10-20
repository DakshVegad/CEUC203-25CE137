import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column { String name(); }

class PersonRow {
    @Column(name = "full_name") String name;
    @Column(name = "age") int age;
    @Override public String toString() { return name + ", age " + age; }
}

public class ColumnMapper {
    static PersonRow map(String[] headers, String[] row) throws Exception {
        Map<String, String> values = new HashMap<>();
        for (int i = 0; i < Math.min(headers.length, row.length); i++)
            values.put(headers[i], row[i]);
        PersonRow person = new PersonRow();
        for (Field field : PersonRow.class.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column == null) continue;
            String value = values.get(column.name());
            if (value == null) continue;
            field.setAccessible(true);
            if (field.getType() == int.class) field.setInt(person, Integer.parseInt(value));
            else field.set(person, value);
        }
        return person;
    }
    public static void main(String[] args) throws Exception {
        String[] headers = {"age", "full_name"};
        String[] row = {"18", "Daksh Vegad"};
        System.out.println(map(headers, row));
    }
}