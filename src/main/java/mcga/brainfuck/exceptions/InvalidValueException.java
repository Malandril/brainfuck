package mcga.brainfuck.exceptions;


import mcga.brainfuck.Memory;

/**
 * This exception is called if the value of a memory cell exceeds 255 or becomes negative.
 */
public class InvalidValueException extends Exception {

    public static final int EXIT_CODE = 1;

    /**
     * Calls the superclass constructor with a default message.
     * @see Exception#Exception()
     */
    public InvalidValueException() {
        super("Value must be between " + Memory.MIN_CELL_VALUE + " and " + Memory.MAX_CELL_VALUE);
    }

    /**
     * Calls the superclass constructor with the message in parameter.
     * @param value String to display when the exception is thrown.
     * @see Exception#Exception(String)
     */
    public InvalidValueException(String value) {
        super("Invalid value: " + value + " , value must be between " + Memory.MIN_CELL_VALUE + " and " + Memory.MAX_CELL_VALUE);
    }
}
