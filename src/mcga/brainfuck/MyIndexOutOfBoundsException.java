package mcga.brainfuck;

/**
 * Created by user on 29/09/2016.
 */
public class MyIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public MyIndexOutOfBoundsException() {
        super();
        System.exit(2);
    }

    public MyIndexOutOfBoundsException(String str) {
        super(str);
        System.err.println("index out of Bound " + str);
        System.exit(2);
    }


}