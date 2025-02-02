package storage;

import shapes.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of shapes (in memory).
 */
public class SvgRepository {

    private final List<Shape> shapes;

    public SvgRepository() {
        this.shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * @param index 0-based index
     * @return the Shape at index or null if out of range
     */
    public Shape getShape(int index) {
        if (index < 0 || index >= shapes.size()) {
            return null;
        }
        return shapes.get(index);
    }

    /**
     * Removes the shape at 0-based index if valid.
     * @return true if removed, false if out of range
     */
    public boolean removeShape(int index) {
        if (index < 0 || index >= shapes.size()) {
            return false;
        }
        shapes.remove(index);
        return true;
    }

    /**
     * @return all shapes in the repository
     */
    public List<Shape> getAllShapes() {
        return shapes;
    }

    /**
     * @return the number of shapes
     */
    public int size() {
        return shapes.size();
    }

    /**
     * Translates all shapes by (dx, dy).
     */
    public void translateAll(double dx, double dy) {
        for (Shape shape : shapes) {
            shape.translate(dx, dy);
        }
    }

    /**
     * Clears all shapes from the repository.
     */
    public void clear() {
        shapes.clear();
    }
}
