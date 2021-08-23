package java.com.epam.jwd.pyramid.action;

import model.Figure;

public class FigureAction {
    public interface FigureActions {

        double perimeter();

        double square();

        Figure getFigure();
    }
}
