package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.constant.TreeLevel;

public class LexemeParser extends AbstractParser {
    private static final String PARSE_TO_LEXEME_REGEX = "\\s";

    @Override
    public void parse(final Component component, final String data) {
        parse(PARSE_TO_LEXEME_REGEX, component, TreeLevel.LEXEME, data);
    }
}
