package by.guretsky.task03.entity;

import by.guretsky.task03.creator.PolishNotationCreator;
import by.guretsky.task03.entity.constant.TreeLevel;
import by.guretsky.task03.exception.IncorrectArgumentException;
import by.guretsky.task03.interpreter.PolishNotationCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Text component class. This class collects child components and component
 * text level.
 *
 * @author ilyaguretsky
 */
public class TextComponent implements Component {
    /**
     * Logger, which used to log events.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TextComponent.class);
    /**
     * Text component level.
     */
    private TreeLevel level;
    /**
     * Text component components.
     */
    private List<Component> components = new ArrayList<>();

    /**
     * Constructor - to create object with parameters.
     *
     * @param treeLevel text component level
     */
    public TextComponent(final TreeLevel treeLevel) {
        level = treeLevel;
    }

    /**
     * Level field getter.
     *
     * @return component level
     */
    public TreeLevel getLevel() {
        return level;
    }

    /**
     * {@inheritDoc}.
     *
     * @param component component you need to add
     */
    @Override
    public void add(final Component component) {
        components.add(component);
    }

    /**
     * {@inheritDoc}.
     *
     * @param component component you need to remove
     */
    @Override
    public void remove(final List<Component> component) {
        components.removeAll(component);
    }

    /**
     * {@inheritDoc}.
     *
     * @param index child index
     * @return the child component of the component
     * @throws IncorrectArgumentException if index is incorrect
     */
    @Override
    public TextComponent getChild(final int index) throws
            IncorrectArgumentException {
        if (index < 0 || index >= components.size()) {
            LOGGER.error("Incorrect argument");
            throw new IncorrectArgumentException("Out of bound");
        }
        return (TextComponent) components.get(index);
    }

    /**
     * {@inheritDoc}.
     *
     * @return the components list
     */
    @Override
    public List<Component> getComponents() {
        return new ArrayList<>(components);
    }

    /**
     * Set new components.
     *
     * @param newComponents new components
     */
    @Override
    public void addComponents(final List<Component> newComponents) {
        components.addAll(newComponents);
    }

    /**
     * {@inheritDoc}.
     * Collect the text.
     *
     * @return component info
     */
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
            if (component.getLevel().equals(TreeLevel.EXPRESSION)) {
                try {
                    PolishNotationCreator creator = new PolishNotationCreator();
                    List<String> polishNotation =
                            creator.createPolishNotation(component.toString());
                    PolishNotationCalculator calculator
                            = new PolishNotationCalculator();
                    builder.append(calculator.calculate(polishNotation));
                } catch (IncorrectArgumentException e) {
                    LOGGER.error("Incorrect expression", e);
                }
                continue;
            }
            builder.append(component.toString());
        }

        if (level.equals(TreeLevel.PARAGRAPH)) {
            builder.append("\n");
        }

        return builder.toString();
    }
}
