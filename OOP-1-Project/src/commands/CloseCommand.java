package commands;

import app.AppContext;

public class CloseCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "close";
    }

    @Override
    public String getUsage() {
        return "close - Closes the currently opened file.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }
        String fileName = context.getCurrentFile();
        context.closeFile();
        System.out.println("Successfully closed " + fileName);
    }
}

