public class NPC {

    private String name;
    private String description;


    public NPC(String name, String description, String itemName, String itemDesc) {
        this.description = description;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
