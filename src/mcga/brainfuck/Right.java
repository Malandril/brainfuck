package mcga.brainfuck;

/**
 * Created by user on 06/10/2016.
 */
public class Right extends Movement {
    @Override
    public void interpret() {
        moveR();
    }

    private void moveR() {
        Brainfuck.memoire.changeCurrentIndex(1);
    }

}
