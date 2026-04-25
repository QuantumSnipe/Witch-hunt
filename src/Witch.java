/**
 * Represents a Witch NPC with magical spell-casting abilities.
 * This class extends the base NPC class and adds spell power capabilities
 * that define the strength of the witch's magical abilities.
 */
public class Witch extends NPC {

    private int spellPower;

    public Witch(String name, String description, int spellPower) {
        super(name, description);
        this.spellPower = spellPower;
    }

    public int getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(int spellPower) {
        this.spellPower = spellPower;
    }


}
