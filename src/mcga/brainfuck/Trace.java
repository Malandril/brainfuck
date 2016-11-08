package mcga.brainfuck;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by user on 02/11/2016.
 */
public class Trace extends Interpreter {

    public static PrintStream logFileStream;


    public Trace(InputStream stream, PrintStream logFileStream) {
        super(stream);
        this.logFileStream = logFileStream;
    }

    @Override
    public void parseFile() {
        super.parseFile();
    }

    @Override
    public void interpretation(int i){
        super.interpretation(i);
            logFileStream.println("Exec step => " + Long.toString(Parser.EXEC_POS)
                    + '\t' + '\t' + "Data pointer loc => " + Brainfuck.memory.getCurrentIndex()
                    + '\t' + '\t' + "Memory => " + Brainfuck.memory);


    }
}
