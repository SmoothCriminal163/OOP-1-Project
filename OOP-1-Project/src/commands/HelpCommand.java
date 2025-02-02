package commands;

import app.AppContext;

public class HelpCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "help - Prints a list of supported commands.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        // You might create a separate help system that queries the usage from each command
        // For simplicity, let's just print a static message:
        System.out.println("The following commands are supported:");
        System.out.println("open <file>        - Opens an SVG file");
        System.out.println("close              - Closes the currently opened file");
        System.out.println("save               - Saves the current file");
        System.out.println("saveas <file>      - Saves the current file under a new path");
        System.out.println("print              - Prints all loaded shapes");
        System.out.println("create ...         - Creates a new shape (rectangle/circle)");
        System.out.println("erase <n>          - Erases shape #n (1-based index)");
        System.out.println("translate ...      - Translates shape(s)");
        System.out.println("within ...         - Lists shapes fully within a region");
        System.out.println("help               - Displays this help message");
        System.out.println("exit               - Exits the program");
    }
}
