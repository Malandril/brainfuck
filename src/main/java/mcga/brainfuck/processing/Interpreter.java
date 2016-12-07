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
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends Parser. It rewrites the methods from the superclass to interpret the parsed actions.
 *
 * @author Team Make Coding Great Again
 */

public class Interpreter extends Parser {

    public List<Instruction> mainInstructions = new ArrayList<>();
    public List<Instruction> instructions = mainInstructions;


    /**
     * Default constructor of the class.
     *
     * @see Parser#Parser()
     */
    public Interpreter() {
        super();
    }

    /**
     * Constructor with a file name
     *
     * @param fileName String file to interpret
     * @throws FileNotFoundException
     */
    public Interpreter(String fileName) throws FileNotFoundException {
        super(fileName);
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
        interpretList(mainInstructions);
        double endTime = System.nanoTime();
        Metrics.setExecTime((endTime - startTime) * Math.pow(10, -6));
        Metrics.printMetrics();
        System.out.println(Brainfuck.getMemory());

    }

    /**
     * Interpret each command between the two index start and end
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
//                Metrics.setExecPos(i + 1);
            Metrics.setExecMove(Metrics.getExecMove() + 1);
        } catch (InvalidValueException e) {
            e.printStackTrace();
        } catch (MyIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public Instruction getInstruction(int i) {
        return instructions.get(i);
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
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
        instructions.add(InstructionCreator.createInstruction(str));
    }
}
