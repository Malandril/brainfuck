package mcga.brainfuck.processing;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.instructions.Instruction;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Class defining the actions to do when the user wants to trace the execution of his program
 * thanks to the metrics.
 *
 * @author Team Make Coding Great Again
 */
public class Trace extends Interpreter {
    private PrintStream logFile = System.out;

    /**
     * Empty constructor
     */
    public Trace() {
        super();
    }

    /**
     * Constructor of the Trace class using the constructor of the Interpreter class
     *
     * @param fileName String file to interpret
     * @throws FileNotFoundException if the file is not found
     */
    public Trace(String fileName, String logFileName) throws FileNotFoundException {
        super(fileName);
        logFile = new PrintStream(logFileName);
    }

    /**
     * Prints the metric values for the Trace option
     */
    public void logMetrics() {
        logFile.println("Exec step => " + Metrics.getExecPos() + '\t' + '\t' + "Data pointer loc => " + Brainfuck.getMemory().getCurrentIndex() + '\t' + '\t' + "Memory => " + Brainfuck.getMemory());
    }

    /**
     * Overrides the method of the Interpreter class to parse the file
     *
     * @see Interpreter#parseFile()
     */
    @Override
    public void parseFile() {
        super.parseFile();
    }

    /**
     * Overrides the method of the Interpreter class to interpret the commands and write the metrics
     * in the log at the same time.
     *
     * @param instruction size of the command to interpret
     * @see Interpreter#interpretation(Instruction)
     */
    @Override
    public void interpretation(Instruction instruction) {
        super.interpretation(instruction);
        logMetrics();
    }
}
