public class EmptySetException extends RuntimeException {
    public EmptySetException() {
        super("Set is empty.");
    }
}
