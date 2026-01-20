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
