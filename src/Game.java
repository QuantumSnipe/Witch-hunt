
import java.util.ArrayList;


public class Game {
    private final Parser parser;
    private final Player player;
    private Room currentRoom;


    private ArrayList<Room> visitedRooms;

    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player();
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
        Room gallows = new Room("Gallows Hill", "A lone tree on a barren rise. Nooses dangle like wind chimes.");
        Room blacksmith = new Room("Blacksmith's Forge", "Tools hang in perfect rows-except one hammer, crusted with dried blood.");
        Room mayor = new Room("Mayor Hawthorne's Manor", "Polished oak and velvet lies. Portraits from past mayors hang around. A small " +
                              "fire burns red and blue.");
        Room bakery = new Room("Marta's Bakery", "The air is sweet with cinnamon.");
        Room well = new Room("Village Well", "A stone ring in the square's corner.");
        Room forest = new Room("The Dark Woods", "The trees stand tall and too close together, " +
                               "their roots clutching bones of old paths.");
        Room witchHut = new Room("Witch's Hut (Forest Edge)", "A crooked cottage of bones and thatch. " +
                                 "Herbs dry upside-down. A cauldron bubbles with something...");
        // initialize exits
        square.setExit("east", tavern);
        square.setExit("north-east", church);
        square.setExit("south-west", blacksmith);
        square.setExit("west", well);
        square.setExit("north-west", forest);

        church.setExit("east", graveyard);
        church.setExit("south-west", square);
        church.setExit("north-west", forest);
        graveyard.setExit("west", church);

        blacksmith.setExit("east", mayor);
        blacksmith.setExit("north", tavern);
        blacksmith.setExit("south", gallows);

        mayor.setExit("west", blacksmith);
        mayor.setExit("north-east", tavern);
        mayor.setExit("south-east", gallows);

        tavern.setExit("west", square);
        tavern.setExit("south-east", bakery);

        gallows.setExit("north", mayor);

        well.setExit("north", forest);
        well.setExit("east", square);

        forest.setExit("east", witchHut);
        forest.setExit("south", well);

        Item letter = new Item("We saw the witch. She shouted these weird curse words to us. She should burn in hell. I left something on the graveyard which should help.", "Letter from a witness");
        Item potion = new Item("Restores some health", "Red Potion");
        Item dagger = new Item("A silver dagger, resting on the table", "Silver Dagger");



        currentRoom = square;
        System.out.println(square);
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