import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

class GenericStack<T> {
    private final List<T> items = new ArrayList<>();
    public void push(T value) { items.add(value); }
    public T pop() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }
    public boolean isEmpty() { return items.isEmpty(); }
}
public class GenericStackDemo {
    public static void main(String[] args) {
        GenericStack<String> words = new GenericStack<>();
        words.push("Java"); words.push("Generics");
        System.out.println(words.pop());
        System.out.println(words.pop());
        GenericStack<Integer> numbers = new GenericStack<>();
        numbers.push(10); numbers.push(20);
        System.out.println(numbers.pop());
        try { numbers.pop(); numbers.pop(); }
        catch (EmptyStackException ex) { System.out.println("Stack is empty"); }
    }
}