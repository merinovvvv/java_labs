package exceptions;

public class EmptySetException extends RuntimeException {
    public EmptySetException() {
        super("model.Set is empty.");
    }
}
