package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;
import by.guretsky.task03.exception.IncorrectArgumentException;

import java.util.List;

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
    void remove(List<Component> component) throws IllegalOperationException;

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

    /**
     * Components field getter.
     *
     * @return new list of the components
     * @throws IllegalOperationException if component is a leaf
     */
    List<Component> getComponents() throws IllegalOperationException;

    /**
     * Components field setter.
     *
     * @param componentList new components list
     * @throws IllegalOperationException if component is a leaf
     */
    void addComponents(List<Component> componentList) throws
            IllegalOperationException;
}
