package mcga.brainfuck.exceptions;

import mcga.brainfuck.Metrics;

/**
 * This exception is called if the parenthesis is not valid
 */
public class InvalidCodeException extends RuntimeException {
    public static final int EXIT_CODE = 4;

    public InvalidCodeException() {
        System.err.println("Code Invalide " + Metrics.getProgSize());
        System.exit(EXIT_CODE);
    }

    public InvalidCodeException(String str) {
        System.err.println("Code Invalide: " + str);
        System.exit(EXIT_CODE);
    }
}
