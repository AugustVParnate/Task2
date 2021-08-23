package reader;

import model.*;

import javax.xml.validation.Validator;
import java.com.epam.jwd.pyramid.action.exception.ArgumentException;
import java.com.epam.jwd.pyramid.action.exception.BuildException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FigureReader {
    private static final Logger LOG = LogManager.getLogger(FigureReader.class);

    private static final String RESULT_MSG = "Number of figures in file: %d. Successfully built %d figures.";
    private static final String FIGURE_WAS_BUILT_MSG = "Figure was built (%s): %s";
    private static final String FIGURE_CAN_NOT_BE_BUILT_MSG = "%s can't be built from coordinates: %s";
    private static final String SUCCESSFUL_INITIALIZATION_MSG = "%s was successfully initialized!";
    private static final String NOT_SUCCESSFUL_INITIALIZATION_MSG = "%s was not successfully initialized!";

    private Validator validator = null;
    private final FigureType figureType;
    private int numberOfFiguresInFile = 0;
    private int numberOfBuiltFigures = 0;

    public FigureReader(FigureType figureType) {
        this.figureType = figureType;
        if (figureType != null) {
            validator = figureType.getValidator();
            LOG.trace(String.format(SUCCESSFUL_INITIALIZATION_MSG, getClass().getSimpleName()));
        } else {
            LOG.warn(String.format(NOT_SUCCESSFUL_INITIALIZATION_MSG, getClass().getSimpleName()));
        }
    }

    public int getNumberOfBuiltFigures() {
        return numberOfBuiltFigures;
    }

    public int getNumberOfFiguresInFile() {
        return numberOfFiguresInFile;
    }

    public ArrayList<? extends Figure> scanFigures(Scanner fileScanner) {
        ArrayList<Figure> figureList = null;
        PointFactory pointFactory = new PointFactory();
        numberOfBuiltFigures = 0;
        numberOfFiguresInFile = 0;

        if (fileScanner != null && figureType != null
                && (2 * figureType.getNumberOfPoints() > 0)) {
            figureList = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] coordinates = line.split("\\s+");
                List<Point> points = new Array List<>();

                numberOfFiguresInFile++;
                try {
                    if (!validator.isValid(line)) {
                        String errorMsg = String.format(FIGURE_CAN_NOT_BE_BUILT_MSG, figureType.name(),
                                new ArrayList<>(Arrays.asList(coordinates)));
                        throw new ArgumentException(errorMsg);
                    }

                    makeListOfCoordinates(pointFactory, coordinates, points);
                    Figure figure = buildFigures(pointFactory, points);
                    figureList.add(figure);
                } catch (ArgumentException | BuildException e) {
                    LOG.error(e);
                }
            }
            LOG.trace(String.format(RESULT_MSG, getNumberOfFiguresInFile(), getNumberOfBuiltFigures()));
        }
        return figureList;
    }

    private Figure buildFigures(FigureFactory figureFactory, List<Point> points) {
        Figure figure;
        switch (figureType) {
            case POINT:
                figureFactory = new PointFactory();
                break;
            case LINE:
            case TRIANGLE:
                break;
            case QUADRANGLE:
                figureFactory = new PyramidFactory();
                break;
        }
        figure = figureFactory.newInstance(points);
        numberOfBuiltFigures++;
        LOG.trace(String.format(FIGURE_WAS_BUILT_MSG,
                figure.getClass().getSimpleName(),
                figure.getPoints()));
        return figure;
    }

    private void makeListOfCoordinates(PointFactory figureFactory, String[] coordinates, List<Point> points) {

        for (int i = 0; i < coordinates.length; i += 2) {
            points.add(figureFactory.newInstance(
                    Double.parseDouble(coordinates[i]),
                    Double.parseDouble(coordinates[i + 1]))
            );
        }
    }
}
