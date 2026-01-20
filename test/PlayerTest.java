import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player();
        
    }

    @Test
    public void testAddItem_WhenItemIsNull_ShouldReturnFalse() {
        assertFalse(player.addItem(null));
        assertEquals(0, player.getCurrentWeight());
    }

    @Test
    public void testAddItem_WeightExceedsMax() {
        Item item = new Item("book", "book", 2);
        assertFalse(player.addItem(item));
        assertEquals(0, player.getCurrentWeight());
    }

    @Test
    public void testAddItem_ItemIsCursed_PlayerCursed() {
        Item sword = new Item("sword", "Longsword", 8);
        sword.setCursed(true);
        assertTrue(player.addItem(sword));
        assertEquals(95, player.getSanity());

        player.setCursed(true);
        Item spellbook = new Item("book", "Spellbook", 3);
        spellbook.setCursed(true);
        assertTrue(player.addItem(spellbook));
        assertEquals(85, player.getSanity());
    }
}
