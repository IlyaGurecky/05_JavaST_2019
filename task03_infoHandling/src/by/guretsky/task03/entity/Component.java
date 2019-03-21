package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IncorrectArgumentException;

/**
 * Interface for Composite Pattern.
 *
 * @author ilyaguretsky
 */
public interface Component {
    /**
     * Add component into components list.
     *
     * @param component component you need to add
     */
    void add(Component component);

    /**
     * Remove component from components list.
     *
     * @param component component you need to remove
     */
    void remove(Component component);

    /**
     * Return component child.
     *
     * @param index child index
     * @return {@link Component} object
     * @throws IncorrectArgumentException if index is incorrect
     */
    Component getChild(int index) throws IncorrectArgumentException;

    /**
     * Level field getter.
     *
     * @return {@link TreeLevel} constant
     */
    TreeLevel getLevel();
}
