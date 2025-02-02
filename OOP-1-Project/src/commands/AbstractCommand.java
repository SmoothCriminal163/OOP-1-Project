package commands;


import app.AppContext;

/**
 * Optional convenience base class for commands.
 * Provides default usage text and common logic if needed.
 */
public abstract class AbstractCommand implements Command {
    @Override
    public String getUsage() {
        return "Usage info not specified.";
    }

    // We still must implement getName() and execute() in subclasses
    public abstract String getName();
    public abstract void execute(String input, String[] args, AppContext context);
}

