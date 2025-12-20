public class Command {
	private final CommandWord commandWord;
	private final String secondWord;

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
}