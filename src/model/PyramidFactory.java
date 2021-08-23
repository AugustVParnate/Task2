package model;

import java.com.epam.jwd.pyramid.action.exception.BuildException;
import java.util.List;

public class PyramidFactory {
    private static final String LINES_ARE_CROSSING_MSG = "Lines are crossing! ";
    private static final String WRONG_NUMBER_OF_COORDINATES_MSG = "Wrong number of coordinates! ";
    private static final String ARGUMENTS_ERROR_MSG = "%s cannot be built from coordinates: %s";


    @Override
    public Pyramid newInstance(List<Point> pointList) {
        String errorMsg = String.format(ARGUMENTS_ERROR_MSG, FigureType.PYRAMID.name(), pointList);

        if (pointList.size() == FigureType.QUADRANGLE.getNumberOfPoints()) {
            if (new BuildValidator().creatable(pointList)) {
                return new Pyramid(pointList);
            } else {
                throw new BuildException(LINES_ARE_CROSSING_MSG + errorMsg);
            }
        } else {
            throw new BuildException(WRONG_NUMBER_OF_COORDINATES_MSG + errorMsg);
        }
    }

}
