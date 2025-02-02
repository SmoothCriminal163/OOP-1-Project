package commands;

import app.AppContext;
import shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class TranslateCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "translate";
    }

    @Override
    public String getUsage() {
        return "translate [<indexes>] vertical=<dy> horizontal=<dx>\n" +
                " - <indexes> can be one or more numbers separated by commas, e.g. 1,3,5\n" +
                " - If <indexes> is omitted or invalid, all shapes are translated.";
    }

    @Override
    public void execute(String input, String[] args, AppContext context) {
        if (!context.isFileOpened()) {
            System.out.println("No file is currently opened.");
            return;
        }

        // We'll collect possibly multiple indexes in a list
        List<Integer> indexes = new ArrayList<>();

        // Attempt to parse the first argument (if any) as one or more indexes
        // Example: "translate 1,3,4 vertical=10 horizontal=50"
        // In this example, "1,3,4" is the first arg -> we try to split by comma
        int startArgIndex = 0;
        if (args.length > 0) {
            boolean allNumeric = true;
            String[] indexStrings = args[0].split(",");
            for (String idxStr : indexStrings) {
                try {
                    int parsed = Integer.parseInt(idxStr) - 1;  // shift to 0-based
                    indexes.add(parsed);
                } catch (NumberFormatException ex) {
                    // If any part isn't a number, we assume the user didn't provide indexes here
                    allNumeric = false;
                    break;
                }
            }
            // If all parts in the first argument were valid indexes, move startArgIndex
            // so the loop for vertical= / horizontal= can start with the next argument.
            if (allNumeric) {
                startArgIndex = 1;
            } else {
                // indexes list is not valid, so clear it
                indexes.clear();
            }
        }

        double dx = 0;
        double dy = 0;

        // Now parse the remaining arguments for vertical= / horizontal=
        for (int i = startArgIndex; i < args.length; i++) {
            String part = args[i];
            if (part.startsWith("vertical=")) {
                String val = part.substring("vertical=".length());
                dy = Double.parseDouble(val);
            } else if (part.startsWith("horizontal=")) {
                String val = part.substring("horizontal=".length());
                dx = Double.parseDouble(val);
            }
        }

        // If we have no valid indexes, translate all
        if (indexes.isEmpty()) {
            context.getRepository().translateAll(dx, dy);
            System.out.printf("Translated all figures by (dx=%.2f, dy=%.2f)%n", dx, dy);
        } else {
            // Otherwise, translate each shape at the specified indexes
            for (int index : indexes) {
                Shape shape = context.getRepository().getShape(index);
                if (shape == null) {
                    System.out.printf("There is no figure number %d!%n", index + 1);
                } else {
                    shape.translate(dx, dy);
                    System.out.printf("Translated figure %d by (dx=%.2f, dy=%.2f)%n", index + 1, dx, dy);
                }
            }
        }
    }
}
