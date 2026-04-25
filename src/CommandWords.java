import java.util.HashMap;

/**
 * Manages and validates available command words in the game.
 * This class maintains a collection of valid commands and provides methods to verify
 * if a given string represents a valid command and to retrieve the corresponding
 * CommandWord enumeration value.
 */
public class CommandWords {

	private final HashMap<String, CommandWord> validCommands;


	public CommandWords() {
		validCommands = new HashMap<>();
		for (CommandWord command : CommandWord.values()) {
			if (command != CommandWord.UNKNOWN) {
				validCommands.put(command.toString(), command);
			}
		}
	}

	public CommandWord getCommandWord(String commandWord) {
		CommandWord command = validCommands.get(commandWord);
		if (command != null) {
			return command;
		} else {
			return CommandWord.UNKNOWN;
		}
	}

	public boolean isCommand(String aString) {
		return validCommands.containsKey(aString);
	}

	public void showAll() {
		for (String command : validCommands.keySet()) {
			System.out.print(command + " ");
		}
		System.out.println();
	}
}
