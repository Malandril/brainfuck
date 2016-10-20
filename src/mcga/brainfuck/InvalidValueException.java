package mcga.brainfuck;


/**
 * This exception is called if the value of a memory cell exceeds 255 or becomes negative.
 */
public class InvalidValueException extends Exception {
    /**
     * Calls the superclass constructor with a default message.
     * @see Exception#Exception()
     */
    public InvalidValueException() {
        super("Value must be between " + Memory.MIN_CELL_VALUE + " and " + Memory.MAX_CELL_VALUE);
    }

    /**
     * Calls the superclass constructor with the message in parameter.
     * @param message String to display when the exception is thrown.
     * @see Exception#Exception(String)
     */
    public InvalidValueException(String message) {
        super("Value must be between " + Memory.MIN_CELL_VALUE + " and " + Memory.MAX_CELL_VALUE);
    }
}
