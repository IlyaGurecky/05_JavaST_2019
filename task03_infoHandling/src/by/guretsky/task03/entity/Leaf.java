package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;

public class Leaf implements Component {
    private String info;
    private TreeLevel level;

    public Leaf(final String leafInfo, TreeLevel treeLevel) {
        level = treeLevel;
        info = leafInfo;
    }

    public TreeLevel getLevel() {
        return level;
    }

    @Override
    public void add(Component component) throws
            UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) throws
            UnsupportedOperationException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Component getChild(int index) throws
            UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return info;
    }
}
