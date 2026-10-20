import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run { }

class SampleTests {
    @Run public void testAddition() { System.out.println("Addition test passed"); }
    @Run public void testText() { System.out.println("Text test passed"); }
    public void helper() { System.out.println("This method is not run"); }
}

public class MiniTestRunner {
    public static void main(String[] args) throws Exception {
        Object testObject = new SampleTests();
        int ran = 0;
        for (Method method : testObject.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class) && method.getParameterCount() == 0) {
                method.invoke(testObject);
                ran++;
            }
        }
        System.out.println("Tests run: " + ran);
    }
}