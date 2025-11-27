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
        return "You are in " + name + ".\n" + description + " What do you want to do next?" + "\nExits: " + exitList();
    }

      private String exitList() {
        if (exits.isEmpty()) {
            return "none";
        }
        return String.join(", ", exits.keySet());
    }

    public String getNpc() {
        return npc;
    }

    public void setNpc(String npc) {
        this.npc = npc;
    }

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

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }
}
