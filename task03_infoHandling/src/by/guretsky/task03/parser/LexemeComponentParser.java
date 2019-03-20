package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;

public class LexemeComponentParser extends AbstractParser {
    private static final String EXPRESSION_REGEX = "[0-9&^~|<>()]+";
    private static final String LEXEME_SEPARATOR = "[^.,?!;:]+";

    @Override
    public void parse(final Component component, String data)  {
        String[] strings = data.split(LEXEME_SEPARATOR);
        String punctuation = "";
        if (strings.length != 0) {
            punctuation = strings[1];
        }
        String word = data.substring(0, data.length() - punctuation.length());
        if (word.matches(EXPRESSION_REGEX)) {
            Component expression =
                    new TextComponent(word, TreeLevel.EXPRESSION);
            component.add(expression);
            startNext(expression, word);
        } else {
            Component wordComponent = new TextComponent(word, TreeLevel.WORD);
            component.add(wordComponent);
            startNext(wordComponent, word);
        }
        if (punctuation.length() != 0) {
            Component punctuationComponent =
                    new TextComponent(punctuation, TreeLevel.PUNCTUATION);
            component.add(punctuationComponent);
            startNext(punctuationComponent, punctuation);
        }
    }
}
