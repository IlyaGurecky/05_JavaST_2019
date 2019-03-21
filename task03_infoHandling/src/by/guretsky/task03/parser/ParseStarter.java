package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;

/**
 * This class used to start parse chain.
 *
 * @author ilyaguretsky
 */
public class ParseStarter extends AbstractParser {
    /**
     * {@inheritDoc}.
     *
     * @param wholeText main text component
     * @param text      text you need to parse
     */
    @Override
    public void parse(final Component wholeText, final String text) {
        startNext(wholeText, text);
    }
}
