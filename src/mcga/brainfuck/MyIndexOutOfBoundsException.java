package mcga.brainfuck;

/**
 * Created by user on 29/09/2016.
 */
public class MyIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public MyIndexOutOfBoundsException() {
        super();
        System.exit(2);
    }


}
