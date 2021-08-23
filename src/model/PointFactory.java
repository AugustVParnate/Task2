package model;

import java.util.List;

public class PointFactory implements FigureFactory {

    public Point newInstance(double x, double y) {
        return new Point(x, y);
    }
    @Override
    public Point newInstance(List<Point> pointList) {
        if (pointList.size() == FigureType.POINT.getNumberOfPoints()) {
            return new Point(pointList.get(0));
        }
        return null;
    }
}
