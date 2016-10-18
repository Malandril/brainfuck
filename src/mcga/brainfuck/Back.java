package mcga.brainfuck;

/**
 * Created by user on 18/10/2016.
 */
public class Back extends Loop {
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        back();
    }

    private void back() {
        int value = Brainfuck.memoire.getCurrentCellValue();
    }
}
