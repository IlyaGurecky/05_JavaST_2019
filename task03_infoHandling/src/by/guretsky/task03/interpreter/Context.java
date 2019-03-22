package by.guretsky.task03.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

class Context {
    private Deque<Integer> contextValues = new ArrayDeque<>();

    Integer poll() {
        return contextValues.pollFirst();
    }

    void push(final Integer value) {
        contextValues.push(value);
    }
}
