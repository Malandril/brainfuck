package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 07/01/2017.
 */
public class InvalidParametersException extends InvalidCodeException {
    public static final int EXIT_CODE = 2501;
    
    public InvalidParametersException() {
        super("Invalid Parameters");
    }
    
    public InvalidParametersException(String message) {
        super("Invalid Parameters in Function: " + message);
    }
    
    @Override
    public int getExitCode() {
        return EXIT_CODE;
    }
}
