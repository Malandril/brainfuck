package mcga.brainfuck.processing;

import mcga.brainfuck.Metrics;

import java.io.FileNotFoundException;

/**
 * Class defining the actions to do when the user wants to trace the execution of his program
 * thanks to the metrics.
 * @author Team Make Coding Great Again
 */
public class Trace extends Interpreter {

    public Trace() {
        super();
    }

    /**
     * Constructor of the Trace class using the constructor of the Interpreter class
     * @param fileName String file to interpret
     * @throws FileNotFoundException
     */
    public Trace(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Overrides the method of the Interpreter class to parse the file
     * @see Interpreter#parseFile()
     */
    @Override
    public void parseFile() {
        super.parseFile();
    }

    /**
     * Overrides the method of the Interpreter class to interpret the commands and write the metrics
     * in the log at the same time
     * @param i index of the command to interpret
     * @see Interpreter#interpretation(int)
     */
    @Override
    public void interpretation(int i) {
        super.interpretation(i);
        Metrics.logMetrics();
    }
}
