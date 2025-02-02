package commands;


import app.AppContext;
import shapes.Shape;

import java.util.List;

public class WithinCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "within";
    }

    @Override
    public String getUsage() {
        return "within rectangle x y width height\n" +
                "within circle cx cy radius\n" +
                "Lists all shapes fully contained in the specified region.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }
        if (args.length < 1) {
            System.out.println("Please specify a region type (rectangle/circle). " + getUsage());
            return;
        }

        String regionType = args[0].toLowerCase();
        List<Shape> allShapes = context.getRepository().getAllShapes();
        boolean foundAny = false;

        switch (regionType) {
            case "rectangle":
                if (args.length < 5) {
                    System.out.println("Usage: within rectangle x y width height");
                    return;
                }
                try {
                    double rx = Double.parseDouble(args[1]);
                    double ry = Double.parseDouble(args[2]);
                    double rw = Double.parseDouble(args[3]);
                    double rh = Double.parseDouble(args[4]);

                    for (int i = 0; i < allShapes.size(); i++) {
                        Shape s = allShapes.get(i);
                        if (s.isWithinRectangle(rx, ry, rw, rh)) {
                            System.out.println((i+1) + ". " + s.getInfo());
                            foundAny = true;
                        }
                    }
                    if (!foundAny) {
                        System.out.printf("No figures are located within rectangle %.2f %.2f %.2f %.2f\n",
                                rx, ry, rw, rh);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rectangle parameters: " + e.getMessage());
                }
                break;

            case "circle":
                if (args.length < 4) {
                    System.out.println("Usage: within circle cx cy radius");
                    return;
                }
                try {
                    double cx = Double.parseDouble(args[1]);
                    double cy = Double.parseDouble(args[2]);
                    double r = Double.parseDouble(args[3]);

                    for (int i = 0; i < allShapes.size(); i++) {
                        Shape s = allShapes.get(i);
                        if (s.isWithinCircle(cx, cy, r)) {
                            System.out.println((i+1) + ". " + s.getInfo());
                            foundAny = true;
                        }
                    }
                    if (!foundAny) {
                        System.out.printf("No figures are located within circle %.2f %.2f %.2f\n",
                                cx, cy, r);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid circle parameters: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Unknown region type: " + regionType);
                break;
        }
    }
}
