package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 07/01/2017.
 */
public abstract class BrainfuckException extends Exception {
    public BrainfuckException() {
        super();
    }
    
    public BrainfuckException(String message) {
        super(message);
    }
    
    public BrainfuckException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BrainfuckException(Throwable cause) {
        super(cause);
    }
    
    public BrainfuckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public abstract int getExitCode();
}
