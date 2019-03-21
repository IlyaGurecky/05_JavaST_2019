package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class used to parse paragraphs into sentences.
 *
 * @author ilyaguretsky
 */
public class SentenceParser extends AbstractParser {
    /**
     * Regular expression to parse paragraphs into sentences.
     */
    private static final String SPLIT_TO_SENTENCE_REGEX =
            "[^.]+?(?:\\.{3}|\\.|!|\\?)";

    /**
     * {@inheritDoc}.
     *
     * @param component in which you need to parse
     * @param data      date you need to parse
     */
    @Override
    public void parse(final Component component, final String data) {
        Pattern pattern = Pattern.compile(SPLIT_TO_SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String sentence = matcher.group(0);
            Component sentenceComponent = new TextComponent(TreeLevel.SENTENCE);
            component.add(sentenceComponent);
            startNext(sentenceComponent, sentence.trim());
        }
    }
}
