package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;

public class LexemeParser extends AbstractParser {
    private static final String PARSE_TO_LEXEME_REGEX = "\\s";

    @Override
    public void parse(Component component) {
        parse(PARSE_TO_LEXEME_REGEX, component);
    }
}
