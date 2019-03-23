package by.guretsky.task03.interpreter;

/**
 * Functional interface is used for Interpreter pattern.
 */
@FunctionalInterface
public interface Expression {
    /**
     * Interpret method.
     *
     * @param context context object
     */
    void interpret(Context context);
}
