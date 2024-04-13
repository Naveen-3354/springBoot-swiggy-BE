package spring.boot.swiggyBE.components.validation;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
