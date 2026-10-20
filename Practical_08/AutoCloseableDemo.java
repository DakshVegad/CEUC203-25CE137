class DemoResource implements AutoCloseable {
    public void use() throws Exception {
        System.out.println("Resource is being used");
        throw new Exception("Original operation error");
    }
    @Override public void close() {
        System.out.println("Resource closed");
    }
}
public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (DemoResource resource = new DemoResource()) {
            resource.use();
        } catch (Exception ex) {
            System.out.println("Caught: " + ex.getMessage());
        }
    }
}