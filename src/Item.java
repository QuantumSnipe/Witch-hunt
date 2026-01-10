public class Item {

    private String description;
    private String name;
    private int weight;
    private boolean isCursed;


    public Item(String description, String name) {
        this.description = description;
        this.name = name.toLowerCase();
        this.weight = 0;
        this.isCursed = false;
    }

    public Item(String description, String name, int weight) {
        this.description = description;
        this.name = name.toLowerCase();
        this.weight = weight;
        this.isCursed = false;
    }

    public Item(String description, String name, int weight, boolean isCursed) {
        this.description = description;
        this.name = name.toLowerCase();
        this.weight = weight;
        this.isCursed = isCursed;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean isCursed() {
        return isCursed;
    }

    public void setCursed(boolean cursed) {
        isCursed = cursed;
    }
}
