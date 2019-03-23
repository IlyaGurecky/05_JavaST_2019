package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.Leaf;
import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class used to parse words, expressions and punctuation marks
 * into symbols.
 *
 * @author ilyaguretsky
 */
public class SymbolParser extends AbstractParser {
    /**
     * Logger, which used to log events.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SymbolParser.class);
    /**
     * Regular expression to parse components into symbols.
     */
    private static final String SPLIT_TO_SYMBOLS_REGEX = "";

    /**
     * {@inheritDoc}.
     *
     * @param component in which you need to parse
     * @param data      date you need to parse
     */
    @Override
    public void parse(final Component component, final String data) {
        List<String> splitInfo = new ArrayList<>(Arrays.asList(
                data.split(SPLIT_TO_SYMBOLS_REGEX)));
        splitInfo.forEach(s -> {
            Component newComponent = new Leaf(s.trim().charAt(0),
                    TreeLevel.SYMBOLS);
            try {
                component.add(newComponent);
            } catch (IllegalOperationException e) {
                LOGGER.error("Unsupported operation", e);
            }
        });
    }
}
