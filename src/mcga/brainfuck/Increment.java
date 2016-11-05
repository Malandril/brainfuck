package mcga.brainfuck;

/**
 * Class defining the action corresponding to the incrementation of the value of a cell.
 * This class, as well as the Decrement class, extends Operation, as they both define a
 * modification of a cell value in a Brainf*ck program.
 */
public class Increment extends Operation {

    /**
     * Overrides the method defined in the Instruction interface to execute the increment action.
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InvalidValueException {
        super.interpret();
        incrementation();
    }

    /**
     * Defines the action of an increment instruction.
     * @throws InvalidValueException
     */
    private void incrementation() throws InvalidValueException {
        Brainfuck.memory.addCurrentCellValue(1);
    }

}
