package by.guretsky.info_system.validator;

public interface Validator<T> {
    boolean validate(T object);
}
