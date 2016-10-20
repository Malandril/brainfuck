package mcga.brainfuck;

/**
 * This exception is called if the memory index exceeds the size of the memory or becomes negative.
 */
public class MyIndexOutOfBoundsException extends IndexOutOfBoundsException {

    /**
     * Calls the superclass constructor.
     * @see IndexOutOfBoundsException#IndexOutOfBoundsException()
     */
    public MyIndexOutOfBoundsException() {
        super();
    }

    /**
     * Calls the superclass constructor with a message in parameter.
     * @param str message to display
     * @see IndexOutOfBoundsException#IndexOutOfBoundsException(String)
     */
    public MyIndexOutOfBoundsException(String str) {
        super(str);
        System.err.println("Index out of Bound " + str);
        System.exit(2);
    }


}
