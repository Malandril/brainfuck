package mcga.brainfuck.exceptions;

/**
 * This exception is called if the parenthesis is not valid
 */
public class InvalidCodeException extends Exception{
    public static final int EXIT_CODE=4;

    public InvalidCodeException() {
        super("Code non valide");
    }
}
