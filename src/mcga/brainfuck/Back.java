package mcga.brainfuck;

/**
 * Class defining the action corresponding to the end of a loop.
 * This class, as well as the Jump class, extends Loop, as they both define a loop in a Brainf*ck program.
 */
public class Back extends Loop {
    public Back() {
        index = Interpreter.getInstructions().size();
        boundLoopIndex = Interpreter.getJumpIndexStack().pop().index;
        ((Loop) Interpreter.getInstructions().get(boundLoopIndex)).boundLoopIndex = index;
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
            for (int i = boundLoopIndex + 1; i < index; i++) {
                if (i >= Interpreter.ignoredUntilIndex) {
                    Interpreter.ignoredUntilIndex = 0;
                    Interpreter.getInstructions().get(i).interpret();
                }
                Parser.EXEC_POS =i+1;
                Parser.EXEC_MOVE++;
                Trace.logFileStream.println("Exec step => " + Integer.toString(Parser.EXEC_POS)
                        + '\t' + '\t' + "Data pointer loc => " + Brainfuck.memory.getCurrentIndex()
                        + '\t' + '\t' + "Memory => " + Brainfuck.memory);
            }
        }
    }
}
