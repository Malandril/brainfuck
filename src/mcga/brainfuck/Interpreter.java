package mcga.brainfuck;

import java.io.InputStream;

/**
 * This class extends Parser. It rewrites the methods from the superclass to interpret the parsed actions.
 */
public class Interpreter extends Parser {

    /**
     * Default constructor of the class.
     * @see Parser#Parser()
     */
    public Interpreter() {
        super();
    }

    /**
     * Constructor defining the stream in parameter as the input stream.
     * @param stream
     * @see Parser#Parser(InputStream)
     */
    public Interpreter(InputStream stream) {
        super(stream);
    }

    /**
     * Calls Parser#parseFile
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile() {
        super.parseFile();
    }

    /**
     * This method overrides Parser#execute called in Parser#parseFile so that it creates the Instruction
     * corresponding to the String in parameter and interprets it.
     * @param str String corresponding to the Instruction
     * @throws InvalidInstructionException
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) throws InvalidInstructionException {
        try {
            InstructionFactory.createInstruction(str).interpret();
        } catch (InvalidValueException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }
    }


}
