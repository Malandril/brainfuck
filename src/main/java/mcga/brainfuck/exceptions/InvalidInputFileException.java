package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 07/01/2017.
 */
public class InvalidInputFileException extends InstructionException {
    private static final int EXIT_CODE = 3;
    
    public InvalidInputFileException() {
        super();
    }
    
    public InvalidInputFileException(String message) {
        super(message);
    }
    
    @Override
    public int getExitCode() {
        return EXIT_CODE;
    }
}
