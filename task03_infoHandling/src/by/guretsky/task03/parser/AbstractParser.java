package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractParser {
    private AbstractParser next;

    public abstract void parse(Component component);

    public void linkWith(AbstractParser nextParser) {
        next = nextParser;
    }

    void startNext(Component component) {
        if (next != null) {
            next.parse(component);
        }
    }

    void parse(final String regex, final Component component) {
        String info = ((TextComponent) component).getInfo();
        List<String> splitInfo
                = new ArrayList<>(Arrays.asList(info.split(regex)));

        splitInfo.stream()
                .map(String::trim)
                .forEach(s -> {
                    Component newComponent = new TextComponent(s);
                    component.add(newComponent);
                    startNext(newComponent);
                });
        /*for (String comp : splitInfo) {
            Component newComponent = new TextComponent(comp.trim());
            component.add(newComponent);
            startNext(newComponent);
        }*/
    }
}
