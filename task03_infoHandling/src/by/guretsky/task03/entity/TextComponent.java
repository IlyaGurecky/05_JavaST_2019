package by.guretsky.task03.entity;

import by.guretsky.task03.exception.IncorrectArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextComponent implements Component {
    private static final Logger LOGGER
            = LogManager.getLogger(TextComponent.class);
    private String info;
    private List<Component> components = new ArrayList<>();

    public TextComponent(String componentInfo) {
        info = componentInfo;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public void add(Component... component) {
        components.addAll(Arrays.asList(component));
    }

    @Override
    public void remove(Component... component) {
        components.removeAll(Arrays.asList(component));
    }

    @Override
    public TextComponent getChild(int index) throws IncorrectArgumentException {
        if (index < 0 || index >= components.size()) {
            LOGGER.error("Incorrect argument");
            throw new IncorrectArgumentException("Out of bound");
        }
        return (TextComponent) components.get(index);
    }

    public void printComponent(final int level) throws
            IncorrectArgumentException {
        this.getChild(0).components.stream()
                .map(component -> ((TextComponent) component).getInfo())
                .forEach(System.out::println);
    }
}
