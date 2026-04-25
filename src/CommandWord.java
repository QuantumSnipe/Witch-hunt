/**
 * Defines all valid command words that can be used in the game.
 * Each enum constant represents a specific action or command that players can execute,
 * such as movement, interaction with objects, or system operations. The UNKNOWN constant
 * represents any unrecognized command input.
 */
public enum CommandWord {

	GO("go"), QUIT("quit"), HELP("help"), 
	TAKE("take"), ADD("add"), ASK("ask"), LOOK("look"),
	DROP("drop"), INVENTORY("inventory"), UNLOCK("unlock"), 
	TALK("talk"), EXAMINE("examine"), INVESTIGATE("investigate"), UNKNOWN("?");

	private final String commandString;

    CommandWord(String commandString) {
		this.commandString = commandString;
    }

	public String getCommandString() {
		return commandString;
	}

	public String toString() {
		return commandString;
	}
}
