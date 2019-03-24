package by.guretsky.task03.sort;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.exception.IllegalOperationException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class allows to sort text lexemes by symbol occurrences.
 */
public class LexemeSorter {

    /**
     * This method sort text lexemes.
     *
     * @param text   text you need to sort
     * @param symbol the character you want to sort by
     * @return list of the sorted lexemes
     * @throws IllegalOperationException if component is a leaf
     */
    public List<Component> sort(final Component text, final char symbol) throws
            IllegalOperationException {
        List<Component> paragraphs = text.getComponents();

        List<Component> sentences = new ArrayList<>();
        for (Component elem : paragraphs) {
            sentences.addAll(elem.getComponents());
        }

        List<Component> lexemes = new ArrayList<>();
        for (Component elem : sentences) {
            lexemes.addAll(elem.getComponents());
        }

        lexemes.sort(Comparator.comparing(o -> o.toString().chars()
                .filter(ch -> ch == symbol).count()).reversed()
                .thenComparing(Object::toString));

        return lexemes;
    }
}
