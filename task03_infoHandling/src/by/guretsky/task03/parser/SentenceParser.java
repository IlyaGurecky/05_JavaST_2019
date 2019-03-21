package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {
    private static final String SPLIT_TO_SENTENCE_REGEX =
            "[^.]+?(?:\\.{3}|\\.|!|\\?)";

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
