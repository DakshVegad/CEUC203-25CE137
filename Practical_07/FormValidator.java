import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength { int value(); }

class SignupForm {
    @NotBlank @MaxLength(30) String name;
    @NotBlank @MaxLength(60) String email;
    SignupForm(String name, String email) { this.name = name; this.email = email; }
}

public class FormValidator {
    static List<String> validate(Object object) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(object);
            if (field.isAnnotationPresent(NotBlank.class) &&
                (value == null || value.toString().isBlank()))
                errors.add(field.getName() + " must not be blank");
            MaxLength max = field.getAnnotation(MaxLength.class);
            if (max != null && value != null && value.toString().length() > max.value())
                errors.add(field.getName() + " must be at most " + max.value() + " characters");
        }
        return errors;
    }
    public static void main(String[] args) throws IllegalAccessException {
        SignupForm form = new SignupForm(" ", "daksh@example.com");
        List<String> errors = validate(form);
        if (errors.isEmpty()) System.out.println("Form valid");
        else errors.forEach(System.out::println);
    }
}