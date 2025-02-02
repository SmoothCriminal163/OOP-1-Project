package commands;


import app.AppContext;
import svg.SvgFileHandler;

public class OpenCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "open";
    }

    @Override
    public String getUsage() {
        return "open <filePath> - Opens the specified SVG file.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (args.length < 1) {
            System.out.println("No file specified. " + getUsage());
            return;
        }

        String filePath = args[0];
        try {
            // Clear existing shapes
            context.getRepository().clear();

            SvgFileHandler.loadFromFile(filePath, context.getRepository());
            context.setCurrentFile(filePath);
            context.setFileOpened(true);

            System.out.println("Successfully opened " + filePath);
        } catch (Exception e) {
            System.out.println("Error opening file: " + e.getMessage());
            // If there's a critical parse error, optionally: System.exit(1);
        }
    }
}
