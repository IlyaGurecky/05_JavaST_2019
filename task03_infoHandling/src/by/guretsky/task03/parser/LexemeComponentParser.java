package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;

public class LexemeComponentParser extends AbstractParser {
    private static final String WORD_REGEX = "^\\(?[A-Za-z-']+\\)?$";
    private static final String WORD_WITH_PUNCTUATION_REGEX
            = "^[A-Za-z-']+[.,!?:]$";

    @Override
    public void parse(final Component component) {
        if (((TextComponent) component).getInfo().matches(WORD_REGEX)) {
            Component word =
                    new TextComponent(((TextComponent) component).getInfo(),
                            TreeLevel.WORD.getLevel());
            component.add(word);
            startNext(word);
        } else if (((TextComponent) component).getInfo()
                .matches(WORD_WITH_PUNCTUATION_REGEX)) {
            String lexemeInfo = ((TextComponent) component).getInfo();
            String word = lexemeInfo.substring(0, lexemeInfo.length() - 1);
            String punctuation = lexemeInfo.substring(lexemeInfo.length() - 1);
            Component wordComponent =
                    new TextComponent(word, TreeLevel.WORD.getLevel());
            Component punctuationComponent =
                    new TextComponent(punctuation,
                            TreeLevel.PUNCTUATION.getLevel());
            component.add(wordComponent);
            component.add(punctuationComponent);
            startNext(wordComponent);
        } else {
            Component expression
                    = new TextComponent(((TextComponent) component).getInfo(),
                    TreeLevel.EXPRESSION.getLevel());
            component.add(expression);
        }
    }
}
