package mcga.brainfuck;

import java.io.InputStream;

/**
 * Created by user on 12/10/2016.
 */
public class Interpreter extends Parser{
    public Interpreter() {
        super();
    }

    public Interpreter(InputStream stream) {
        this.stream = stream;
    }
    @Override
    public void execute(String str) {
        InstructionFactory.getInstruction(str).interpret();
    }
}
