package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 20/11/2016.
 */
public class MyIndexOutOfBoundsException extends Exception {
    public static final int EXIT_CODE=2;
    public MyIndexOutOfBoundsException(String s) {
        super(s);
    }
}
