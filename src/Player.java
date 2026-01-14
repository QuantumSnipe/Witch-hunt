import java.util.HashMap;

/**
 * Player class, the main character of the game and controllable.
 */
public class Player {
    private final HashMap<String, Item> inventory;
    private int maxWeight = 50;
    private int currentWeight = 0;
    private int sanity = 100;
    private int faith = 0;
    private int suspicion = 0;
    private boolean isCursed;

    public Player() {
        inventory = new HashMap<>();
    }

    /**
     * Attempts to take an item from the current room and add it
     * to the player's inventory
     * @param itemName The name of the item to take
     * @return true if the item was successfully taken, false
     * otherwise
     */
    public boolean takeItem(String itemName, Room currentRoom) {
        if (itemName == null || itemName.trim().isEmpty()) {
            System.out.println("Take what?");
            return false;
        }
        Item item = currentRoom.getItem(itemName);
        if (item == null) {
            System.out.println("There is no " + itemName + " here!");
            return false;
        }
        currentRoom.removeItemFromRoom(itemName);  // remove from room first

        // Try adding item to inventory
        return addItem(item);
    }

    /**
     * Add an item to inventory
     * @param item The item to add
     * @return true if added successfully, false otherwise
     */
    public boolean addItem(Item item) {
        if (item == null) {
            return false;
        }

        // Check weight limit
        if (currentWeight + item.getWeight() > maxWeight) {
            System.out.println("You can't carry that much weight. Current: " + currentWeight +
                    "/" + maxWeight);
            return false;
        }

        // Check for cursed items
        if (item.isCursed()) {
            if (this.isCursed) {
                sanity -= 10; // Additional penalty for cursed player
                System.out.println("This cursed item affects you deeply! Sanity decreased");
            } else {
                sanity -= 5;
                System.out.println("This item is cursed! Your sanity has been affected.");
            }
        }

        // Add to inventory
        inventory.put(item.getName(), item);
        currentWeight += item.getWeight();
        System.out.println("You picked up " + item.getName());
        return true;
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

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
        System.out.println("Current sanity: " + sanity);
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public void setSuspicion(int suspicion) {
        this.suspicion = suspicion;
    }

    public void setCursed(boolean cursed) {
        isCursed = cursed;
    }
}
