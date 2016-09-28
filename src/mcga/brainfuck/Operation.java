package mcga.brainfuck;

/**
 * Created by user on 26/09/2016.
 */
public class Operation {

    Memory memory = new Memory();

    public Operation() {
    }

    public void incrementation() {
        memory.addCurrentCellValue(1);
    }

    public void decrementation() {
        memory.addCurrentCellValue(-1);
    }

    public void moveR() {
        memory.changeCurrentPointerValue(1);
    }

    public void moveL() {
        memory.changeCurrentPointerValue(-1);
    }
}
