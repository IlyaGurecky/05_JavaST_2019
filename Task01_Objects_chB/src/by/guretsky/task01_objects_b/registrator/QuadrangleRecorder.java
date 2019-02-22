package by.guretsky.task01_objects_b.registrator;

import by.guretsky.task01_objects_b.action.Calculator;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.observer.Observer;

public class QuadrangleRecorder implements Observer {
    private int id;
    private static int idCount = 0;
    private double square;
    private double perimeter;

    public void register(final Quadrangle quadrangle) {
        id = idCount++;
        calculate(quadrangle);
    }

    public int getId() {
        return id;
    }

    public double getSquare() {
        return square;
    }

    public double getPerimeter() {
        return perimeter;
    }

    private void calculate(Quadrangle quadrangle) {
        Calculator calculator = new Calculator();
        square = calculator.square(quadrangle);
        perimeter = calculator.perimeter(quadrangle);
    }

    @Override
    public void update(Object obj) {
        calculate((Quadrangle) obj);
    }
}
