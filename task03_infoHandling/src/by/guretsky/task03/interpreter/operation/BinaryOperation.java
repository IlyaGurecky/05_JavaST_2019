package by.guretsky.task03.interpreter.operation;

/**
 * This class consists of binary operations constants.
 */
public enum BinaryOperation {
    /**
     * Binary not operation.
     */
    NOT("~", 5),
    /**
     * Binary Left shift operation.
     */
    LEFT_SHIFT("<<", 4),
    /**
     * Binary right shift operation.
     */
    RIGHT_SHIFT(">>", 4),
    /**
     * Binary unsigned right shift operation.
     */
    UNSIGNED_RIGHT_SHIFT(">>>", 4),
    /**
     * Binary and operation.
     */
    AND("&", 3),
    /**
     * Binary xor operation.
     */
    XOR("^", 2),
    /**
     * Binary or operation.
     */
    OR("|", 1),
    /**
     * Right bracket.
     */
    RIGHT_BRACKET(")", 0),
    /**
     * Left bracket.
     */
    LEFT_BRACKET("(", 0);

    /**
     * String representation of a constant.
     */
    private String operation;
    private int priority;

    /**
     * Constructs objects with special parameters.
     *
     * @param binOperation operation
     * @param prior        operation priority
     */
    BinaryOperation(final String binOperation, final int prior) {
        operation = binOperation;
        priority = prior;
    }

    /**
     * Operation field getter.
     *
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Priority field getter.
     *
     * @return priority
     */
    public int getPriority() {
        return priority;
    }
}
