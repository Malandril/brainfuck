package mcga.brainfuck;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class extends Parser. It rewrites the methods from the superclass to interpret the parsed actions.
 */

public class Interpreter extends Parser {

    //Trace trace = new Trace();


    public static int ignoredUntilIndex = 0;
    /**
     * Default constructor of the class.
     *
     * @see Parser#Parser()
     */
    private static List<Instruction> instructions = new ArrayList<>();
    private static Stack<Jump> jumpIndexStack = new Stack<>();

    public Interpreter() {
        super();
    }

    /**
     * Constructor defining the stream in parameter as the input stream.
     *
     * @param stream
     * @see Parser#Parser(InputStream)
     */
    public Interpreter(InputStream stream) {
        super(stream);
    }

    public static List<Instruction> getInstructions() {
        return instructions;
    }

    public static Stack<Jump> getJumpIndexStack() {
        return jumpIndexStack;
    }

    @Override
    public void parseFile() {
        super.parseFile();
        for (int i = 0; i < instructions.size(); i++) {
            interpretation(i);
        }
        Parser.PROG_SIZE = instructions.size();
    }

    /**
     * This method overrides Parser#execute called in Parser#parseFile so that it creates the Instruction
     * corresponding to the String in parameter and interprets it.
     *
     * @param str String corresponding to the Instruction
     * @throws InvalidInstructionException
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) throws InvalidInstructionException {
        instructions.add(InstructionFactory.createInstruction(str));
    }

    public void interpretation(int i) {
        try {
            if (i >= ignoredUntilIndex) {
                ignoredUntilIndex = 0;
                instructions.get(i).interpret();
                EXEC_POS = i + 1;
                EXEC_MOVE++;
            }
        } catch (InvalidValueException e) {
            e.printStackTrace();
        }
    }


}
