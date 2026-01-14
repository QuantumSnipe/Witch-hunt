import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private Room room;
    private Item item;


    @Before
    public void setUp() {
        player = new Player();
        room = new Room("Church");
    }

    @Test
    public void testAddItem_WhenItemIsNull_ShouldReturnFalse() {
        assertFalse(player.addItem(null));
        assertEquals(0, player.getCurrentWeight());
    }

    @Test
    public void testAddItem_WeightExceedsMax() {
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
