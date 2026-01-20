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