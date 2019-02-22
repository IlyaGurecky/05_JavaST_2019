package by.guretsky.task01_objects_b.observer;

/**
 * Interface for observables.
 */
public interface Observable {
    /**
     * The method is used to add observer.
     *
     * @param observer observer we need to add
     */
    void addObserver(Observer observer);

    /**
     * The method is used to delete observer.
     *
     * @param observer observer we need to delete
     */
    void deleteObserver(Observer observer);

    /**
     * The method is used to report changes.
     */
    void notifyObservers();
}
