package by.guretsky.task03.entity;

import by.guretsky.task03.exception.IncorrectArgumentException;

public interface Component {
    void add(final Component component);

    void remove(final Component component);

    void add(final Component... component);

    void remove(final Component... component);

    Component getChild(final int index) throws IncorrectArgumentException;
}