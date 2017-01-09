package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 07/01/2017.
 */
public class InvalidCodeException extends BrainfuckException {
    private static final int EXIT_CODE = 4;
    
    public InvalidCodeException() {
        super();
    }
    
    public InvalidCodeException(String message) {
        super(message);
    }
    
    public InvalidCodeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidCodeException(Throwable cause) {
        super(cause);
    }
    
    public InvalidCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    @Override
    public int getExitCode() {
        return EXIT_CODE;
    }
}
