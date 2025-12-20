import java.util.HashMap;

/**
 * Player class, the main character of the game and controllable.
 */
public class Player {
    private HashMap<String, Item> inventory;
    private int maxWeight = 50;
    private int currentWeight = 0;
    private int sanity = 100;
    private int faith = 0;
    private int suspicion = 0;

    public Player() {
        inventory = new HashMap<>();
    }

    public void addItem(Item item) {
        if (maxWeight <= 5) {
            if (item != null) {
                inventory.put(item.getName(), item);
                currentWeight += item.getWeight();
                System.out.println("You picked " + item);
            }
        } else {
            System.out.println("You reached your weight limit. You can't carry more items");
        }
    }

    public Item removeItem(String itemName) {
        Item item = inventory.remove(itemName);
        if (item != null) {
            currentWeight -= item.getWeight();
        }
        return item;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getSanity() {
        return sanity;
    }

    public int getFaith() {
        return faith;
    }

    public int getSuspicion() {
        return suspicion;
    }

    
}
