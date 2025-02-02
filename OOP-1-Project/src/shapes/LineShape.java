package shapes;

import java.util.Locale;

public class LineShape implements Shape {

    // Координати на двата края на линията
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    // Цвят и дебелина
    private String strokeColor;
    private double strokeWidth;

    public LineShape(double x1, double y1, double x2, double y2,
                     String strokeColor, double strokeWidth) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    @Override
    public String getInfo() {
        // Примерен стринг: "line 10 10 50 60 red 2"
        return String.format("line %.2f %.2f %.2f %.2f %s %.2f",
                x1, y1, x2, y2, strokeColor, strokeWidth);
    }

    @Override
    public void translate(double dx, double dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    @Override
    public boolean isWithinRectangle(double rx, double ry, double rw, double rh) {
        // Проверяваме дали и двата края на линията са в правоъгълника
        // x >= rx && x <= rx + rw
        // y >= ry && y <= ry + rh

        boolean firstPointInside = (x1 >= rx && x1 <= rx + rw &&
                y1 >= ry && y1 <= ry + rh);
        boolean secondPointInside = (x2 >= rx && x2 <= rx + rw &&
                y2 >= ry && y2 <= ry + rh);

        return firstPointInside && secondPointInside;
    }

    @Override
    public boolean isWithinCircle(double cx, double cy, double r) {
        // Проверяваме дали и двата края на линията са в кръга
        // (x - cx)^2 + (y - cy)^2 <= r^2
        return isPointInCircle(x1, y1, cx, cy, r)
                && isPointInCircle(x2, y2, cx, cy, r);
    }

    private boolean isPointInCircle(double px, double py,
                                    double cx, double cy, double r) {
        double distSq = Math.pow(px - cx, 2) + Math.pow(py - cy, 2);
        double rSq = Math.pow(r, 2);
        return distSq <= rSq;
    }

    @Override
    public String toSvg() {
        // Генерираме <line> елемент.
        // Използваме Locale.US, за да сме сигурни, че дробната част е с точка вместо запетая.
        return String.format(Locale.US,
                "<line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"%.2f\"/>",
                x1, y1, x2, y2, strokeColor, strokeWidth
        );
    }
}