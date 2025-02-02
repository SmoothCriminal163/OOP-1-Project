package commands;

import app.AppContext;

public class ExitCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getUsage() {
        return "exit - Exits the program.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
