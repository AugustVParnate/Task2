package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Point {
    public static final FigureType FIGURE_TYPE = FigureType.POINT;

    private final int id;
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.id = 0;
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.id = 0;
        this.x = point.getX();
        this.y = point.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int getNumberOfPoints() {
        return FIGURE_TYPE.getNumberOfPoints();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ArrayList<Point> getPoints() {
        return new ArrayList<>(Collections.singletonList(new Point(this)));
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

