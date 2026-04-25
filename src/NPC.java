/**
 * Represents a Non-Player Character (NPC) in a game or interactive system.
 * This class serves as a base representation for NPCs with a name and description.
 * It can be extended by specific NPC types to add additional behavior and attributes.
 */
public class NPC {

    private String name;
    private String description;


    public NPC() {
    }

    public NPC(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getNPCName() {
        return name;
    }

    public String getNPCDescription() {
        return description;
    }

     public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
