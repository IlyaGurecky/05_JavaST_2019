package by.guretsky.task01_objects_b.observer;

/**
 * Interface for observers.
 */
public interface Observer {
    /**
     * The method is used to update information in observers.
     *
     * @param obj changes.
     */
    void update(Object obj);
}
