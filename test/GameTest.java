import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;
    private Room square, tavern, forge;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testGoEastFromSquareToTavern() {
        game.processCommand(new Command(CommandWord.GO, "east"));
        assertEquals("You are in the Black Goat Tavern", game.getCurrentRoom().getShortDescription());
    }

}
