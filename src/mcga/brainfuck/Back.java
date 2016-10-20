package mcga.brainfuck;

/**
 * Class defining the action corresponding to the end of a loop.
 * This class, as well as the Jump class, extends Loop, as they both define a loop in a Brainf*ck program.
 */
public class Back extends Loop {

    /**
     * Overrides the method defined in the Instruction interface to execute the back action.
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        back();
    }

    /**
     * Defines the actions of a back instruction.
     */
    private void back() {
        int value = Brainfuck.memory.getCurrentCellValue();
    }
}
