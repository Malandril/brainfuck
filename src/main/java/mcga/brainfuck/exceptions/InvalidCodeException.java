package mcga.brainfuck.exceptions;


/**
 * This exception is called if the parenthesis is not valid
 */
public class InvalidCodeException extends ParserException {
    private static final int EXIT_CODE = 4;
    
    public InvalidCodeException() {
        super();
    }
    
    public InvalidCodeException(String message) {
        super(message);
    }
    
    @Override
    public int getExitCode() {
        return EXIT_CODE;
    }
}
