import java.util.HashMap;

public class Room {

    private final String name;
    private final String description;
    private final HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    private String npc;
    private String lockMessage;
    private boolean isLocked;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        if (direction == null) {
            return null;
        }
        return exits.get(direction);
    }

    public String getLongDescription() {
        String longDescription = "You are " + name + ".\n" + description + "\nExits: " + exitList();
        return longDescription + "\n" + getExitList() + ".\n" + getItemInRoomString();
    }

    public String getShortDescription() {
        StringBuilder sb = new StringBuilder();
        return sb.append("You are ").append(name).toString();
    }

      private String exitList() {
        if (exits.isEmpty()) {
            return "none";
        }
        return String.join(", ", exits.keySet());
    }

    public String getExitList() {
        return exitList();
    }

    // NPCs
    public String getNpc() {
        return npc;
    }

    public void setNpc(String npc) {
        this.npc = npc;
    }

    // Door access
    public boolean isLocked() {
        return isLocked;
    }

    public void lock(String message) {
        isLocked = true;
        lockMessage = (message == null || message.isBlank()) ? null : message;
    }

    public String getLockMessage() {
        return (lockMessage == null || lockMessage.isBlank()) ? "It won't budge." : lockMessage;
    }
    
    // Items
    public void addItemToRoom(Item item) {
        items.put(item.getName(), item);
    }

    public Item getItem(String itemName) {
        return items.get(itemName.toLowerCase());
    }

    public Item removeItemFromRoom(String itemName) {
        return items.remove(itemName.toLowerCase());
    }

    public String getItemInRoomString() {
        if (items.isEmpty()) {
            System.out.println("There is nothing noteworthy here.");
        }
        StringBuilder sb = new StringBuilder("You notice the following items: ");
        int count = 0;
        for (Item item : items.values()) {
            if (count > 0) {
                sb.append(", ");
            }
            sb.append(item.getName());
            count++;
        }
        return sb.toString();
    }
}
