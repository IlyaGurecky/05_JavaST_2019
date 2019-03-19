package by.guretsky.task03.parser;

import by.guretsky.task03.entity.Component;
import by.guretsky.task03.entity.Leaf;
import by.guretsky.task03.entity.constant.TreeLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymbolParser extends AbstractParser {
    private static final String SPLIT_TO_SYMBOLS_REGEX = "";

    @Override
    public void parse(final Component component, final String data) {
        List<String> splitInfo = new ArrayList<>(Arrays.asList(
                data.split(SPLIT_TO_SYMBOLS_REGEX)));
        splitInfo.forEach(s -> {
            Component newComponent = new Leaf(s.trim(), TreeLevel.SYMBOLS);
            component.add(newComponent);
            startNext(newComponent, s);
        });
    }
}
