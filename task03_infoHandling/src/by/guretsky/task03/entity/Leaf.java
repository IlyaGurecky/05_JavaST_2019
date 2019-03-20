package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IllegalOperationException;

public class Leaf implements Component {
    private static final String EXCEPTION_MESSAGE = "Unsupported operation";
    private char info;
    private TreeLevel level;

    public Leaf(final char leafInfo, final TreeLevel treeLevel) {
        level = treeLevel;
        info = leafInfo;
    }

    public TreeLevel getLevel() {
        return level;
    }

    @Override
    public void add(Component component) throws
            IllegalOperationException {
        throw new IllegalOperationException(EXCEPTION_MESSAGE);
    }

    @Override
    public void remove(Component component) throws
            IllegalOperationException {
        throw new IllegalOperationException(EXCEPTION_MESSAGE);

    }

    @Override
    public Component getChild(int index) throws
            IllegalOperationException {
        throw new IllegalOperationException(EXCEPTION_MESSAGE);
    }

    @Override
    public String toString() {
        return String.valueOf(info);
    }
}
