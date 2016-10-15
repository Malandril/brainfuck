package mcga.brainfuck;

import java.util.ArrayList;
import java.util.List;

/**
 * Creasted by user on 28/09/2016.
 */
public class Increment extends Operation {

    @Override
    public void interpret() {
        incrementation();
    }

    private void incrementation() {
        Brainfuck.memoire.addCurrentCellValue(1);
    }

}
