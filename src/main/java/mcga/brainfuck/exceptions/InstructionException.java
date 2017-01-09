package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 07/01/2017.
 */
public abstract class InstructionException extends BrainfuckException {
    public InstructionException() {
    }
    
    public InstructionException(String message) {
        super(message);
    }
    
    public InstructionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InstructionException(Throwable cause) {
        super(cause);
    }
    
    public InstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
