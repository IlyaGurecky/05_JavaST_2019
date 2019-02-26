package by.guretsky.task01_objects_b.repository.specification;

/**
 * Interface for specifications, which need figure recorder.
 */
public interface FindByQuadrangleCharacteristics extends
        FindQuadrangleSpecification {
    /**
     * {@inheritDoc}.
     */
    @Override
    boolean specified(Object obj);
}
