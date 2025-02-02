package cli;

import app.AppContext;
import commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Processes user input by matching the first token to a Command
 * and then executing that command.
 */
public class CommandProcessor {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProcessor() {
        register(new OpenCommand());
        register(new CloseCommand());
        register(new SaveCommand());
        register(new SaveAsCommand());
        register(new PrintCommand());
        register(new CreateCommand());
        register(new EraseCommand());
        register(new TranslateCommand());
        register(new WithinCommand());
        register(new HelpCommand());
        register(new ExitCommand());
    }

    private void register(Command cmd) {
        commands.put(cmd.getName().toLowerCase(), cmd);
    }

    /**
     * Parses the user input line, finds the appropriate command, and executes it.
     */
    public void processCommand(String inputLine, AppContext context) {
        if (inputLine == null || inputLine.trim().isEmpty()) {
            return;
        }

        String[] tokens = inputLine.trim().split("\\s+");
        String cmdName = tokens[0].toLowerCase();
        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, tokens.length - 1);

        Command cmd = commands.get(cmdName);
        if (cmd == null) {
            System.out.println("Unknown command. Type 'help' for available commands.");
            return;
        }

        cmd.execute(inputLine, args, context);
    }
}
