package mcga.brainfuck;

/**
 * Class defining the action corresponding to the shift of the memory pointer to the right.
 * This class, as well as the Left class, extends Movement, as they both define a shift of the
 * memory pointer in a Brainf*ck program.
 */
public class Right extends Movement {

    /**
     * Overrides the method defined in the Instruction interface to shift the memory pointer to the right.
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws IndexOutOfBoundsException {
        moveR();
    }

    /**
     * Defines the actions of shifting the memory pointer to the right.
     * @throws IndexOutOfBoundsException
     */
    private void moveR() throws IndexOutOfBoundsException {
        Brainfuck.memory.changeCurrentIndex(1);
    }

}
