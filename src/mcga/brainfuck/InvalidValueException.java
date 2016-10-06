package mcga.brainfuck;

/**
 * Created by user on 28/09/2016.
 */
public class InvalidValueException extends RuntimeException {
    public InvalidValueException() {
        super();
        System.err.println("Value  must be between 0 and 255");
        System.exit(1);
    }
}
