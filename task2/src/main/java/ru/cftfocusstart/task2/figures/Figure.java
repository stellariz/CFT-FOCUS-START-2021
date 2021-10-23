package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Figure {
    protected final TypesOfFigures type;

    protected Figure(TypesOfFigures type) {
        this.type = type;
    }


    public void getInfo(Logger logger) {
        logger.log(Level.INFO, LogMessages.FIGURE_TYPE.msg + type.name);
        logger.log(Level.INFO, LogMessages.AREA.msg + getArea());
        logger.log(Level.INFO, LogMessages.PERIMETER.msg + getPerimeter());
    }

    protected abstract double getArea();

    protected abstract double getPerimeter();

    public TypesOfFigures getType() {
        return type;
    }
}
