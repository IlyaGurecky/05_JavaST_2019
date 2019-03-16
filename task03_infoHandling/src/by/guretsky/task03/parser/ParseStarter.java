package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;

public class ParseStarter extends AbstractParser {
    @Override
    public void parse(final Component wholeText) {
        startNext(wholeText);
    }
}
