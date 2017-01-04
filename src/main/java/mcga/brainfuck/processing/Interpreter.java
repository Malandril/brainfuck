package mcga.brainfuck.processing;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;
import mcga.brainfuck.instructions.Instruction;
import mcga.brainfuck.instructions.Jump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public int index=1;
    public int size;
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
     * @throws FileNotFoundException
     */
    public Interpreter(String fileName) throws FileNotFoundException {
        super(fileName);
        instructionsStack.push(new ArrayList<>());
    }

    /**
     * Constructor defining the stream in parameter as the input stream.
     *
     * @param stream inputStream
     * @see Parser(InputStream)
     */
    public Interpreter(FileInputStream stream) {
        super(stream);
    }


    /**
     * Overrides the method of the Parser class to interpret the list of commands
     *
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile() {
        super.parseFile();
        if (!Jump.isJumpStackEmpty()) {
            throw new InvalidCodeException();
        }
        double startTime = System.nanoTime();
        interpretList(instructionsStack.peek());
        double endTime = System.nanoTime();
        Metrics.setExecTime((endTime - startTime) * Math.pow(10, -6));
        Metrics.printMetrics();
        System.out.println(Brainfuck.getMemory());

    }

    /**
     * Interpret each command between the two size start and end
     */
    public void interpretList(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            interpretation(instruction);
        }
    }

    /**
     * Interpret the current command of the list and modify the metrics corresponding
     */
    public void interpretation(Instruction instruction) {
        try {
            instruction.interpret();
            Metrics.incrExecPos(1);
            Metrics.setExecMove(Metrics.getExecMove() + 1);
        } catch (InvalidValueException e) {
            System.err.println(e.getMessage());
            System.exit(InvalidValueException.EXIT_CODE);
        } catch (MyIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.exit(MyIndexOutOfBoundsException.EXIT_CODE);
        }

    }


    /**
     * This method overrides Parser#execute called in Parser#parseFile so that it creates the Instruction
     * corresponding to the String in parameter and interprets it.
     *
     * @param str String corresponding to the Instruction
     * @throws InvalidInstructionException
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) throws InvalidInstructionException {
        instructionsStack.peek().add(InstructionCreator.createInstruction(str));
        index++;
        if (RIGHT.isIdentifier(str)) {
            size++;
        }
    }

    public void pushInstructions(List<Instruction> item) {
        instructionsStack.push(item);
    }

    public List<Instruction> popInstructions() {
        return instructionsStack.pop();
    }


    public int readProcedureText(String str) {
        int prevIndex = size;
        super.readText(str);
        return size + 1 - prevIndex;
    }
}
