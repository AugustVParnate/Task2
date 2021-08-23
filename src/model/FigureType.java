package model;

import jdk.nashorn.internal.codegen.CompilerConstants;
import validator.BuildValidator;
import validator.PointValidator;

import javax.xml.validation.Validator;

public enum FigureType {
    POINT(1, new PointValidator()),
    LINE(2, null),
    TRIANGLE(3, null),
    QUADRANGLE(4, new BuildValidator()),
    ;

    public static final CompilerConstants.Access PYRAMID = 0;
    private final int numberOfPoints;
    private final Validator validator;

    FigureType(int numberOfPoints, Validator validator) {
        this.numberOfPoints = numberOfPoints;
        this.validator = validator;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public Validator getValidator() {
        return validator;
    }
}
}
