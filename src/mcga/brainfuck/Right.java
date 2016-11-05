package mcga.brainfuck;

/**
 * Class defining the action corresponding to the shift of the memory pointer to the right.
 * This class, as well as the Left class, extends Movement, as they both define a shift of the
 * memory pointer in a Brainf*ck program.
 */
public class Right extends Movement {

    /**
     * Overrides the method defined in the Instruction interface to shift the memory pointer to the right.
     *
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException {
        super.interpret();
        moveR();
    }

    /**
     * Defines the actions of shifting the memory pointer to the right.
     *
     * @throws
     */
    private void moveR() {
        Brainfuck.memory.changeCurrentIndex(1);
    }

}
