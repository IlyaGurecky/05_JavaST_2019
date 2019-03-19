package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.Leaf;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;

public class LexemeComponentParser extends AbstractParser {
    private static final String WORD_REGEX = "^\\(?[A-Za-z-']+\\)?$";
    private static final String WORD_WITH_PUNCTUATION_REGEX
            = "^[A-Za-z-']+[.,!?:]$";

    @Override
    public void parse(final Component component, String data) {
        if (data.matches(WORD_REGEX)) {
            Component word = new TextComponent(data, TreeLevel.WORD);
            component.add(word);
            startNext(word, data);
        } else if (data.matches(WORD_WITH_PUNCTUATION_REGEX)) {

            String word = data.substring(0, data.length() - 1);
            String punctuation = data.substring(data.length() - 1);


            Component wordComponent = new TextComponent(word, TreeLevel.WORD);
            Component punctuationComponent =
                    new Leaf(punctuation, TreeLevel.PUNCTUATION);
            component.add(wordComponent);
            component.add(punctuationComponent);
            startNext(wordComponent, word);
        } else {
            Component expression = new Leaf(data, TreeLevel.EXPRESSION);
            component.add(expression);
        }
    }
}
