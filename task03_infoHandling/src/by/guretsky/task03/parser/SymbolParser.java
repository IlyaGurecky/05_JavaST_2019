package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;

public class SymbolParser extends AbstractParser {
    private static final String SPLIT_TO_SYMBOLS_REGEX = "";

    @Override
    public void parse(Component component) {
        parse(SPLIT_TO_SYMBOLS_REGEX, component);
    }
}
