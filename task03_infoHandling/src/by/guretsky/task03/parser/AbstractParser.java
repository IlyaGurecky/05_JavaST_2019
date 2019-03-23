package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract parser used to create hierarchy for chain of responsibility Pattern.
 *
 * @author ilyaguretsky
 */
public abstract class AbstractParser {
    /**
     * Logger, which used to log events.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AbstractParser.class);
    /**
     * Next parser in the chain.
     */
    private AbstractParser next;

    /**
     * Abstract method.
     *
     * @param component in which you need to parse
     * @param data      date you need to parse
     */
    public abstract void parse(Component component, String data);

    /**
     * This method used to link parsers in the chain.
     *
     * @param nextParser next parser
     */
    public void linkWith(final AbstractParser nextParser) {
        next = nextParser;
    }

    /**
     * This method start next parser in chain.
     *
     * @param component component in which you need to parse
     * @param data      data you need to parse
     */
    void startNext(final Component component, final String data) {
        if (next != null) {
            next.parse(component, data);
        }
    }

    /**
     * Inner parse method used to avoid code duplication.
     *
     * @param regex         regular expression
     * @param component     component in which you need to parse
     * @param level         component level
     * @param componentData data you need to parse
     */
    void parse(final String regex, final Component component,
               final TreeLevel level, final String componentData) {
        List<String> splitInfo
                = new ArrayList<>(Arrays.asList(componentData.split(regex)));

        splitInfo.forEach(s -> {
            Component newComponent = new TextComponent(level);
            try {
                component.add(newComponent);
            } catch (IllegalOperationException e) {
                LOGGER.error("Unsupported operation", e);
            }
            startNext(newComponent, s.trim());
        });
    }
}
