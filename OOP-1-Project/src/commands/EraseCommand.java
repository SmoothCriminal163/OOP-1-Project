package commands;

import app.AppContext;
import shapes.Shape;

public class EraseCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "erase";
    }

    @Override
    public String getUsage() {
        return "erase <index> - Removes shape at the given 1-based index.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }
        if (args.length < 1) {
            System.out.println(getUsage());
            return;
        }

        try {
            int index = Integer.parseInt(args[0]) - 1; // convert from 1-based to 0-based
            Shape shape = context.getRepository().getShape(index);
            if (shape == null) {
                System.out.println("There is no figure number " + args[0] + "!");
                return;
            }
            String shapeTypeName = shape.getInfo().split(" ")[0]; // e.g. "rectangle"
            context.getRepository().removeShape(index);
            System.out.println("Erased a " + shapeTypeName + " (" + (index+1) + ")");
        } catch (NumberFormatException e) {
            System.out.println("Invalid index: " + args[0]);
        }
    }
}
