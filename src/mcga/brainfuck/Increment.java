package mcga.brainfuck;

/**
 * Created by user on 28/09/2016.
 */
public class Increment extends Operation {

    @Override
    public void interpret() throws InvalidValueException {
        incrementation();
    }

    private void incrementation() throws InvalidValueException {
        Brainfuck.memoire.addCurrentCellValue(1);
    }

}
