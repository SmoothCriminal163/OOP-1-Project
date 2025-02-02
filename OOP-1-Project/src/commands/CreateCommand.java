package commands;


import app.AppContext;
import shapes.Shape;
import shapes.ShapeFactory;

/**
 * create rectangle x y width height color
 * create circle cx cy radius color
 */
public class CreateCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getUsage() {
        return "create <shapeType> [params] - e.g.\n" +
                "  create rectangle x y width height color\n" +
                "  create circle cx cy r color";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }
        if (args.length < 1) {
            System.out.println("Not enough arguments. " + getUsage());
            return;
        }
        String shapeType = args[0].toLowerCase();

        try {
            switch (shapeType) {
                case "rectangle":
                    // "rectangle x y width height color"
                    if (args.length < 6) {
                        System.out.println("Usage: create rectangle x y width height color");
                        return;
                    }
                    double rx = Double.parseDouble(args[1]);
                    double ry = Double.parseDouble(args[2]);
                    double rw = Double.parseDouble(args[3]);
                    double rh = Double.parseDouble(args[4]);
                    String rColor = args[5];
                    Shape rect = ShapeFactory.createRectangle(rx, ry, rw, rh, rColor);
                    context.getRepository().addShape(rect);
                    System.out.println("Successfully created rectangle (" + context.getRepository().size() + ")");
                    break;

                case "circle":
                    // "circle cx cy r color"
                    if (args.length < 5) {
                        System.out.println("Usage: create circle cx cy r color");
                        return;
                    }
                    double cx = Double.parseDouble(args[1]);
                    double cy = Double.parseDouble(args[2]);
                    double r = Double.parseDouble(args[3]);
                    String cColor = args[4];
                    Shape circle = ShapeFactory.createCircle(cx, cy, r, cColor);
                    context.getRepository().addShape(circle);
                    System.out.println("Successfully created circle (" + context.getRepository().size() + ")");
                    break;

                default:
                    System.out.println("Unsupported shape type: " + shapeType);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric argument: " + e.getMessage());
        }
    }
}
