package mcga.brainfuck;

/**
 * Created by user on 06/10/2016.
 */
public class Left extends Movement {

    @Override
    public void interpret() {
        moveL();
    }

    private void moveL() {
        Brainfuck.memoire.changeCurrentIndex(-1);
    }

}
