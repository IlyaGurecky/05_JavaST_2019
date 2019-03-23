package by.guretsky.task03.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class is used to store intermediate calculations and final decision.
 */
class Context {
    /**
     * List of calculations.
     */
    private Deque<Integer> contextValues = new ArrayDeque<>();

    /**
     * This method is used for get first element from queue.
     *
     * @return integee number
     */
    Integer poll() {
        return contextValues.pollFirst();
    }

    /**
     * This method is used for add number into the list.
     *
     * @param value value you need to add
     */
    void push(final Integer value) {
        contextValues.push(value);
    }
}
