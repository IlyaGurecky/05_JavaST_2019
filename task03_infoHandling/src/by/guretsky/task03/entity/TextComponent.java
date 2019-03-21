package by.guretsky.task03.entity;

import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IncorrectArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComponent implements Component {
    private static final Logger LOGGER
            = LogManager.getLogger(TextComponent.class);
    private TreeLevel level;
    private List<Component> components = new ArrayList<>();

    public TextComponent(final TreeLevel treeLevel) {
        level = treeLevel;
    }

    public TreeLevel getLevel() {
        return level;
    }

    @Override
    public void add(final Component component) {
        components.add(component);
    }

    @Override
    public void remove(final Component component) {
        components.remove(component);
    }

    @Override
    public TextComponent getChild(final int index) throws
            IncorrectArgumentException {
        if (index < 0 || index >= components.size()) {
            LOGGER.error("Incorrect argument");
            throw new IncorrectArgumentException("Out of bound");
        }
        return (TextComponent) components.get(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        switch (level) {
            case LEXEME:
                builder.append(" ");
                break;
            case PARAGRAPH:
                builder.append("   ");
                break;
            default:
                break;
        }
        for (Component component : components) {
            builder.append(component.toString());
        }
        if (level.equals(TreeLevel.PARAGRAPH)) {
            builder.append("\n");
        }

        return builder.toString();
    }
}
