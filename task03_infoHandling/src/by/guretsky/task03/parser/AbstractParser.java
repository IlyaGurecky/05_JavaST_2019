package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.TextComponent;
import by.guretsky.task03.entity.constant.TreeLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractParser {
    private AbstractParser next;

    public abstract void parse(final Component component, final String data);

    public void linkWith(final AbstractParser nextParser) {
        next = nextParser;
    }

    void startNext(final Component component, final String data) {
        if (next != null) {
            next.parse(component, data);
        }
    }

    void parse(final String regex, final Component component,
               final TreeLevel level, final String componentData) {
        List<String> splitInfo
                = new ArrayList<>(Arrays.asList(componentData.split(regex)));

        splitInfo.forEach(s -> {
            Component newComponent = new TextComponent(s.trim(), level);
            component.add(newComponent);
            startNext(newComponent, s.trim());
        });
    }
}
