package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;

/**
 * Terminal class of text components.
 *
 * @author ilyaguretsky
 */
public class Leaf implements Component {
    /**
     * Symbol.
     */
    private char info;
    /**
     * Text level.
     */
    private TreeLevel level;

    /**
     * Constructor - to create object with parameters.
     *
     * @param leafInfo  char symbol
     * @param treeLevel leaf text level
     */
    public Leaf(final char leafInfo, final TreeLevel treeLevel) {
        level = treeLevel;
        info = leafInfo;
    }

    /**
     * Level field getter.
     *
     * @return TreeLevel constant
     */
    public TreeLevel getLevel() {
        return level;
    }

    /**
     * @throws IllegalOperationException unsupported operation
     */
    @Override
    public void add(final Component component) throws
            IllegalOperationException {
        throw new IllegalOperationException("Unsupported operation");
    }

    /**
     * @throws IllegalOperationException unsupported operation
     */
    @Override
    public void remove(final Component component) throws
            IllegalOperationException {
        throw new IllegalOperationException("Unsupported operation");
    }

    /**
     * @throws IllegalOperationException unsupported operations
     */
    @Override
    public Component getChild(final int index) throws
            IllegalOperationException {
        throw new IllegalOperationException("Illegal operation");
    }

    /**
     * {@inheritDoc}.
     *
     * @return string symbol
     */
    @Override
    public String toString() {
        return String.valueOf(info);
    }
}
