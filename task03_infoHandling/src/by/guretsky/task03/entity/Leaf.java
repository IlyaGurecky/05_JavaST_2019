package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;

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
     * {@inheritDoc}.
     *
     * @param component component
     */
    @Override
    public void add(final Component component) {
    }

    /**
     * {@inheritDoc}.
     *
     * @param component component
     */
    @Override
    public void remove(final Component component) {
    }

    /**
     * {@inheritDoc}.
     *
     * @param index index
     */
    @Override
    public Component getChild(final int index) {
        return null;
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
