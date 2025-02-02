package commands;


import app.AppContext;
import svg.SvgFileHandler;

public class SaveAsCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "saveas";
    }

    @Override
    public String getUsage() {
        return "saveas <filePath> - Saves the current file under a new path.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }
        if (args.length < 1) {
            System.out.println("No target file specified. " + getUsage());
            return;
        }
        String filePath = args[0];

        try {
            SvgFileHandler.saveToFile(filePath, context.getRepository());
            context.setCurrentFile(filePath);
            System.out.println("Successfully saved " + filePath);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
