package commands;


import app.AppContext;
import svg.SvgFileHandler;

public class SaveCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getUsage() {
        return "save - Saves changes to the currently opened file.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }
        String filePath = context.getCurrentFile();
        try {
            SvgFileHandler.saveToFile(filePath, context.getRepository());
            System.out.println("Successfully saved " + filePath);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}

