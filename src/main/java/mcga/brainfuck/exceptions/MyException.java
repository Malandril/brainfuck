package mcga.brainfuck.exceptions;

/**
 * Created by Thomas on 07/01/2017.
 */
public abstract class MyException extends Exception {
    public MyException() {
        super();
    }
    
    public MyException(String message) {
        super(message);
    }
    
    public abstract int getExitCode();
}
