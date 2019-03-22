package by.guretsky.task03.creator;

import by.guretsky.task03.exception.IncorrectArgumentException;
import by.guretsky.task03.interpreter.operation.BinaryOperation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class create the polish notation from the binary expression.
 *
 * @author ilyaguretsky
 */
public class PolishNotationCreator {
    /**
     * Regular expression to split expression to numbers and operations.
     */
    private static final String SPLIT_EXPRESSION_TO_NUMBERS_AND_FIGURES_REGEX =
            "(?:>{3}|>{2}|<{2}|~|&|\\)|\\(|\\^|[0-9]+|\\|)";
    /**
     * Regular expression to check element for a number.
     */
    private static final String NUMBER_CHECK_EXPRESSION = "\\d+";
    /**
     * Final polish notation.
     */
    private List<String> polishNotation = new ArrayList<>();
    /**
     * Operations dequeue.
     */
    private Deque<BinaryOperation> operations = new ArrayDeque<>();

    /**
     * Main method for create polish notation.
     *
     * @param expression expression you need to parse to polish notation
     * @return polish notation list
     * @throws IncorrectArgumentException if expression is incorrect
     */
    public List<String> createPolishNotation(final String expression) throws
            IncorrectArgumentException {
        if (expression == null || expression.isEmpty()) {
            throw new IncorrectArgumentException("Incorrect expression");
        }
        List<String> brokenExpression = splitExpression(expression);
        for (String element : brokenExpression) {
            if (checkForNumber(element)) {
                addElementToNotation(element);
            } else {
                if (isBracket(element)) {
                    continue;
                }
                appendOperation(element);
            }
        }
        addRemainingOperations();
        return polishNotation;
    }

    /**
     * The private method used to split expression to numbers and opearations.
     *
     * @param expression expression you need to parse
     * @return list of the numbers and operations
     */
    private List<String> splitExpression(final String expression) {
        Pattern pattern =
                Pattern.compile(SPLIT_EXPRESSION_TO_NUMBERS_AND_FIGURES_REGEX);
        Matcher matcher = pattern.matcher(expression);
        List<String> expressionElements = new ArrayList<>();
        while (matcher.find()) {
            expressionElements.add(matcher.group(0));
        }
        return expressionElements;
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
     * This method add element to the final polish notation.
     *
     * @param element element you need to add
     */
    private void addElementToNotation(final String element) {
        polishNotation.add(element);
    }

    /**
     * This method find operation in the {@link BinaryOperation}.
     *
     * @param element operation you need to find
     * @return {@link BinaryOperation} object
     * @throws IncorrectArgumentException if operation doesn't exist
     */
    private BinaryOperation findOperation(final String element)
            throws IncorrectArgumentException {
        switch (element) {
            case "(":
                return BinaryOperation.LEFT_BRACKET;
            case ")":
                return BinaryOperation.RIGHT_BRACKET;
            case "~":
                return BinaryOperation.NOT;
            case "<<":
                return BinaryOperation.LEFT_SHIFT;
            case ">>":
                return BinaryOperation.RIGHT_SHIFT;
            case ">>>":
                return BinaryOperation.UNSIGNED_RIGHT_SHIFT;
            case "&":
                return BinaryOperation.AND;
            case "^":
                return BinaryOperation.XOR;
            case "|":
                return BinaryOperation.OR;
            default:
                throw new IncorrectArgumentException("Operation doesn't exist");
        }
    }

    /**
     * This method check the element for a bracket and do some logic it the
     * element is bracket.
     *
     * @param element element you need to check
     * @return true if the element is right or left bracket
     */
    private boolean isBracket(final String element) {
        if ("(".equals(element)) {
            operations.add(BinaryOperation.LEFT_BRACKET);
            return true;
        } else if (")".equals(element)) {
            while (!operations.peekLast()
                    .equals(BinaryOperation.LEFT_BRACKET)) {
                polishNotation.add(operations.pollLast().getOperation());
            }
            operations.pollLast();
            return true;
        }
        return false;
    }

    /**
     * This method add operation to a polish notation or to the queue.
     *
     * @param element element you need to add
     * @throws IncorrectArgumentException if the element is incorrect
     */
    private void appendOperation(final String element) throws
            IncorrectArgumentException {
        if (operations.isEmpty()) {
            operations.add(findOperation(element));
        } else {
            BinaryOperation operation = findOperation(element);
            if (operations.peekLast().getPriority()
                    < operation.getPriority()) {
                operations.add(operation);
            } else if (operations.peekLast().getPriority()
                    >= operation.getPriority()) {
                addElementToNotation(operations.pollLast().getOperation());
                operations.add(operation);
            }
        }
    }

    /**
     * This method add remaining operation from queue to polish notation list.
     */
    private void addRemainingOperations() {
        while (!operations.isEmpty()) {
            polishNotation.add(operations.pollLast().getOperation());
        }
    }
}
