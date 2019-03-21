package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;

/**
 * This class used to parse lexeme component into words, punctuation marks
 * or expressions.
 *
 * @author ilyaguretsky
 */
public class LexemeComponentParser extends AbstractParser {
    /**
     * Regular expression to check the lexeme for an expression.
     */
    private static final String EXPRESSION_REGEX = "[0-9&^~|<>()]+";
    /**
     * Regular expression used to separate punctuation mark from the word.
     */
    private static final String LEXEME_SEPARATOR = "[^.,?!;:]+";

    /**
     * {@inheritDoc}.
     *
     * @param component component in which you need to parse
     * @param data      date you need to parse
     */
    @Override
    public void parse(final Component component, final String data) {
        String[] strings = data.split(LEXEME_SEPARATOR);
        String punctuation = "";
        if (strings.length != 0) {
            punctuation = strings[strings.length - 1];
        }
        String word = data.substring(0, data.length() - punctuation.length());
        if (word.matches(EXPRESSION_REGEX)) {
            Component expression = new TextComponent(TreeLevel.EXPRESSION);
            component.add(expression);
            startNext(expression, word);
        } else {
            Component wordComponent = new TextComponent(TreeLevel.WORD);
            component.add(wordComponent);
            startNext(wordComponent, word);
        }
        if (punctuation.length() != 0) {
            Component punctuationComponent =
                    new TextComponent(TreeLevel.PUNCTUATION);
            component.add(punctuationComponent);
            startNext(punctuationComponent, punctuation);
        }
    }
}
