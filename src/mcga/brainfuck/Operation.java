package mcga.brainfuck;

/**
 * Created by user on 26/09/2016.
 */
public class Operation {

    //Memory memory = new Memory();

    public Operation() {
    }

    public void incrementation() {
        Brainfuck.memoire.addCurrentCellValue(1);
    }

    public void decrementation() {
        Brainfuck.memoire.addCurrentCellValue(-1);
    }

    public void moveR() {
        Brainfuck.memoire.changeCurrentIndex(1);
    }

    public void moveL() {
        Brainfuck.memoire.changeCurrentIndex(-1);
    }

}
