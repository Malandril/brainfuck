package mcga.brainfuck.exceptions;

import mcga.brainfuck.Memory;

/**
 * This exception is called if the pointer of the memory points on a cell not in the possible values :
 * negative size or over 30 000
 */
public class BrainfuckIndexOutOfBoundsException extends InstructionException {
    private static final int EXIT_CODE = 2;
    private int index = 0;
    
    public BrainfuckIndexOutOfBoundsException(String s) {
        super(s);
    }
    
    
    public BrainfuckIndexOutOfBoundsException(BrainfuckIndexOutOfBoundsException e, int min, int max, String msg) {
        super("invalid Index: " + e.index + " ,value must be between " + min + " and " + max + " " + msg);
    }
    public BrainfuckIndexOutOfBoundsException(int i) {
        super("invalid Index: " + i + " ,value must be between " + 0 + " and " + Memory.MAX_SIZE);
    }
    
    @Override
    public int getExitCode() {
        return EXIT_CODE;
    }
    
}
