package by.guretsky.task01_objects_b.repository;

import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectArgumentException;
import by.guretsky.task01_objects_b.observer.Observer;
import by.guretsky.task01_objects_b.registrator.QuadrangleRecorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuadrangleRepositorySingleton implements Observer {
    private static final Logger LOGGER =
            LogManager.getLogger(QuadrangleRepositorySingleton.class);
    private static final QuadrangleRepositorySingleton INSTANCE =
            new QuadrangleRepositorySingleton();
    private List<Quadrangle> quadrangles;
    private List<QuadrangleRecorder> recorders;

    private QuadrangleRepositorySingleton() {
        quadrangles = new ArrayList<>();
        recorders = new ArrayList<>();
    }

    public static QuadrangleRepositorySingleton getInstance() {
        return INSTANCE;
    }

    public void addFigure(final Quadrangle quadrangle) throws
            IncorrectArgumentException {
        if (quadrangle == null) {
            LOGGER.error("Incorrect argument, we can't delete this "
                    + " figure from repository");
            throw new IncorrectArgumentException("Argument is null");
        }
        quadrangles.add(quadrangle);
        QuadrangleRecorder recorder = new QuadrangleRecorder();
        recorder.register(quadrangle);
        recorders.add(recorder);
    }

    public void deleteFigure(final int index) throws
            IncorrectArgumentException {
        if (index >= quadrangles.size() || index < 0) {
            LOGGER.error("Argument is bigger than list size or less than 0");
            throw new IncorrectArgumentException("Out of bounds");
        }

    }

    public void deleteFigure(final Quadrangle quadrangle) throws
            IncorrectArgumentException {
        if (quadrangle == null) {
            LOGGER.error("Incorrect argument, we can't delete this "
                    + " figure from repository");
            throw new IncorrectArgumentException("Argument is null");
        }
        int counter = 0;
        for (Quadrangle quadrangle1 : quadrangles) {
            if (quadrangle1 == quadrangle) {
                quadrangles.remove(quadrangle);
                recorders.remove(counter);
            }
            counter++;
        }
    }

    public List<Quadrangle> getQuadrangles() {
        return new ArrayList<>(quadrangles);
    }

    public List<QuadrangleRecorder> getRecorders() {
        return new ArrayList<>(recorders);
    }

    @Override
    public void update(Object obj) {
        Quadrangle changedQuadrangle = (Quadrangle) obj;
        int counter = 0;
        for (Quadrangle quadrangle : quadrangles) {
            if (quadrangle == changedQuadrangle) {
                quadrangles.set(counter, changedQuadrangle);
                recorders.get(counter).update(obj);
            }
            ++counter;
        }
    }



    /*public static void main(String[] args) throws IncorrectQuadranglePointsException {
        List<Point> points = new ArrayList<>(Arrays
                .asList(new Point(-2.0, 2.0),
                        new Point(2.0, 2.0),
                        new Point(2.0, -2.0),
                        new Point(-2.0, -2.0)));
        Quadrangle quadrangle = new Quadrangle(points);

        QuadrangleRepositorySingleton repos = QuadrangleRepositorySingleton.getInstance();

        repos.addFigure(quadrangle);
        System.out.println(repos.getRecorders().get(0));
        quadrangle.addObserver(repos);
        quadrangle.setPointX(0, -3.0);

        quadrangle.notifyObservers();

        System.out.println(repos.recorders.get(0));

    }*/
}
