package mcga.brainfuck;

/**
 * Created by user on 06/10/2016.
 */
public class Right extends Movement {
    @Override
    public void interpret() throws IndexOutOfBoundsException {
        moveR();
    }

    private void moveR() throws IndexOutOfBoundsException {
        Brainfuck.memoire.changeCurrentIndex(1);
    }

}
