package mcga.brainfuck;

import java.io.InputStream;

/**
 * Created by user on 12/10/2016.
 */
public class Interpreter extends Parser {
    public Interpreter() {
        super();
    }

    public Interpreter(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public void execute(String str) throws InvalidInstructionException {
        try {
            InstructionFactory.getInstruction(str).interpret();
        } catch (InvalidValueException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }
    }
}
