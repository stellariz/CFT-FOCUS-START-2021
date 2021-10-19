package ru.cftfocusstart.task2.figures;

import java.util.logging.Logger;

public abstract class Figure {
    protected final TypesOfFigures type;

    protected Figure(TypesOfFigures type) {
        this.type = type;
    }

    public abstract void getInfo(Logger logger);

    protected abstract double getArea();

    protected abstract double getPerimeter();

    public String getName(){
        return type.name;
    }
}
