package by.guretsky.task01_objects_b.repository;

import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectArgumentException;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import by.guretsky.task01_objects_b.observer.Observer;
import by.guretsky.task01_objects_b.registrator.QuadrangleRecorder;
import by.guretsky.task01_objects_b.repository.specification.FindByQuadrangleCharacteristics;
import by.guretsky.task01_objects_b.repository.specification.FindQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.QuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortQuadrangleSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is used to store objects and information about this objects.
 */
public final class QuadrangleRepositorySingleton implements Observer {
    /**
     * Logger, which used for log events.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(QuadrangleRepositorySingleton.class);
    /**
     * Object of this class.
     */
    private static final QuadrangleRepositorySingleton INSTANCE =
            new QuadrangleRepositorySingleton();
    /**
     * List of the {@link Quadrangle} objects.
     */
    private List<Quadrangle> quadrangles;
    /**
     * List of the information about all {@link Quadrangle}.
     */
    private List<QuadrangleRecorder> recorders;

    /**
     * Private constructor without parameters.
     */
    private QuadrangleRepositorySingleton() {
        quadrangles = new ArrayList<>();
        recorders = new ArrayList<>();
    }

    /**
     * The method, which create only one object of this class.
     *
     * @return {@link QuadrangleRepositorySingleton} object
     */
    public static QuadrangleRepositorySingleton getInstance() {
        return INSTANCE;
    }

    /**
     * Get method for the quadrangles field.
     *
     * @return list of the {@link Quadrangle}
     */
    public List<Quadrangle> getQuadrangles() {
        return new ArrayList<>(quadrangles);
    }

    /**
     * Get method for the recorders field.
     *
     * @return list of the {@link QuadrangleRecorder}
     */
    public List<QuadrangleRecorder> getRecorders() {
        return new ArrayList<>(recorders);
    }

    /**
     * This method allows you to add objects to the repository.
     *
     * @param quadrangle Object, which we want to add
     * @throws IncorrectArgumentException if argument is null
     */
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

    /**
     * This method allows you to delete object from repository by index.
     *
     * @param index index of the element
     * @throws IncorrectArgumentException if argument is bigger than list size
     *                                    or less than 0
     */
    public void deleteFigure(final int index) throws
            IncorrectArgumentException {
        if (index < 0 || index >= quadrangles.size()) {
            LOGGER.error("Argument is bigger than list size or less than 0");
            throw new IncorrectArgumentException("Out of bounds!");
        }
        quadrangles.remove(index);
        recorders.remove(index);
    }

    /**
     * This method allows you to delete object from repository by object,
     * which you want to delete from repository.
     *
     * @param quadrangle object, which you want to delete
     * @throws IncorrectArgumentException if argument is null
     */
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

    /**
     * The method removes all information from repository.
     */
    public void deleteAll() {
        quadrangles.clear();
        recorders.clear();
    }

    /**
     * The method helps to find quadrangle by recorder ID.
     *
     * @param id id, which you need
     * @return quadrangle
     */
    private Optional<Quadrangle> findQuadrangleById(final int id) {
        for (Quadrangle quadrangle : quadrangles) {
            if (quadrangle.getId() == id) {
                return Optional.of(quadrangle);
            }
        }
        return Optional.empty();
    }

    /**
     * The method allows to change x coordinate of the quadrangle
     * point you need.
     *
     * @param quadrangleIndex index of the quadrangle in repository
     * @param pointIndex      index of the quadrangle point
     * @param newX            new value of the x coordinate
     * @throws IncorrectArgumentException       if argument is bigger than
     *                                          list size or less than 0
     * @throws IncorrectQuadrangleDataException if point index less than 0 or
     *                                          bigger than 3
     */
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

    /**
     * The method allows to change y coordinate of the quadrangle
     * point you need.
     *
     * @param quadrangleIndex index of the quadrangle in repository
     * @param pointIndex      index of the quadrangle point
     * @param newY            new value of the y coordinate
     * @throws IncorrectArgumentException       if argument is bigger than
     *                                          list size or less than 0
     * @throws IncorrectQuadrangleDataException if point index less than 0 or
     *                                          bigger than 3
     */
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

    /**
     * The method allows you to get the quadrangles you need.
     *
     * @param specification specification, which you need
     * @return list of the quadrangles
     */
    public List<Quadrangle> query(final QuadrangleSpecification specification) {
        List<Quadrangle> quadrangleList;
        if (specification instanceof SortQuadrangleSpecification) {
            quadrangleList = getQuadrangles();
            quadrangleList.sort(((SortQuadrangleSpecification) specification)
                    .specifiedComparator());
            return quadrangleList;

        } else if (specification instanceof FindQuadrangleSpecification) {
            quadrangleList = new ArrayList<>();
            if (specification instanceof FindByQuadrangleCharacteristics) {
                for (QuadrangleRecorder recorder : recorders) {
                    if (((FindByQuadrangleCharacteristics) specification)
                            .specified(recorder)) {
                        Optional<Quadrangle> quadrangle =
                                findQuadrangleById(recorder.getId());
                        quadrangle.ifPresent(quadrangleList::add);
                    }
                }
                return quadrangleList;
            } else {
                for (Quadrangle quadrangle : quadrangles) {
                    if (((FindQuadrangleSpecification) specification)
                            .specified(quadrangle)) {
                        quadrangleList.add(quadrangle);
                    }
                }
                return quadrangleList;
            }
        } else {
            LOGGER.info("Incorrect query");
            return new ArrayList<>();
        }
    }

    /**
     * {@inheritDoc}
     */
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
