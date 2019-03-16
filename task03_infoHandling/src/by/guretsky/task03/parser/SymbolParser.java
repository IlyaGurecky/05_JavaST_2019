package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.constant.TreeLevel;

public class SymbolParser extends AbstractParser {
    private static final String SPLIT_TO_SYMBOLS_REGEX = "";

    @Override
    public void parse(final Component component) {
        parse(SPLIT_TO_SYMBOLS_REGEX, component, TreeLevel.SYMBOLS.getLevel());
    }
}
