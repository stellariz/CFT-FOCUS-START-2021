package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Figure {

    protected final TypesOfFigures type;
    protected boolean degenerate = false;

    protected Figure(TypesOfFigures type, double[] args) {
        checkArgs(args);
        this.type = type;
    }


    public void getInfo(Logger logger) {
        logger.log(Level.INFO, LogMessages.FIGURE_TYPE.msg + getType());
        logger.log(Level.INFO, LogMessages.AREA.msg + getArea());
        logger.log(Level.INFO, LogMessages.PERIMETER.msg + getPerimeter());
    }

    protected abstract double getArea();

    protected abstract double getPerimeter();

    public TypesOfFigures getType() {
        return type;
    }

    protected void checkArgs(double[] args) {
        checkParamsSize(args);
        checkPosParams(args);
    }

    protected abstract void checkParamsSize(double[] args);

    private void checkPosParams(double[] args) {
        for (double param : args) {
            if (param < 0.0) {
                throw new IllegalArgumentException("Parameter " + param + " is negative!");
            }
            if (param == 0.0){
                degenerate = true;
            }
        }
    }
}
