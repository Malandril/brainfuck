package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 20/11/2016.
 */
public class InvalidCodeException extends Exception{
    public static final int EXIT_CODE=4;

    public InvalidCodeException() {
        super("Code non valide");
    }
}
