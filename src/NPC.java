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
