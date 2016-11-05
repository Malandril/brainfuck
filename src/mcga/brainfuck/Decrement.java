package mcga.brainfuck;

/**
 * Class defining the action corresponding to the decrementation of the value of a cell.
 * This class, as well as the Increment class, extends Operation, as they both define a
 * modification of a cell value in a Brainf*ck program.
 */
public class Decrement extends Operation {

    /**
     * Overrides the method defined in the Instruction interface to execute the decrement action.
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InvalidValueException {
        super.interpret();
        decrementation();
    }

    /**
     * Defines the action of a decrement instruction.
     * @throws InvalidValueException
     */
    private void decrementation() throws InvalidValueException {
        Brainfuck.memory.addCurrentCellValue(-1);
    }

}
