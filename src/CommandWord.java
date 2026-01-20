public enum CommandWord {

	GO("go"), QUIT("quit"), HELP("help"), 
	TAKE("take"), ADD("add"), ASK("ask"), LOOK("look"),
	DROP("drop"), INVENTORY("inventory"), UNLOCK("unlock"), 
	TALK("talk"), EXAMINE("examine"), UNKNOWN("?");

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
