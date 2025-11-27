public class Command {
	private final CommandWord commndword;
	private final String argument;

	public Command(CommandWord commandWord, String argument) {
		this.commndword = commandWord;
		this.argument = argument;
	}

	public CommandWord getCommandWord() {
		return commndword;
	}

	public String getArgument() {
		return argument;
	}

	public boolean isUnknown() {
		return commndword == CommandWord.UNKNOWN;
	}

	public boolean hasArgument() {
		return argument != null;
	}
}