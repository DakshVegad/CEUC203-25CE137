class Box<T> {
    private T value;
    public Box(T value) { this.value = value; }
    public T get() { return value; }
    public void set(T value) { this.value = value; }
}
class Pair<A, B> {
    private final A first;
    private final B second;
    Pair(A first, B second) { this.first = first; this.second = second; }
    public A getFirst() { return first; }
    public B getSecond() { return second; }
}
public class GenericContainers {
    public static <T> T first(T[] values) {
        if (values == null || values.length == 0) throw new IllegalArgumentException("Array is empty");
        return values[0];
    }
    public static void main(String[] args) {
        Box<String> textBox = new Box<>("Hello Java");
        Box<Integer> numberBox = new Box<>(42);
        Pair<String, Integer> pair = new Pair<>("Age", 18);
        System.out.println(textBox.get());
        System.out.println(numberBox.get());
        System.out.println(pair.getFirst() + " = " + pair.getSecond());
        System.out.println("First item: " + first(new String[]{"one", "two"}));
    }
}