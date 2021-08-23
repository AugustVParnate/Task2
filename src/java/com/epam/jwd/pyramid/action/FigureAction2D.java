package java.com.epam.jwd.pyramid.action;

import model.Figure;
import org.apache.log4j.LogManager;

import java.awt.*;
import java.util.logging.Logger;

public class FigureAction2D implements FigureAction {
    private static final Logger LOG = LogManager.getLogger(FigureAction2D.class);

    private final Figure figure;

    public FigureActions2D(Figure figure) {
        this.figure = figure;
    }

    public Double distanceBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2) + Math.pow((point2.getY() - point1.getY()), 2));
    }

    public Boolean isConvex() {
        boolean convex = true;
        List<MathVector> vectors = figure.getVectors();
        boolean negative = vectors.get(0).scalarProduct(vectors.get(1)) < 0;

        if (figure instanceof Point) {
            return true;
        }
        for (int i = 0; i < vectors.size(); i++) {
            boolean tmp;
            if (i != vectors.size() - 1) {
                tmp = (vectors.get(i).scalarProduct(vectors.get(i + 1)) < 0) != negative;
            } else {
                tmp = (vectors.get(i).scalarProduct(vectors.get(0)) < 0) != negative;
            }
            if (tmp) {
                convex = false;
                break;
            }
        }
        LOG.info(figure + ": " + convex);
        return convex;
    }

    @Override
    public double square() {
        double square = 0;

        if (figure instanceof Point) {
            return 0;
        }
        for (int i = 0; i < figure.getNumberOfPoints() - 1; i++) {
            square += figure.getPoints().get(i).getX() * figure.getPoints().get(i + 1).getY()
                    - figure.getPoints().get(i).getY() * figure.getPoints().get(i + 1).getX();
        }
        square += figure.getPoints().get(figure.getNumberOfPoints() - 1).getX() * figure.getPoints().get(0).getY()
                - figure.getPoints().get(figure.getNumberOfPoints() - 1).getY() * figure.getPoints().get(0).getX();
        LOG.info(figure + ": " + Math.abs(square / 2));
        return Math.abs(square / 2);
    }

    @Override
    public double perimeter() {
        double perimeter = 0;

        if (figure instanceof Point) {
            return 0;
        }
        for (int i = 0; i < figure.getNumberOfPoints() - 1; i++) {
            perimeter += distanceBetweenTwoPoints(figure.getPoints().get(i),
                    figure.getPoints().get(i + 1));
        }
        perimeter += distanceBetweenTwoPoints(figure.getPoints().get(figure.getNumberOfPoints() - 1),
                figure.getPoints().get(0));
        LOG.info(figure + ": " + perimeter);
        return perimeter;
    }

    @Override
    public Figure getFigure() {
        return figure;
    }
}
