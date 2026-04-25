/**
 * Main game engine for a text-based adventure game set in a historical setting.
 * Manages the game world, player interactions, and command processing.
 *
 * The Game class coordinates all major game systems including room navigation,
 * item management, NPC interactions, and command parsing. It initializes the
 * game world by creating rooms, items, and NPCs, then enters a main game loop
 * that processes player commands until the game ends.
 *
 * The game supports various commands including movement between rooms, examining
 * and collecting items, talking to NPCs, and receiving help information.
 */
public class Game {
    private final Parser parser;
    private Room currentRoom;
    private Player player;

    public Game() {
        parser = new Parser();
        player = new Player();
        createRooms();
    }

    private void createRooms() {
        Room square = new Room("in the Village Square", "Cobblestones slick with fog. A muddy open space ringed by " +
                "crooked timber framed houses that lean inward. A young blond woman is locked in the stocks, her wrists " +
                "and neck trapped in the heavy boards.");

        Room church = new Room("in St. Aldric's Church", "Cold stone arches with damp. Candles sputter before a crooked altar. " +
                "The scent of incense struggles to hide the rot beneath the floorboards.");

        Room graveyard = new Room("in the graveyard", "Iron gates lean inward, guarding rows of tilting headstones. " +
                "A raven watches from a cracked angel's shoulders.");

        Room tavern = new Room("in the Black Goat Tavern", "Smoke, ale, and whispers. Eyes " +
                "follow you from the shadows.");

        Room gallows = new Room("Gallows Hill", "You stand atop the barren hill where the village hangs its condemned. " +
                "A weathered wooden scaffold looms overhead. Below it lies a patch of trampled earth stained dark " +
                "with old blood. ");

        Room blacksmith = new Room("Blacksmith's Forge", "Tools hang in perfect rows-except one hammer, crusted with " +
                "dried blood.");

        Room mayor = new Room("Mayor Hawthorne's Manor", "Polished oak and velvet lies. Portraits from past mayors " +
                "hang around. A small " +
                "fire burns red and blue.");

        Room well = new Room("Village Well", "A stone ring in the square's corner.");

        Room forest = new Room("The Dark Woods", "The trees stand tall and too close together, " +
                "their roots clutching bones of old paths.");

        Room witchHut = new Room("Witch's Hut (Forest Edge)", "A crooked cottage of bones and thatch. " +
                "Herbs dry upside-down. A cauldron bubbles with something...");

        Room apothecary = new Room("Apothecary shop", "The air is thick with the smell of dried herbs.");

        Room meetingHouse = new Room("the village meeting house", "The old court house. The single room is lit only by a dying fire.");

        Room churchCellar = new Room("the damp cellar beneath the church", "The air is cold and stale. " +
                "Chains hang from the walls, and a small wooden cage stands empty in the corner.");

        Room magistratesHouse = new Room("the magistrate's house", "Dark wood paneling and heavy furniture speak of wealth and authority. " +
                "A large desk holds papers and a quill still wet with ink. A locked door leads deeper in the house.");

        Room oldCemetery = new Room("the old cemetery", "Moss-covered headstones lean at odd angles. " +
                "A fresh grave has been dug recently-no marker yet.");

        Room herbGarden = new Room("the herbalist's overgrown garden", "Weeds and strange plants choke the once-tidy rows. " +
                "A small stone well stands in the center.");


        // initialize exits and directions
        square.setExit("east", tavern);
        square.setExit("north-east", church);
        square.setExit("south-west", blacksmith);
        square.setExit("west", well);
        square.setExit("north-west", forest);

        church.setExit("east", graveyard);
        church.setExit("south-west", square);
        church.setExit("north-west", forest);

        graveyard.setExit("west", church);
        graveyard.setExit("north", oldCemetery);

        oldCemetery.setExit("west", herbGarden);

        herbGarden.setExit("west-east", gallows);
        herbGarden.setExit("south", apothecary);
        herbGarden.setExit("east", oldCemetery);
        herbGarden.setExit("north", witchHut);

        blacksmith.setExit("east", mayor);
        blacksmith.setExit("north", tavern);
        blacksmith.setExit("south", gallows);

        mayor.setExit("west", blacksmith);
        mayor.setExit("north-east", tavern);
        mayor.setExit("south-east", gallows);

        tavern.setExit("west", square);

        apothecary.setExit("south", tavern);
        apothecary.setExit("north", herbGarden);

        magistratesHouse.setExit("east", square);
        magistratesHouse.setExit("north", church);

        gallows.setExit("north", mayor);

        well.setExit("north", forest);
        well.setExit("east", square);

        forest.setExit("east", witchHut);
        forest.setExit("south", well);

        witchHut.setExit("west", forest);

        createItems(church, graveyard, witchHut, blacksmith, churchCellar, herbGarden, meetingHouse, oldCemetery, magistratesHouse);


        currentRoom = square;
        System.out.println(currentRoom);
    }

    private void createItems(Room church, Room graveyard, Room witchhut, Room blacksmith, Room churchCellar,
                             Room herbGarden, Room meetingHouse, Room oldCemetery, Room magistratesHouse) {
        Item letter = new Item("We saw the witch. She shouted these weird curse words to us. She should burn in hell. " +
                "I left something on the graveyard which should help.", "Letter from a witness");

        Item potion = new Item("Restores some health", "potion", 3);

        Item dagger = new Item("A silver dagger, resting on the table", "dagger", 4);

        Item torch = new Item("An old flickering torch mounted on the wall. It casts earie shadows.", "Torch", 2);

        Item spellbook = new Item("A dusty leather-bound book titled 'Signs of Witchcraft. It looks recently read", "Book", 4, true);

        Item poppet = new Item("A crude cloth doll stuffed with herbs and pierced with several pins. " +
                "A lock of human hair is tied around its neck—someone's likeness, no doubt meant for malicious spellwork", "poppet", 2);

        Item cauldron = new Item("A large iron pot blackened with fire, still warm to the touch.", "cauldron", 10);

        Item silverCross = new Item("A small silver crucifix on a chain. It's said to ward off evil spirits " +
                "and reveal witches by burning their skin on contact.", "silver cross", 3);

        Item mandrake = new Item("A gnarled root shaped eerily like a human figure.", "mandrake", 2);

        Item blackCandle = new Item("A candle made from tallow and blackened wax, half-burned. Used in rituals to summon dark forces. " +
                "Its flame burns with an unnatural blue hue.", "black candle", 2, true);

        Item prickingNeedle = new Item("A long, sharp bodkin used by witch prickers to search for the Devil's mark.", "needle", 1);

        Item herbsBundle = new Item("A dried bundle of suspicious plants: belladonna, henbane, and wolfsbane.", "herbs", 1);

        Item sword = new Item("A heavy, pitted longsword, its once-sharp blade now dulled and speckled with rust. " +
                "The leather-wrapped hilt is stained dark – " +
                "whether from old blood or something worse, you can't tell. It feels heavier than it should",
                "Longsword", 8, false);

        // Add items to Rooms
        church.addItemToRoom(silverCross);
        church.addItemToRoom(potion);
        graveyard.addItemToRoom(blackCandle);
        graveyard.addItemToRoom(prickingNeedle);
        churchCellar.addItemToRoom(spellbook);
        churchCellar.addItemToRoom(sword);
        witchhut.addItemToRoom(cauldron);
        oldCemetery.addItemToRoom(poppet);
        herbGarden.addItemToRoom(mandrake);
        herbGarden.addItemToRoom(herbsBundle);
        magistratesHouse.addItemToRoom(letter);
        blacksmith.addItemToRoom(dagger);
        meetingHouse.addItemToRoom(torch);
    }

    private void createNPCs(Room church, Room herbGarden, Room meetingHouse, Room mayor, Room square, Room tavern) {
        NPC priest = new NPC("Father Elias", "A gaunt man in his sixties, always clutching a worn silver crucifix that catches" +
                "the light wrong.");

        NPC herbalist = new NPC("Mistress Agatha", "An old woman with wild gray hair barely tamed by a linen coif," +
                "fingers painted green from roots and berries. She seems to know a lot about plants.");

        NPC tavernKeeper = new NPC("Old Tom Fletcher", "Fat, red nosed, always wiping tankards with a rag. The tavern is the only " +
                "warm place in town.");

        NPC girl = new NPC("Little Bess", "A child of about ten, dressed in rags, always lurking in shadows with wide unblinking eyes. " +
                "Carries a rag doll that looks... wrong somehow.");

        NPC captain = new NPC("Captain Nathaniel Thorne - the witch hunter", "Tall broad shouldered man, " +
                "dressed in a long black coat and wide brimmed hat.");

        NPC goodwife = new NPC("Goodwife Mercy Hale", "Young woman, mid twenties, once the prettiest in village. " +
                "Now bruised, hair matted, dress torn. Still has this spark in her eyes that makes people nervous.");

        NPC villageMayor = new NPC("Mayor Josiah Whitcomb", "A portly man in late forties, dressed finer than anyone else. " +
                "He always talks about 'purging evil'.");


        church.addNpcToRoom(priest);
        herbGarden.addNpcToRoom(herbalist);
        square.addNpcToRoom(girl);
        square.addNpcToRoom(goodwife);
        tavern.addNpcToRoom(tavernKeeper);
        meetingHouse.addNpcToRoom(captain);
        mayor.addNpcToRoom(villageMayor);
    }

    public void playGame() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thanx for playing! Until the next adventure...");
    }

    public void printWelcome() {
        System.out.println("Welcome to Eldermoon Village.");
        System.out.println("A witchhunt is taking place. A secret is being concealed. Survive the night and discover Eldermoon's secrets.");
        System.out.println("Hunt for the real witch...or become the hunted.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    public boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case LOOK -> lookInRoom(command);
            case TAKE -> takeItem(command);
            case INVESTIGATE -> investigateItem(command);
            case QUIT -> wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    public void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the village.");
        System.out.println();
        System.out.println("Your command words are: ");
        parser.showCommands();
    }

    /**
     * Attempts to move the player to an adjacent room in the specified direction.
     * If no direction is provided in the command, prompts the user to specify where to go.
     * If there is no exit in the given direction, notifies the user that the exit does not exist.
     * Upon successful movement, updates the current room and displays its description.
     *
     * @param command the command containing the direction to move in as the second word
     */
    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no exit!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Examines items in the current room. If a specific item name is provided as a second word,
     * displays the description of that item. If no second word is provided, displays a list of
     * all items present in the room.
     *
     * @param command the command containing an optional item name to examine
     */
    public void lookInRoom(Command command) {
        if (command.hasSecondWord()) {
            String itemName = command.getSecondWord();
            Item item = currentRoom.getItem(itemName);
            if (item != null) {
                System.out.println(item.getDescription());
            } else {
                System.out.println("There is no " + itemName + " in this room.");
            }
        } else {
            // Just look- redescribes the entire room (now includes items automatically)
            System.out.println(currentRoom.getItemInRoomString());
        }
    }

    /**
     * Investigates an item in the player's inventory and displays its description.
     * If no item name is provided or the item is not found in the inventory,
     * an appropriate error message is displayed.
     *
     * @param command the command containing the name of the item to investigate
     */
    public void investigateItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Investigate what?");
            return;
        }
        String itemName = command.getSecondWord().trim();
        Item item = player.getItem(itemName);
        if (item != null) {
            System.out.println(item.getDescription());
        } else {
            System.out.println("You don't have " + itemName + " in your inventory.");
        }
    }

    /**
     * Attempts to take an item from the current room and add it to the player's inventory.
     * Validates that the command has a second word specifying the item name, retrieves the item
     * from the current room, and attempts to add it to the player's inventory. Displays
     * appropriate success or failure messages based on the outcome.
     *
     * @param command the command containing the name of the item to take
     */
    public void takeItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord().trim();
        Item itemInRoom = currentRoom.getItem(itemName);
        boolean success = player.takeItem(itemName, currentRoom);
        if (success) {
            System.out.println("You picked: " + player.getItem(itemName));
        } else {
            System.out.println("You couldn't pick " + itemInRoom);
        }
    }

    /**
     * Initiates a conversation with a character (NPC) in the current room.
     * If no character name is specified in the command, prompts the user to specify whom to talk to.
     *
     * @param command the command containing the name of the character to talk to
     */
    public void talkToCharacter(Command command) {String character = command.getSecondWord();
        if (!command.hasSecondWord()) {
            System.out.println("Talk to whom?");
        }
        String characterName = command.getSecondWord();
    }

    /**
     * The command to quit the game
     * @param command Command word 'quit'
     * @return Quit the game
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }

    // Getter method for testing
    public Room getCurrentRoom() {
        return currentRoom;
    }
}