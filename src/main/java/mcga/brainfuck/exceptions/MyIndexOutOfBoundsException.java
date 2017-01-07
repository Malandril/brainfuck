package mcga.brainfuck.exceptions;

import mcga.brainfuck.Memory;

/**
 * This exception is called if the pointer of the memory points on a cell not in the possible values :
 * negative size or over 30 000
 */
public class MyIndexOutOfBoundsException extends MyException {
    private static final int EXIT_CODE = 2;
    
    public MyIndexOutOfBoundsException(String s) {
        super(s);
    }
    
    public MyIndexOutOfBoundsException(int i) {
        super("invalid Index: " + i + " ,value must be between " + 0 + " and " + Memory.MAX_SIZE);
    }
    
    @Override
    public int getExitCode() {
        return EXIT_CODE;
    }
}
