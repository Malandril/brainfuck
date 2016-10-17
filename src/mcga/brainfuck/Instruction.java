package mcga.brainfuck;

/**
 * Created by user on 06/10/2016.
 */
public interface Instruction {
    void interpret() throws InvalidValueException, IndexOutOfBoundsException;
}
