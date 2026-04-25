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
        return exits.get(direction.toLowerCase());
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

    public String getExitString() {
        if (exits.isEmpty()) {
            return "There are no exits";
        }
        return "Exits: " + String.join(", ", exits.keySet());
    }

    public void addNpcToRoom(NPC npc) {
        if (npc != null) {
            charactersInRoom.add(npc);
        }
    }

    public void removeNPC(NPC npc) {
        charactersInRoom.remove(npc);
    }

    /**
     * Returns a formatted string listing all NPCs currently present in the room.
     * The string includes a header followed by a comma-separated list of NPC names.
     * If no NPCs are present, only the header text is returned.
     *
     * @return a string containing "Characters currently in room: " followed by the names
     *         of all NPCs in the room, separated by commas
     */
    public String getNpcInRoomString() {
        StringBuilder npcString = new StringBuilder("Characters currently in room: ");

        int count = 0;
        for (NPC npc : charactersInRoom) {
            if (count > 0) {
                npcString.append(", ");
            }
            npcString.append(npc.getNPCName());
            count++;
        }
        return npcString.toString();
    }


    public boolean isLocked() {
        return locked;
    }

    public void lock(String message) {
        locked = true;
        lockMessage = normalizeMessage(message);
    }

    public void unlock() {
        locked = false;
        lockMessage = null;
    }

    public String getLockMessage() {
        if (lockMessage == null) {
            return "It won't budge";
        }
        return lockMessage;
    }
    
    // Items
    public void addItemToRoom(Item item) {
        items.put(item.getName(), item);
    }

    public Item getItem(String itemName) {
        return items.get(normalizeKey(itemName));
    }

    public Item removeItemFromRoom(String itemName) {
        return items.remove(normalizeKey(itemName));
    }

    public String getItemInRoomString() {
        if (items.isEmpty()) {
            return "There is nothing noteworthy here.";
        }

        StringBuilder itemString = new StringBuilder("You notice the following items: ");
        int count = 0;
        for (Item item : items.values()) {
            if (count > 0) {
                itemString.append(", ");
            }
            itemString.append(item.getName());
            count++;
        }
        return itemString.toString();
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
