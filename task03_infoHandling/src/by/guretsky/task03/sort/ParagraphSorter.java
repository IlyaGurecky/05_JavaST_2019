package by.guretsky.task03.sort;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.exception.IllegalOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 * This class includes method, which used to sort text paragraphs by number
 * of sentences.
 */
public class ParagraphSorter {
    /**
     * Logger to log events.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ParagraphSorter.class);

    /**
     * This method sort paragraphs by number of sentences.
     *
     * @param component component you need to sort.
     * @return components list
     * @throws IllegalOperationException if components is a leaf
     */
    public List<Component> sort(final Component component) throws
            IllegalOperationException {
        List<Component> paragraphs = component.getComponents();
        paragraphs.sort(Comparator.comparingInt(o -> {
            try {
                return o.getComponents().size();
            } catch (IllegalOperationException e) {
                LOGGER.error("Unsupported operation");
            }
            return 0;
        }));
        return paragraphs;
    }
}
