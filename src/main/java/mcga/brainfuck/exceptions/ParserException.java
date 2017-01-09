package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 07/01/2017.
 */
public abstract class ParserException extends BrainfuckException {
    public ParserException() {
    }
    
    public ParserException(String message) {
        super(message);
    }
    
    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ParserException(Throwable cause) {
        super(cause);
    }
    
    public ParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
