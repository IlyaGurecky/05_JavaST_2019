package by.guretsky.task01_objects_b.repository;

import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectArgumentException;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import by.guretsky.task01_objects_b.observer.Observer;
import by.guretsky.task01_objects_b.registrator.QuadrangleRecorder;
import by.guretsky.task01_objects_b.repository.specification.FindQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.QuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortQuadrangleSpecification;
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

    public List<Quadrangle> getQuadrangles() {
        return new ArrayList<>(quadrangles);
    }

    public List<QuadrangleRecorder> getRecorders() {
        return new ArrayList<>(recorders);
    }

    public void addFigure(final Quadrangle quadrangle) throws
            IncorrectArgumentException {
        if (quadrangle == null) {
            LOGGER.error("Incorrect argument, you can't add this figure"
                    + " to repository");
            throw new IncorrectArgumentException("Argument is null");
        }
        quadrangles.add(quadrangle);
        QuadrangleRecorder recorder = new QuadrangleRecorder();
        recorder.register(quadrangle);
        recorders.add(recorder);
    }

    public void deleteFigure(final int index) throws
            IncorrectArgumentException {
        if (index < 0 || index >= quadrangles.size()) {
            LOGGER.error("Argument is bigger than list size or less than 0");
            throw new IncorrectArgumentException("Out of bounds");
        }
        quadrangles.remove(index);
        recorders.remove(index);
    }

    public void deleteFigure(final Quadrangle quadrangle) throws
            IncorrectArgumentException {
        if (quadrangle == null) {
            LOGGER.error("Incorrect argument, you can't delete this "
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

    public void deleteAll() {
        quadrangles.clear();
        recorders.clear();
    }

    public void changeQuadranglePointX(final int quadrangleIndex,
                                       final int pointIndex,
                                       final double newX) throws
            IncorrectArgumentException, IncorrectQuadrangleDataException {
        final int quadranglePointsAmount = 4;

        if (quadrangleIndex >= quadrangles.size() || quadrangleIndex < 0
                || pointIndex >= quadranglePointsAmount || pointIndex < 0) {
            LOGGER.error("Argument is bigger than list size or less than 0");
            throw new IncorrectArgumentException("Out of bounds");
        }
        Quadrangle quadrangle = quadrangles.get(quadrangleIndex);
        quadrangle.addObserver(INSTANCE);
        quadrangle.setPointX(pointIndex, newX);
        quadrangle.notifyObservers();
    }

    public void changeQuadranglePointY(final int quadrangleIndex,
                                       final int pointIndex,
                                       final double newY) throws
            IncorrectArgumentException, IncorrectQuadrangleDataException {
        final int quadranglePointsAmount = 4;

        if (quadrangleIndex >= quadrangles.size() || quadrangleIndex < 0
                || pointIndex >= quadranglePointsAmount || pointIndex < 0) {
            LOGGER.error("Argument is bigger than list size or "
                    + " less than zero");
            throw new IncorrectArgumentException("Out of bounds");
        }
        Quadrangle quadrangle = quadrangles.get(quadrangleIndex);
        quadrangle.addObserver(INSTANCE);
        quadrangle.setPointY(pointIndex, newY);
        quadrangle.notifyObservers();
    }

    public List<Quadrangle> query(final QuadrangleSpecification specification) {
        List<Quadrangle> quadrangleList;

        if (specification instanceof SortQuadrangleSpecification) {
            quadrangleList = getQuadrangles();
            quadrangleList.sort(((SortQuadrangleSpecification) specification)
                    .specifiedComparator());
        } else if (specification instanceof FindQuadrangleSpecification) {
            quadrangleList = new ArrayList<>();
            for (Quadrangle quadrangle : quadrangles) {
                if (((FindQuadrangleSpecification) specification)
                        .specified(quadrangle)) {
                    quadrangleList.add(quadrangle);
                }
            }
        } else {
            LOGGER.info("Incorrect query");
            return new ArrayList<>();
        }
        return quadrangleList;
    }

    @Override
    public void update(final Object obj) {
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
}
