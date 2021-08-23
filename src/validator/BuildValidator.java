package validator;

import model.FigureType;

import javax.xml.validation.Validator;
import java.awt.*;
import java.util.regex.Pattern;

public class BuildValidator implements Validator {
    private static final String COORDINATES_LINE_REG_EX = "([-+]?[0-9]*\\.?[0-9]+\\s+){7}[-+]?[0-9]*\\.?[0-9]+\\s*";

    public boolean creatable(List<Point> points) {
        if (points.size() != FigureType.QUADRANGLE.getNumberOfPoints()) {
            return false;
        }

        for (int i = 0; i < FigureType.QUADRANGLE.getNumberOfPoints() - 1; i++) {
            MathVector mathVector1;
            MathVector mathVector2;
            if (i != FigureType.QUADRANGLE.getNumberOfPoints() - 2) {
                mathVector1 = new MathVector(points.get(i), points.get(i + 1));
                mathVector2 = new MathVector(points.get(i + 1), points.get(i + 2));
            } else {
                mathVector1 = new MathVector(points.get(FigureType.QUADRANGLE.getNumberOfPoints() - 1),
                        points.get(0));
                mathVector2 = new MathVector(points.get(0), points.get(1));
            }
            if (Double.valueOf(0).equals(mathVector1.scalarProduct(mathVector2))) {
                return false;
            }
        }

        return twoLinesSegmentsIntersect(points.get(0), points.get(1), points.get(2), points.get(3))
                && twoLinesSegmentsIntersect(points.get(0), points.get(3), points.get(1), points.get(2));
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile(COORDINATES_LINE_REG_EX);
    }

    private Double getOrientedAreaOfAPyramid(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY())
                - (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    private boolean theLineSegmentsLieOnOneStraightLine(Double a, Double b, Double c, Double d) {
        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }
        if (c > d) {
            double temp = c;
            c = d;
            d = temp;
        }
        return !(Math.max(a, c) <= Math.min(b, d));
    }

    private boolean twoLinesSegmentsIntersect(Point a, Point b, Point c, Point d) {
        return theLineSegmentsLieOnOneStraightLine(a.getX(), b.getX(), c.getX(), d.getX())
                || theLineSegmentsLieOnOneStraightLine(a.getY(), b.getY(), c.getY(), d.getY())
                || !(getOrientedAreaOfAPyramid(a, b, c) * getOrientedAreaOfAPyramid(a, b, d) <= 0)
                || !(getOrientedAreaOfAPyramid(c, d, a) * getOrientedAreaOfAPyramid(c, d, b) <= 0);
    }
}
