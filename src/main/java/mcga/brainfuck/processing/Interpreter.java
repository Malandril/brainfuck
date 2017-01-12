package mcga.brainfuck.processing;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InstructionException;
import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.instructions.Instruction;
import mcga.brainfuck.instructions.Jump;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static mcga.brainfuck.InstructionCreator.RIGHT;

/**
 * This class extends Parser. It rewrites the methods from the superclass to interpret the parsed actions.
 *
 * @author Team Make Coding Great Again
 */

public class Interpreter extends Parser {
    private int index = 1;
    private int size;
    private Deque<List<Instruction>> instructionsStack = new ArrayDeque<>();
    
    /**
     * Default constructor of the class.
     *
     * @see Parser#Parser()
     */
    public Interpreter() {
        super();
        instructionsStack.push(new ArrayList<>());
    }
    
    /**
     * Constructor with a file name
     *
     * @param fileName String file to interpret
     * @throws FileNotFoundException if the file is not found
     */
    public Interpreter(String fileName) throws FileNotFoundException {
        super(fileName);
        instructionsStack.push(new ArrayList<>());
    }
    
    
    /**
     * Sets the toString to print at the end of the execution of the program.
     */
    public void printMetrics() {
        System.out.println("\nPROG_SIZE = " + Metrics.getProgSize() + '\n' + "EXEC_TIME = " + Metrics.getExecTime() + " ms" + '\n' + "EXEC_MOVE = " + Metrics.getExecMove() + '\n' + "DATA_MOVE = " + Metrics.getDataMove() + '\n' + "DATA_READ = " + Metrics.getDataRead() + '\n' + "DATA_WRITE = " + Metrics.getDataWrite() + '\n');
    }
    
    /**
     * Overrides the method of the Parser class to interpret the list of commands.
     *
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile() throws InvalidCodeException {
        super.parseFile();
        if (!Jump.isJumpStackEmpty()) {
            throw new InvalidCodeException();
        }
        double startTime = System.nanoTime();
        try {
            interpretList(instructionsStack.peek());
        } catch (InstructionException e) {
            System.err.println(e.getMessage());
            System.exit(e.getExitCode());
        }
        double endTime = System.nanoTime();
        Metrics.setExecTime((endTime - startTime) * Math.pow(10, -6));
        System.out.println("\n\n"+Brainfuck.getMemory());
        printMetrics();
    }
    
    /**
     * Interprets each command between the two size start and end.
     */
    public void interpretList(List<Instruction> instructions) throws InstructionException {
        for (Instruction instruction : instructions) {
            interpretation(instruction);
        }
    }
    
    /**
     * Interprets the current command of the list and modifies the metrics corresponding.
     */
    public void interpretation(Instruction instruction) throws InstructionException {
        instruction.interpret();
        Metrics.incrExecPos(1);
        Metrics.setExecMove(Metrics.getExecMove() + 1);
        
    }
    
    
    /**
     * This method overrides {@link Parser#execute(String) execute} called in {@link Parser#parseFile() parseFile}
     * so that it creates the Instruction corresponding to the String in parameter and adds it to the top List of
     * instructionsStack.
     *
     * @param str String corresponding to the Instruction
     * @throws InvalidInstructionException if the instruction is invalid
     */
    @Override
    public void execute(String str) throws InvalidCodeException {
        instructionsStack.peek().add(InstructionCreator.createInstruction(str));
        index++;
        if (RIGHT.isIdentifier(str)) {
            size++;
        }
    }

    /**
     * Pushes the List of instructions in parameter in the instructionsStack dequeue.
     * @param item List of instructions
     */
    public void pushInstructions(List<Instruction> item) {
        instructionsStack.push(item);
    }

    /**
     * Pops the instructionsStack dequeue.
     * @return List of instructions
     */
    public List<Instruction> popInstructions() {
        return instructionsStack.pop();
    }

    /**
     * Reads the code of the procedure and calculates its size in the memory.
     * @param str Code of the procedure
     * @return size of the procedure in the memory
     */
    public int readProcedureText(String str) throws InvalidCodeException {
        int prevIndex = size;
        super.readText(str);
        return size + 1 - prevIndex;
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getSize() {
        return size;
    }
}
