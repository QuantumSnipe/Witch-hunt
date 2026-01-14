import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;
    private Room square, tavern, forge;
    private Player player;


    @BeforeEach
    void setUp() {
        game = new Game();
        player = new Player();
    }

    @Test
    void testGoEastFromSquareToTavern() {
        game.processCommand(new Command(CommandWord.GO, "east"));
        assertEquals("You are in the Black Goat Tavern", game.getCurrentRoom().getShortDescription());
    }
}
