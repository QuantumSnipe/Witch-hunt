import java.util.*;

/**
 * Represents a location in a text-based adventure game.
 *<p>
 * A Room contains a name and description, and manages connections to other rooms through exits
 * in various directions. Rooms can contain items that can be collected or examined, and NPCs
 * that inhabit the space. Rooms may also be locked, preventing access until unlocked.
 *<p>
 * Each room maintains:
 * - A collection of exits mapped by direction strings (e.g., "north", "south")
 * - A collection of items that can be interacted with
 * - A set of NPCs currently present in the room
 * - A lock state with an optional custom lock message
 */
public class Room {

    private final String name;
    private final String description;
    private final Map<String, Room> exits;
    private final Map<String, Item> items;
    private final Set<NPC> charactersInRoom;

    private String lockMessage;
    private boolean locked;


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new LinkedHashMap<>();
        items = new LinkedHashMap<>();
        charactersInRoom = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setExit(String direction, Room neighbor) {
        if (direction == null | neighbor == null) {
            return;
        }
        exits.put(direction.toLowerCase(), neighbor);
    }

    public Room getExit(String direction) {
        if (direction == null) {
            return null;
        }
        return exits.get(direction);
    }

    public String getShortDescription() {
        return "You are " + name;
    }

    /**
     * Returns a comprehensive description of the room including its short description,
     * full description, available exits, items present, and any NPCs in the room.
     * The description is formatted with line breaks between each section.
     *
     * @return a detailed multi-line string containing all room information including
     *         location name, description, exits, items (if any), and characters (if any)
     */
    public String getLongDescription() {
        StringBuilder descriptionBuilder = new StringBuilder();

        descriptionBuilder
                .append(getShortDescription())
                .append(".\n")
                .append(description)
                .append("\n")
                .append(getExitString());

        if (!items.isEmpty()) {
            descriptionBuilder
                    .append(".\n")
                    .append(getItemInRoomString());
        }

        if (!charactersInRoom.isEmpty()) {
            descriptionBuilder
                    .append(".\n")
                    .append(getNpcInRoomString());
        }
        return descriptionBuilder.toString();
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

    public String getExitString() {
        if (exits.isEmpty()) {
            return "There are no exits!";
        }
        StringBuilder sb = new StringBuilder("Exits: ");
        int count = 0;
        for (String direction : exits.keySet()) {
            if (count > 0) {
                sb.append(", ");
            }
            sb.append(direction);
            count++;
        }
        return sb.toString();
    }

    public String getExitList() {
        return exitList();
    }

    // NPCs
    private String getNpcInRoomString() {
        String npcString = "Character:";
        for (NPC npc : charactersInRoom) {
            npcString += "\n" + npc.getNPCName();
        }
        return npcString;
    }

    public void addNpcToRoom(NPC npc) {
        charactersInRoom.add(npc);
    }

    public void removeNPC(NPC npc) {
        charactersInRoom.remove(npc);
    }

    // Door access
    public boolean isLocked() {
        return locked;
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
            return "There is nothing noteworthy here.";
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

    private String normalizeKey(String key) {
        if (key == null) {
            return "";
        }
        return key.trim().toLowerCase();
    }

    private String normalizeMessage(String message) {
        if (message == null | message.isBlank()) {
            return null;
        }
        return message;
    }

    @Override
    public String toString() {
        return getShortDescription();
    }
}
