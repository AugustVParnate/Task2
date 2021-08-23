package model;

import java.util.List;
import java.util.Objects;

public class Pyramid {
    public Pyramid(List<Point> points) {
        super(points);
    }

    @Override
    public String toString() {
        return "Pyramid`{" +
                "points=" + getPoints() +
                '}';
    }

    private String getPoints() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pyramid that = (Pyramid) o;
        return Objects.equals(getPoints(), that.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints());
    }
}

