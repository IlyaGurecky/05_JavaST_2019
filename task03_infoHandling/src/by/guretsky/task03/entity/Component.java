package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;
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
     * @throws IllegalOperationException if operation is unsupported
     */
    void add(Component component) throws IllegalOperationException;

    /**
     * Remove component from components list.
     *
     * @param component component you need to remove
     * @throws IllegalOperationException if operation is unsupported
     */
    void remove(Component component) throws IllegalOperationException;

    /**
     * Return component child.
     *
     * @param index child index
     * @return {@link Component} object
     * @throws IncorrectArgumentException if index is incorrect
     * @throws IllegalOperationException  if operation is unsupported
     */
    Component getChild(int index) throws IncorrectArgumentException,
            IllegalOperationException;

    /**
     * Level field getter.
     *
     * @return {@link TreeLevel} constant
     */
    TreeLevel getLevel();
}
