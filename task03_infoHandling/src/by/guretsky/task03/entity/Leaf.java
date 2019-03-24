package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;

import java.util.List;

/**
 * Terminal class of text components.
 *
 * @author ilyaguretsky
 */
public class Leaf implements Component {
    /**
     * Exceptions message.
     */
    private static final String EXCEPTION_MESSAGE = "Unsupported operation";
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
        throw new IllegalOperationException(EXCEPTION_MESSAGE);
    }

    /**
     * @throws IllegalOperationException unsupported operation
     */
    @Override
    public void remove(final List<Component> component) throws
            IllegalOperationException {
        throw new IllegalOperationException(EXCEPTION_MESSAGE);
    }

    /**
     * @throws IllegalOperationException unsupported operations
     */
    @Override
    public Component getChild(final int index) throws
            IllegalOperationException {
        throw new IllegalOperationException(EXCEPTION_MESSAGE);
    }

    /**
     * @throws IllegalOperationException unsupported operation
     */
    @Override
    public List<Component> getComponents() throws IllegalOperationException {
        throw new IllegalOperationException(EXCEPTION_MESSAGE);
    }

    /**
     * @throws IllegalOperationException unsupported operation
     */
    @Override
    public void addComponents(final List<Component> components) throws
            IllegalOperationException {
        throw new IllegalOperationException(EXCEPTION_MESSAGE);
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
