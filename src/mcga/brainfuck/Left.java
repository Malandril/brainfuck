package mcga.brainfuck;

/**
 * Created by user on 06/10/2016.
 */
public class Left extends Movement {

    @Override
    public void interpret() throws InvalidValueException {
        moveL();
    }

    private void moveL() throws InvalidValueException {
        Brainfuck.memoire.changeCurrentIndex(-1);
    }

}
