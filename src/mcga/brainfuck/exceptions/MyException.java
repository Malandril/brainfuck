package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 20/11/2016.
 */
public class MyException extends Exception {
    public static final int EXIT_CODE = 0;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

}
