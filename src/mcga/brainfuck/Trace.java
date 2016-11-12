package mcga.brainfuck;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by user on 02/11/2016.
 */
public class Trace extends Interpreter {

    public PrintStream logFileStream;


    public Trace(String fileName) throws FileNotFoundException {
        super(fileName);
        logFileStream = logFileStream;
    }

    @Override
    public void parseFile() {
        super.parseFile();
    }

    @Override
    public void  interpretation(int i){
        super.interpretation(i);
        Metrics.logMetrics();
    }
}
