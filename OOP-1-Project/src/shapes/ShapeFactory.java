package shapes;

/**
 * Provides static methods to create shapes from user parameters.
 */
public class ShapeFactory {

    /**
     * Creates a RectangleShape given typical parameters: x, y, width, height, color.
     */
    public static Shape createRectangle(double x, double y,
                                        double width, double height,
                                        String color) {
        return new RectangleShape(x, y, width, height, color);
    }

    /**
     * Creates a CircleShape given typical parameters: cx, cy, radius, color.
     */
    public static Shape createCircle(double cx, double cy,
                                     double radius,
                                     String color) {
        return new CircleShape(cx, cy, radius, color);
    }

    public static Shape createLine(double x1, double y1,
                                   double x2, double y2,
                                   String strokeColor,
                                   double strokeWidth) {
        return new LineShape(x1, y1, x2, y2, strokeColor, strokeWidth);
    }
}

