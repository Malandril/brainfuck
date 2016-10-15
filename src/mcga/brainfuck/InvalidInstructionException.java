package mcga.brainfuck;

/**
 * Created by Thomas on 12/10/2016.
 */
public class InvalidInstructionException extends RuntimeException {
    public InvalidInstructionException(String message) {
        super(message);
        System.err.println("Valeur Invalide: "+message);
        System.exit(42);
    }
}
