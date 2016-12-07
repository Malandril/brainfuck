package mcga.brainfuck.exceptions;

/**
 * This exception is called if the bitmap structure is invalid :
 * <ul>
 * <li>The image height or length isn't a multiple of 3.</li>
 * </ul>
 */
public class InvalidBitmapException extends Exception {
    public static final int EXIT_CODE = 14;
}
