public class NPC {

    private String name;
    private String description;


    public NPC(String name, String description) {
        this.description = description;
        this.name = name;

    }

    public String getNPCName() {
        return name;
    }

    public String getNPCDescription() {
        return description;
    }
}
