package shapes;

import java.util.Locale;

public class CircleShape implements Shape {

    private double cx;
    private double cy;
    private double r;
    private String fillColor;

    public CircleShape(double cx, double cy, double r, String fillColor) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.fillColor = fillColor;
    }

    @Override
    public String getInfo() {
        // e.g. "circle 5 5 10 blue"
        return String.format("circle %.2f %.2f %.2f %s",
                cx, cy, r, fillColor);
    }

    @Override
    public void translate(double dx, double dy) {
        cx += dx;
        cy += dy;
    }

    @Override
    public boolean isWithinRectangle(double rx, double ry, double rw, double rh) {
        // The circle must fit entirely within the rectangle:
        // (cx - r) >= rx, (cy - r) >= ry
        // (cx + r) <= (rx + rw), (cy + r) <= (ry + rh)
        if ((cx - r) < rx) return false;
        if ((cy - r) < ry) return false;
        if ((cx + r) > (rx + rw)) return false;
        if ((cy + r) > (ry + rh)) return false;
        return true;
    }

    @Override
    public boolean isWithinCircle(double centerX, double centerY, double bigR) {
        // Distance between centers plus this circle's radius <= bigR
        double distCentersSq = Math.pow(cx - centerX, 2) + Math.pow(cy - centerY, 2);
        double bigRSq = Math.pow(bigR, 2);
        // (sqrt(distCentersSq) + r)^2 <= bigRSq might be used,
        // but to avoid floating inaccuracies, compare squares carefully:
        // We want: sqrt(distCentersSq) + r <= bigR
        // => distCentersSq + 2*r*sqrt(distCentersSq) + r^2 <= bigR^2
        // For simplicity, we can do the direct approach:
        double distCenters = Math.sqrt(distCentersSq);
        return distCenters + r <= bigR;
    }

    @Override
    public String toSvg() {
        return String.format(Locale.US,
                "<circle cx=\"%.2f\" cy=\"%.2f\" r=\"%.2f\" fill=\"%s\"/>",
                cx, cy, r, fillColor
        );
    }
}
