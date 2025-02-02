package shapes;

import java.util.Locale;

public class RectangleShape implements Shape {

    private double x;
    private double y;
    private double width;
    private double height;
    private String fillColor;

    public RectangleShape(double x, double y, double width, double height, String fillColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
    }

    @Override
    public String getInfo() {
        // e.g. "rectangle 5 5 10 20 green"
        return String.format("rectangle %.2f %.2f %.2f %.2f %s",
                x, y, width, height, fillColor);
    }

    @Override
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean isWithinRectangle(double rx, double ry, double rw, double rh) {
        // Must be completely within [rx, rx + rw] × [ry, ry + rh]
        return x >= rx && y >= ry &&
                (x + width) <= (rx + rw) &&
                (y + height) <= (ry + rh);
    }

    @Override
    public boolean isWithinCircle(double cx, double cy, double r) {
        // All 4 corners must be within distance r of (cx, cy)
        double x2 = x + width;
        double y2 = y + height;
        return checkCornerInCircle(x,  y,  cx, cy, r)
                && checkCornerInCircle(x2, y,  cx, cy, r)
                && checkCornerInCircle(x,  y2, cx, cy, r)
                && checkCornerInCircle(x2, y2, cx, cy, r);
    }

    private boolean checkCornerInCircle(double cornerX, double cornerY,
                                        double centerX, double centerY, double radius) {
        double distSq = Math.pow(cornerX - centerX, 2) + Math.pow(cornerY - centerY, 2);
        return distSq <= Math.pow(radius, 2);
    }

    @Override
    public String toSvg() {
        // Force US locale so decimal values use a dot instead of comma
        return String.format(Locale.US,
                "<rect x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" height=\"%.2f\" fill=\"%s\"/>",
                x, y, width, height, fillColor
        );
    }
}
