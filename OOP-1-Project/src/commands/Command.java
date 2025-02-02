package commands;


import app.AppContext;

/**
 * Interface for any command that can be executed from CLI.
 */
public interface Command {

    /**
     * @return the command name (e.g., "open", "close", "print", etc.)
     */
    String getName();

    /**
     * Execute the command with the given user input and arguments.
     * @param input   the full line as typed by user
     * @param args    the tokens after the command name
     * @param context the global application context
     */
    void execute(String input, String[] args, AppContext context);

    /**
     * @return A short usage description or help text
     */
    default String getUsage() {
        return "No usage provided for this command.";
    }
}
