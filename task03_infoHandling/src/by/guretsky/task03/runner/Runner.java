package by.guretsky.task03.runner;

import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.FileException;
import by.guretsky.task03.parser.*;
import by.guretsky.task03.reader.FileDataReader;

import java.io.File;
import java.util.Arrays;

public class Runner {
    private static final String PATH = "data" + File.separator + "info.txt";

    public static void main(String[] args) throws FileException {
        FileDataReader reader = new FileDataReader(PATH);
        String string = reader.readString();

        TextComponent parsedText =
                new TextComponent(string, TreeLevel.TEXT);

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
        parser.parse(parsedText, string);
        System.out.println(parsedText);
    }
}
