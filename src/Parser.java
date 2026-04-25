import java.util.Scanner;

/**
 * Handles parsing of user input commands in the game.
 * The parser reads input from the console, tokenizes it into command words and arguments,
 * and converts it into Command objects that can be processed by the game engine.
 *
 * The parser recognizes the first word of user input as the command word and treats
 * all remaining words as a single argument string. Input is validated against a set
 * of known commands managed by the CommandWords class.
 */
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
