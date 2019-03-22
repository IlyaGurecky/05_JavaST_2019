package by.guretsky.task03.interpreter;

import by.guretsky.task03.exception.IncorrectArgumentException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class includes methods, which allows to calculate polish notation.
 */
public class PolishNotationCalculator {
    /**
     * Regular expression to check string for a number.
     */
    private static final String NUMBER_CHECK_EXPRESSION = "\\d+";
    /**
     * List of expressions.
     */
    private List<Expression> expressions;

    /**
     * This method parse elements of polish notations into {@link Expression}
     * and add this to the expressions list.
     *
     * @param polishNotation List of the polish notation
     * @throws IncorrectArgumentException if notation if incorrect
     */
    private void parse(final List<String> polishNotation) throws
            IncorrectArgumentException {
        for (String element : polishNotation) {
            switch (element) {
                case "^":
                    expressions.add(c -> c.push(c.poll() ^ c.poll()));
                    break;
                case "|":
                    expressions.add(c -> c.push(c.poll() | c.poll()));
                    break;
                case "&":
                    expressions.add(c -> c.push(c.poll() & c.poll()));
                    break;
                case "~":
                    expressions.add(c -> c.push(~c.poll()));
                    break;
                case ">>":
                    expressions.add(c -> {
                        int secondNumber = c.poll();
                        int firstNumber = c.poll();
                        c.push(firstNumber >> secondNumber);
                    });
                    break;
                case "<<":
                    expressions.add(c -> {
                        int secondNumber = c.poll();
                        int firstNumber = c.poll();
                        c.push(firstNumber << secondNumber);
                    });
                    break;
                case ">>>":
                    expressions.add(c -> {
                        int secondNumber = c.poll();
                        int firstNumber = c.poll();
                        c.push(firstNumber >>> secondNumber);
                    });
                    break;
                default:
                    if (checkForNumber(element)) {
                        expressions.add(c -> c.push(Integer.parseInt(element)));
                    } else {
                        throw new IncorrectArgumentException("Incorrect "
                                + "polish notation");
                    }
            }
        }
    }

    /**
     * This method check the element for a number.
     *
     * @param element element you need to check.
     * @return true if the element is a number
     */
    private boolean checkForNumber(final String element) {
        return element.matches(NUMBER_CHECK_EXPRESSION);
    }

    /**
     * Client method.
     *
     * @param polishNotation notation you need to calculate
     * @return Integer result
     * @throws IncorrectArgumentException if notation is incorrect
     */
    public Integer calculate(final List<String> polishNotation) throws
            IncorrectArgumentException {
        expressions = new ArrayList<>();
        Context context = new Context();
        parse(polishNotation);
        for (Expression expression : expressions) {
            expression.interpret(context);
        }
        return context.poll();
    }
}
