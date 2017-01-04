package mcga.brainfuck.exceptions;

/**
 * This exception is called if the pointer of the memory points on a cell not in the possible values :
 * negative size or over 30 000
 */
public class MyIndexOutOfBoundsException extends Exception {
    public static final int EXIT_CODE = 2;

    public MyIndexOutOfBoundsException(String s) {
        super(s);
    }
}
