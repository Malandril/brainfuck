package mcga.brainfuck;

import java.io.InputStream;
import java.io.PrintStream;

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
            printStream.println("Exec step => " + Integer.toString(Parser.EXEC_MOVE + 1)
                    + '\t' + '\t' + "Data pointer loc => " + Brainfuck.memory.getCurrentIndex()
                    + '\t' + '\t' + "Memory => " + Brainfuck.memory);
            InstructionFactory.createInstruction(str).interpret();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
