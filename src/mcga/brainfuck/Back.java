package mcga.brainfuck;

/**
 * Class defining the action corresponding to the end of a loop.
 * This class, as well as the Jump class, extends Loop, as they both define a loop in a Brainf*ck program.
 */
public class Back extends Loop {

    public Back() {
        index = interpreter.getIndex();
        boundLoopIndex = Jump.popJumpStack().index;
        ((Loop) interpreter.getInstruction(boundLoopIndex)).boundLoopIndex = index;
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the back action.
     *
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException {
        super.interpret();
        back();
    }

    /**
     * Defines the actions of a back instruction.
     */
    private void back() throws InvalidValueException {
        while (Brainfuck.memory.getCurrentCellValue() != 0) {
            interpreter.interpretList(boundLoopIndex, index);
        }
    }
}
