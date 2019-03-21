package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.constant.TreeLevel;

/**
 * This class used to parse text into paragraphs.
 *
 * @author ilyaguretsky
 */
public class ParagraphParser extends AbstractParser {
    /**
     * Regular expression used to parse text into paragraphs.
     */
    private static final String SPLIT_TO_PARAGRAPH_REGEX = "(?m)(?=^\\s{4})";

    /**
     * {@inheritDoc}.
     *
     * @param wholeText text component in which you need to parse
     * @param data      date you need to parse
     */
    @Override
    public void parse(final Component wholeText, final String data) {
        parse(SPLIT_TO_PARAGRAPH_REGEX, wholeText, TreeLevel.PARAGRAPH, data);
    }
}
