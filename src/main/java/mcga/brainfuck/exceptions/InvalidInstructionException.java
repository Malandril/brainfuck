package mcga.brainfuck.exceptions;

/**
 * This exception is called if the instruction written in the file doesn't correspond to a Brainf*ck instruction.
 */
public class InvalidInstructionException extends InvalidCodeException {
    
    private static final int EXIT_CODE = 42;

    /**
     * Calls the superclass constructor with the message in parameter.
     * @param message String to display when the exception is thrown.
     * @see Exception#Exception()
     */
    public InvalidInstructionException(String message) {
        super("Instruction Invalide: " + message);
    }
    
    public InvalidInstructionException() {
        super("Instruction Invalide");
    }
    
    @Override
    public int getExitCode() {
        return EXIT_CODE;
    }
}
