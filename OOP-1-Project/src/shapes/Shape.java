package shapes;

/**
 * Common interface for all supported shapes (rectangle, circle, etc.).
 */
public interface Shape {

    /**
     * @return A short human-readable description for use in commands like `print`.
     *         E.g. "rectangle 5 5 10 20 green"
     */
    String getInfo();

    /**
     * Moves the shape by the specified horizontal (dx) and vertical (dy) offsets.
     */
    void translate(double dx, double dy);

    /**
     * Checks if this shape is fully contained within the specified rectangle.
     * @param rx The X coordinate of the rectangle's top-left corner
     * @param ry The Y coordinate of the rectangle's top-left corner
     * @param rw The width of the rectangle
     * @param rh The height of the rectangle
     * @return True if entirely within the rectangle, false otherwise
     */
    boolean isWithinRectangle(double rx, double ry, double rw, double rh);

    /**
     * Checks if this shape is fully contained within the specified circle.
     * @param cx Circle center X
     * @param cy Circle center Y
     * @param r  Circle radius
     * @return True if entirely within the circle, false otherwise
     */
    boolean isWithinCircle(double cx, double cy, double r);

    /**
     * @return The SVG element string (e.g. <rect ... /> or <circle ... />).
     */
    String toSvg();
}
