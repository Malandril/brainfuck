package mcga.brainfuck;

/**
 * Created by Thomas on 12/10/2016.
 */
public class InvalidInstructionException extends Exception {
    public InvalidInstructionException(String message) {
        super("Instruction Invalide: " + message);
    }
}
