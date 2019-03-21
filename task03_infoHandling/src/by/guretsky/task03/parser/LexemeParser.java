package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.constant.TreeLevel;

/**
 * This class used to parse sentence into lexeme.
 *
 * @author ilyaguretsky
 */
public class LexemeParser extends AbstractParser {
    /**
     * Regular expression used to parse sentences into lexemes.
     */
    private static final String PARSE_TO_LEXEME_REGEX = "\\s";

    /**
     * {@inheritDoc}.
     *
     * @param component in which you need to parse
     * @param data      date you need to parse
     */
    @Override
    public void parse(final Component component, final String data) {
        parse(PARSE_TO_LEXEME_REGEX, component, TreeLevel.LEXEME, data);
    }
}
