package by.guretsky.task03.sort;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.exception.IllegalOperationException;
import by.guretsky.task03.exception.IncorrectArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class allows you to sort words in sentences by word length.
 */
public class SentencesSorter {
    /**
     * Logger, which used to log events.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SentencesSorter.class);

    /**
     * This method sort words by length.
     *
     * @param text text you need to sort
     * @return components list
     * @throws IllegalOperationException if component is a leaf
     */
    public List<Component> sort(final Component text) throws
            IllegalOperationException {
        List<Component> paragraphs = text.getComponents();
        List<Component> sentences = new ArrayList<>();
        for (Component component : paragraphs) {
            sentences.addAll(component.getComponents());
        }

        for (Component component : sentences) {
            List<Component> lexemes = component.getComponents();
            try {
                component.remove(lexemes);
            } catch (IllegalOperationException e) {
                LOGGER.error("Unsupported operation");
            }
            lexemes.sort(Comparator.comparing(l -> {
                        try {
                            return l.getChild(0).toString().length();
                        } catch (IncorrectArgumentException e) {
                            LOGGER.error("Out of bounds", e);
                        } catch (IllegalOperationException e) {
                            LOGGER.error("Unsupported operation");
                        }
                        return 0;
                    }
            ));
            component.addComponents(lexemes);
        }
        return sentences;
    }
}
