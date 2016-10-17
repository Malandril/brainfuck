package mcga.brainfuck;

/**
 * Created by user on 28/09/2016.
 */
public class InvalidValueException extends Exception {
    public InvalidValueException() {
        super("Value Must Be between 0 and 255");
    }

    public InvalidValueException(String message) {
        super("Invalid Value " + message + " Value Must Be between 0 and 255");
    }
}
