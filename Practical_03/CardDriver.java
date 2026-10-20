import java.util.ArrayList;
import java.util.List;

public class CardDriver {
    public static void main(String[] args) {
        Card[] input = {
            new Card("Ace", "Spades"), new Card("King", "Hearts"),
            new Card("Ace", "Spades"), new Card("Queen", "Clubs")
        };
        List<Card> accepted = new ArrayList<>();
        for (Card card : input) {
            boolean duplicate = false;
            for (Card earlier : accepted) {
                if (card.equals(earlier)) { duplicate = true; break; }
            }
            if (duplicate) {
                System.out.println("Duplicate found: " + card);
                break;
            }
            accepted.add(card);
        }
    }
}