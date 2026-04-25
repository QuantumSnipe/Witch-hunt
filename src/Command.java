/**
 * Represents a command issued by the user in the game.
 * A command consists of a command word and an optional second word (argument).
 * The command word indicates the type of action to be performed, while the
 * second word provides additional information needed to execute the command.
 *
 * Commands can be checked for validity and completeness using the provided
 * utility methods. An unknown command is one where the command word is not
 * recognized by the system.
 */
public class Command {
	
	private CommandWord commandWord;
	private String secondWord;

	public Command(CommandWord commandWord, String argument) {
		this.commandWord = commandWord;
		this.secondWord = argument;
	}

	public CommandWord getCommandWord() {
		return commandWord;
	}

	public String getSecondWord() {
		return secondWord;
	}

	public boolean isUnknown() {
		return commandWord == CommandWord.UNKNOWN;
	}

	public boolean hasSecondWord() {
		return secondWord != null;
	}

    public void setCommandWord(CommandWord commandWord) {
        this.commandWord = commandWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }
}