package by.guretsky.task03.creator;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.parser.AbstractParser;
import by.guretsky.task03.parser.LexemeComponentParser;
import by.guretsky.task03.parser.LexemeParser;
import by.guretsky.task03.parser.ParagraphParser;
import by.guretsky.task03.parser.ParseStarter;
import by.guretsky.task03.parser.SentenceParser;
import by.guretsky.task03.parser.SymbolParser;

/**
 * This class includes the method to create text components tree.
 */
public class TextTreeCreator {

    /**
     * Create text component tree.
     *
     * @param data string text
     * @return text component
     */
    public Component createTextTree(final String data) {
        Component text = new TextComponent(TreeLevel.TEXT);

        AbstractParser parser = new ParseStarter();
        AbstractParser paragraphParser = new ParagraphParser();
        AbstractParser sentenceParser = new SentenceParser();
        AbstractParser lexemeParser = new LexemeParser();
        AbstractParser lexemeComponentParser = new LexemeComponentParser();
        AbstractParser symbolParser = new SymbolParser();

        parser.linkWith(paragraphParser);
        paragraphParser.linkWith(sentenceParser);
        sentenceParser.linkWith(lexemeParser);
        lexemeParser.linkWith(lexemeComponentParser);
        lexemeComponentParser.linkWith(symbolParser);
        parser.parse(text, data);

        return text;
    }
}
