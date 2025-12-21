
import java.util.ArrayList;


public class Game {
    private final Parser parser;
    private Room currentRoom;


    private final ArrayList<Room> visitedRooms;

    public Game() {
        createRooms();
        parser = new Parser();
        visitedRooms = new ArrayList<>();

    }

    private void createRooms() {
        Room square = new Room("Village Square", "Cobblestones slick with fog. Torches flicker.");
        Room church = new Room("St. Aldric's Church", "Cold stone arches with damp. Candles sputter before a crooked altar. " +
                               "The scent of incense struggles to hide the rot beneath the floorboards.");
        Room graveyard = new Room("Graveyard", "Iron gates lean inward, guarding rows of tilting headstones. " +
                                   "A raven watches from a cracked angel's shoulders.");
        Room tavern = new Room("The Black Goat Tavern", "Smoke, ale, and whispers. Eyes " +
                               "follow you from the shadows.");
        Room gallows = new Room("Gallows Hill", "You stand atop the barren hill where the village hangs its condemned. A weathered wooden scaffold looms overhead. Below it lies a patch of trampled earth stained dark with old blood. ");
        Room blacksmith = new Room("Blacksmith's Forge", "Tools hang in perfect rows-except one hammer, crusted with dried blood.");
        Room mayor = new Room("Mayor Hawthorne's Manor", "Polished oak and velvet lies. Portraits from past mayors hang around. A small " +
                              "fire burns red and blue.");
        Room well = new Room("Village Well", "A stone ring in the square's corner.");
        Room forest = new Room("The Dark Woods", "The trees stand tall and too close together, " +
                               "their roots clutching bones of old paths.");
        Room witchHut = new Room("Witch's Hut (Forest Edge)", "A crooked cottage of bones and thatch. " +
                                 "Herbs dry upside-down. A cauldron bubbles with something...");

        Room apothecary = new Room("Apothecary shop", "The air is thick with the smell of dried herbs.");
        Room meetingHouse = new Room("In the village meeting house", "The old court house. The single room is lit only by a dying fire.");
        Room churchCellar = new Room("in the damp cellar beneath the church", "The air is cold and stale. Chains hang from the walls, and a small wooden cage stands empty in the corner.");
        Room magistratesHouse = new Room("in the magistrate's house", "Dark wood paneling and heavy furniture speak of wealth and authority. A large desk holds papers and a quill still wet with ink. A locked door leads deeper in the house.");
        Room oldCemetery = new Room("in the old cemetery", "Moss-covered headstones lean at odd angles. A fresh grave has been dug recently-no marker yet.");
        Room herbGarden = new Room("in the herbalist's overgrown garden", "Weeds and strange plants choke the once-tidy rows. A small stone well stands in the center.");


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

        createItems(church, tavern, graveyard, witchHut, blacksmith, churchCellar, herbGarden, meetingHouse, oldCemetery, magistratesHouse);

  
        currentRoom = square;
        System.out.println(square);
    }

    private void createItems(Room church, Room tavern, Room graveyard, Room witchhut, Room blacksmith, Room churchCellar, Room herbGarden, Room meetingHouse, Room oldCemetery, Room magistratesHouse) {
        Item letter = new Item("We saw the witch. She shouted these weird curse words to us. She should burn in hell. I left something on the graveyard which should help.", "Letter from a witness");
        Item potion = new Item("Restores some health", "Red Potion");
        Item dagger = new Item("A silver dagger, resting on the table", "Silver Dagger");
        Item torch = new Item("An old flickering torch mounted on the wall. It casts earie shadows.", "Torch");
        Item spellbook = new Item("A dusty leather-bound book titled 'Signs of Witchcraft. It looks recently read", "Book");
        Item poppet = new Item("A crude cloth doll stuffed with herbs and pierced with several pins. A lock of human hair is tied around its neck—someone's likeness, no doubt meant for malicious spellwork", "poppet");
        Item cauldron = new Item("A large iron pot blackened with fire, still warm to the touch.", "cauldron");
        Item silverCross = new Item("A small silver crucifix on a chain. It's said to ward off evil spirits and reveal witches by burning their skin on contact.", "cross");
        Item mandrake = new Item("A gnarled root shaped eerily like a human figure.", "mandrake");
        Item blackCandle = new Item("A candle made from tallow and blackened wax, half-burned. Used in rituals to summon dark forces. Its flame burns with an unnatural blue hue.", "black candle");
        Item prickingNeedle = new Item("A long, sharp bodkin used by witch prickers to search for the Devil's mark.", "needle");
        Item herbsBundle = new Item("A dried bundle of suspicious plants: belladona, henbane, and wolfsbane.", "herbs");



        church.addItemToRoom(silverCross);
        church.addItemToRoom(potion);
        graveyard.addItemToRoom(blackCandle);
        churchCellar.addItemToRoom(spellbook);
        witchhut.addItemToRoom(cauldron);
        oldCemetery.addItemToRoom(poppet);
        herbGarden.addItemToRoom(mandrake);
        herbGarden.addItemToRoom(herbsBundle);
        magistratesHouse.addItemToRoom(prickingNeedle);
        blacksmith.addItemToRoom(dagger);
    }

    public void playGame() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("The pyre is cold. You survived... this time.");
    }

    public void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Eldermoon Village.");
        System.out.println("A witchhunt is taking place. You're not here to save anyone-you're here to survive the night.");
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
            case LOOK -> lookInRoom();
            case QUIT -> wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    public void printHelp() {
        System.out.println("You are lost. You alone. You wander");
        System.out.println("around at the village.");
        System.out.println();
        System.out.println("Your command words are: ");
        parser.showCommands();
    }

    /**
     * Try to go to one direction if there is an exit, enter
     * new room, or print an error message
     * @param command
     */
    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        if (command.getSecondWord().equals("back")) {
            Room lastRoom = null;

            if (!visitedRooms.isEmpty()) {
                lastRoom = visitedRooms.get(visitedRooms.size() - 1);
            }

            if (lastRoom == getCurrentRoom() || lastRoom == null) {
                System.out.println("You can't go back any further!");
            } else {
                setCurrentRoom(lastRoom);
                visitedRooms.remove(visitedRooms.size() - 1);
            }
        } else {
            String direction = command.getSecondWord();

            Room nextRoom = currentRoom.getExit(direction);

            if (nextRoom == null) {
                System.out.println("There is no door");
            } else {
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }

    public void lookInRoom() {

    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}