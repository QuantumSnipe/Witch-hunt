import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;


    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGoEastFromSquareToTavern() {
        game.processCommand(new Command(CommandWord.GO, "east"));
        assertEquals("You are in the Black Goat Tavern", game.getCurrentRoom().getShortDescription());
    }

    @Test
    public void testTakeItem_NoSecondWord() {
        Command command = new Command(null, null);
        game.takeItem(command);
    }
}
