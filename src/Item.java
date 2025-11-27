public class Item {

    private String description;
    private String name;
    private int weight;

    public Item(String description, String name) {
        this.description = description;
        this.name = name;
        this.weight = 0;
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
}
