package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;

public class Leaf implements Component {
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
    public void add(Component component) {

    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public Component getChild(int index) {
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(info);
    }
}
