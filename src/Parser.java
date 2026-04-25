import java.util.Scanner;

public class Parser {
    private final CommandWords commands;
    private final Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        try (Scanner tokenizer = new Scanner(inputLine)) {
            if (tokenizer.hasNext()) {
                word1 = tokenizer.next();
                StringBuilder rest = new StringBuilder();
                while (tokenizer.hasNext()) {
                    if (rest.length() > 0) {
                        rest.append(" ");
                    }
                    rest.append(tokenizer.next());
                }
                if (rest.length() > 0) {
                    word2 = rest.toString();
                }
            }
        }
        return new Command(commands.getCommandWord(word1), word2);
    }

    public void showCommands() {
        commands.showAll();
    }
}
