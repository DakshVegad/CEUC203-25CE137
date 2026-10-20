import java.util.List;

class OutOfStockException extends Exception {
    private final int shortfall;
    OutOfStockException(String item, int shortfall) {
        super("Out of stock: " + item + " (short by " + shortfall + ")");
        this.shortfall = shortfall;
    }
    public int getShortfall() { return shortfall; }
}
class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) { super(message); }
}
class Warehouse {
    private final java.util.Map<String, Integer> stock = new java.util.HashMap<>();
    Warehouse() { stock.put("Pen", 5); stock.put("Notebook", 2); }
    void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) throw new InvalidQuantityException("Quantity must be positive.");
        int available = stock.getOrDefault(item, 0);
        if (qty > available) throw new OutOfStockException(item, qty - available);
        stock.put(item, available - qty);
        System.out.println("Issued " + qty + " " + item + "(s)");
    }
}
public class StockIssue {
    record Request(String item, int qty) { }
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        List<Request> requests = List.of(
            new Request("Pen", 2), new Request("Notebook", 4),
            new Request("Pen", 0), new Request("Eraser", 1)
        );
        for (Request request : requests) {
            try {
                warehouse.issue(request.item(), request.qty());
            } catch (OutOfStockException | InvalidQuantityException ex) {
                System.out.println("Request failed: " + ex.getMessage());
            }
        }
    }
}