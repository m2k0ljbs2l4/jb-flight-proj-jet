package je.jdbc.validator;

public interface Validator<T> {
    ValidationResult isValid(T t);
}
