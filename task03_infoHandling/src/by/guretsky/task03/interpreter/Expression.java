package by.guretsky.task03.interpreter;

@FunctionalInterface
public interface Expression {
    void interpret(Context context);
}
