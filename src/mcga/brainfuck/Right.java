package mcga.brainfuck;

/**
 * Created by user on 06/10/2016.
 */
public class Right extends Movement {
    @Override
    public void interpret() throws IndexOutOfBoundsException, InvalidValueException {
        moveR();
    }

    private void moveR() throws IndexOutOfBoundsException, InvalidValueException {
        Brainfuck.memoire.changeCurrentIndex(1);
    }

}
