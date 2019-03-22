package by.guretsky.task03.interpreter.operation;

public enum BinaryOperation {
    RIGHT_BRACKET(")", 6),
    LEFT_BRACKET("(", 6),
    NOT("~", 5),
    LEFT_SHIFT("<<", 4),
    RIGHT_SHIFT(">>", 4),
    UNSIGNED_RIGHT_SHIFT(">>>", 4),
    AND("&", 3),
    XOR("^", 2),
    OR("|", 1);

    private String operator;
    private int priority;

    BinaryOperation(final String binOperator, final int prior) {
        operator = binOperator;
        priority = prior;
    }

    public String getOperator() {
        return operator;
    }

    public int getPriority() {
        return priority;
    }
}
