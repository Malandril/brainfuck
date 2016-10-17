package mcga.brainfuck;

/**
 * Created by user on 06/10/2016.
 */
public class Decrement extends Operation {
    @Override
    public void interpret() throws InvalidValueException {
        decrementation();
    }

    private void decrementation() throws InvalidValueException {
        Brainfuck.memoire.addCurrentCellValue(-1);
    }

}
