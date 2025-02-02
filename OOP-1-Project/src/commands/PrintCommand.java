package commands;

import app.AppContext;
import shapes.Shape;

import java.util.List;

public class PrintCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "print";
    }

    @Override
    public String getUsage() {
        return "print - Prints all loaded shapes with their index.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }
        List<Shape> shapes = context.getRepository().getAllShapes();
        if (shapes.isEmpty()) {
            System.out.println("No figures to print.");
            return;
        }
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println((i+1) + ". " + shapes.get(i).getInfo());
        }
    }
}
