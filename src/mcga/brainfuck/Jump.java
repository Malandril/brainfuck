package mcga.brainfuck;

/**
 * Created by user on 18/10/2016.
 */
public class Jump extends Loop {
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        jump();
    }

    private void jump() {
        int value = Brainfuck.memoire.getCurrentCellValue();

    }
}
