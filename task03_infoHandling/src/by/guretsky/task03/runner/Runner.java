package by.guretsky.task03.runner;

import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.exception.FileException;
import by.guretsky.task03.exception.IncorrectArgumentException;
import by.guretsky.task03.parser.*;
import by.guretsky.task03.reader.FileDataReader;

import java.io.File;

public class Runner {
    private static final String PATH = "data" + File.separator + "info.txt";

    public static void main(String[] args) throws FileException, IncorrectArgumentException {
        FileDataReader reader = new FileDataReader(PATH);
        String string = reader.readString();

        TextComponent parsedText = new TextComponent(string);

        AbstractParser parser = new ParseStarter();
        AbstractParser paragraphParser = new ParagraphParser();
        AbstractParser sentenceParser = new SentenceParser();
        AbstractParser lexemeParser = new LexemeParser();

        parser.linkWith(paragraphParser);
        paragraphParser.linkWith(sentenceParser);
        sentenceParser.linkWith(lexemeParser);

        parser.parse(parsedText);
        parsedText.printComponent(1);
    }
}
