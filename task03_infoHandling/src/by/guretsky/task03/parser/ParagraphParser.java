package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;

public class ParagraphParser extends AbstractParser {
    private static final String SPLIT_TO_PARAGRAPH_REGEX = "(?m)(?=^\\s{4})";
    @Override
    public void parse(Component wholeText) {
        parse(SPLIT_TO_PARAGRAPH_REGEX, wholeText);
    }
}
