package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;
import by.guretsky.task03.exception.IncorrectArgumentException;

public interface Component {
    void add(final Component component) throws IllegalOperationException;

    void remove(final Component component) throws IllegalOperationException;

    Component getChild(final int index) throws IncorrectArgumentException,
            IllegalOperationException;

    TreeLevel getLevel();
}