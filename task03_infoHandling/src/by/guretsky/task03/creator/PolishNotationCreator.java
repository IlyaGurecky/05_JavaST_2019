package by.guretsky.task03.creator;

import by.guretsky.task03.exception.IncorrectArgumentException;
import by.guretsky.task03.interpreter.operation.BinaryOperation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolishNotationCreator {
    private static final String SPLIT_EXPRESSION_TO_NUMBERS_AND_FIGURES_REGEX =
            "(?:>{3}|>{2}|<{2}|~|&|\\)|\\(|\\^|[0-9]+|\\|)";
    private List<String> polishNotation = new ArrayList<>();
    private Deque<BinaryOperation> operations = new ArrayDeque<>();

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

    private boolean checkForNumber(final String element) {
        return element.matches("\\d+");
    }

    private void addElementToNotation(String element) {
        polishNotation.add(element);
    }

    private BinaryOperation findOperation(String element)
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

    private boolean isBracket(String element) {
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

    private void addRemainingOperations() {
        while (!operations.isEmpty()) {
            polishNotation.add(operations.pollLast().getOperation());
        }
    }
}
