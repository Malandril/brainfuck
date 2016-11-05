package mcga.brainfuck;

import java.io.InputStream;
import java.io.PrintStream;

import static mcga.brainfuck.Interpreter.instructions;

/**
 * Created by user on 02/11/2016.
 */
public class Trace extends Parser {

    public static PrintStream printStream;


    public Trace(InputStream stream, PrintStream printStream) {
        super(stream);
        this.printStream = printStream;
    }

    @Override
    public void parseFile() {
        super.parseFile();
    }

    @Override
    public void execute(String str) throws InvalidInstructionException {
        try {
            if (Parser.EXEC_MOVE > 0) {
                printStream.println("Exec step => " + Integer.toString(Parser.EXEC_MOVE)
                        + '\t' + '\t' + "Data pointer loc => " + Brainfuck.memory.getCurrentIndex()
                        + '\t' + '\t' + "Memory => " + Brainfuck.memory);
            }
            InstructionFactory.createInstruction(str).interpret();
            instructions.add(InstructionFactory.createInstruction(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
