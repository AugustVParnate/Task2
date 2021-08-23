package java.com.epam.jwd.pyramid.action;

import model.Figure;
import validator.MathVector;

import java.com.epam.jwd.pyramid.action.FigureAction2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PyramidAction extends FigureAction2D {

    private static final int NUMBER_OF_DIAGONALS = 2;
    private static final Logger LOG = LogManager.getLogger(PyramidAction.class);

    public QuadrangleActions(Figure figure) {
        super(figure);
    }

    public PyramidType defineTheType() {
        List<MathVector> vectors = getFigure().getVectors();
        List<MathVector> diagonals = getMathVectorsOfDiagonals();

        if (sidesOfTheFigureAreParallel()) {
            if (sidesOfTheFigureArePerpendicular(vectors)) {
                LOG.info(getFigure() + ": " + PyramidTipe.SQUARE);
                return PyramidTipe.SQUARE;
            } else if (diagonals.get(0).perpendicular(diagonals.get(1))) {
                LOG.info(getFigure() + ": " + PyramidTipe.DIAMOND);
                return PyramidTipe.DIAMOND;
            }
        } else if (oppositeSidesOfFigureAreEqual()) {
            LOG.info(getFigure() + ": " + PyramidTipe.PARALLELOGRAM);
            return PyramidTipe.PARALLELOGRAM;
        } else if (twoSidesOfFigureAreParallelTwoOthersNot(vectors)) {
            LOG.info(getFigure() + ": " + PyramidTipe.TRAPEZOID);
            return PyramidTipe.TRAPEZOID;
        }
        LOG.info(getFigure() + ": " + PyramidTipe.ARBITRARY);
        return PyramidTipe.ARBITRARY;
    }

    private List<MathVector> getMathVectorsOfDiagonals() {
        List<MathVector> diagonals = new ArrayList<>();

        //special list (contains vectors of diagonals) for the diamond
        for (int i = 0; i < NUMBER_OF_DIAGONALS; i++) {
            diagonals.add(new MathVector(getFigure().getPoints().get(i),
                    getFigure().getPoints().get(i + 2)));
        }
        return diagonals;
    }

    private boolean twoSidesOfFigureAreParallelTwoOthersNot(List<MathVector> vectors) {
        return (vectors.get(0).scalarProduct(vectors.get(2)) == 0
                && vectors.get(1).scalarProduct(vectors.get(3)) != 0)
                || (vectors.get(0).scalarProduct(vectors.get(2)) != 0
                && vectors.get(1).scalarProduct(vectors.get(3)) == 0);
    }

    private boolean oppositeSidesOfFigureAreEqual() {
        return distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(2),
                        getFigure().getPoints().get(3)))
                && distanceBetweenTwoPoints(getFigure().getPoints().get(1), getFigure().getPoints().get(2))
                .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(3),
                        getFigure().getPoints().get(0)));
    }

    private boolean sidesOfTheFigureArePerpendicular(List<MathVector> vectors) {
        return vectors.get(0).perpendicular(vectors.get(1))
                && vectors.get(2).perpendicular(vectors.get(3));
    }

    private boolean sidesOfTheFigureAreParallel() {
        return distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(1),
                        getFigure().getPoints().get(2)))
                && distanceBetweenTwoPoints(getFigure().getPoints().get(2), getFigure().getPoints().get(3))
                .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(3),
                        getFigure().getPoints().get(0)))
                && distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(2),
                        getFigure().getPoints().get(3)));
    }
}{
}
