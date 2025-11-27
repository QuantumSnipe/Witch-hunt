public enum CommandWord {

	GO("go"), QUIT("quit"), HELP("help"), 
	TAKE("take"), ASK("ask"), LOOK("look"), 
	DROP("drop"), INVENTORY("inventory"), UNLOCK("unlock"), 
	TALK("talk"), EXAMINE("examine"), UNKNOWN("?");

	private String commandString;

    CommandWord(String commandString) {
		this.commandString = commandString;
    }

	public String toString() {
		return commandString;
	}
}
