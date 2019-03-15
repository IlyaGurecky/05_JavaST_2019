package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;

public class WordParser extends AbstractParser {
    private static final String PARSE_TO_WORD_REGEX = "";
    @Override
    public void parse(Component component) {
        parse(PARSE_TO_WORD_REGEX, component);
    }
}
