package by.guretsky.task03.action;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.exception.IllegalOperationException;
import by.guretsky.task03.sort.LexemeSorter;
import by.guretsky.task03.sort.ParagraphSorter;
import by.guretsky.task03.sort.SentencesSorter;

import java.util.List;

/**
 * This class includes methods to sort text.
 */
public class TextSorter {
    /**
     * Sort paragraphs by sentences amount.
     *
     * @param text text you need to sort
     * @return List of the sorted paragraphs
     * @throws IllegalOperationException if component is Leaf
     */
    public List<Component> sortParagraphsBySentences(final Component text)
            throws IllegalOperationException {
        ParagraphSorter sorter = new ParagraphSorter();
        return sorter.sort(text);
    }

    /**
     * Sort words by length.
     *
     * @param text text you need to sort
     * @return List of the sorted sentences
     * @throws IllegalOperationException if Component is a Leaf
     */
    public List<Component> sortSentencesByWordLength(final Component text)
            throws IllegalOperationException {
        SentencesSorter sorter = new SentencesSorter();
        return sorter.sort(text);
    }

    /**
     * Sort lexemes by symbol occurrences.
     *
     * @param text   text you need to sort
     * @param symbol symbol, which occurrences you need to check
     * @return List of the sorted lexemes
     * @throws IllegalOperationException if Component is a Leaf
     */
    public List<Component> sortLexemesBySymbols(final Component text,
                                                final char symbol) throws
            IllegalOperationException {
        LexemeSorter sorter = new LexemeSorter();
        return sorter.sort(text, symbol);
    }
}
