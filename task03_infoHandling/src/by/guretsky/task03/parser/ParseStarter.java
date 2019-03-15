package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;

public class ParseStarter extends AbstractParser {
    @Override
    public void parse(Component wholeText) {
        startNext(wholeText);
    }
}
