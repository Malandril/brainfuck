package mcga.brainfuck.processing;

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

    public int ignoredUntilIndex = 0;
    private List<Instruction> instructions = new ArrayList<>();
    private int index = 0;
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

    public int getIndex() {
        return index;
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
        interpretList(0, instructions.size());
    }

    /**
     * Interpret each command between the two index start and end
     *
     * @param start int representing the beginning of the list to interpret
     * @param end int representing the end of the list to interpret
     */
    public void interpretList(int start, int end) {
        for (int i = start; i < end; i++) {
            interpretation(i);
        }
    }

    /**
     * Interpret the current command of the list and modify the metrics corresponding
     *
     * @param i index of the current command in the list
     */
    public void interpretation(int i) {
        try {
            if (i >= ignoredUntilIndex) {
                ignoredUntilIndex = 0;
                instructions.get(i).interpret();
                Metrics.setExecPos(i + 1);
                Metrics.setExecMove(Metrics.getExecMove() + 1);
            }
        } catch (MyIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.exit(MyIndexOutOfBoundsException.EXIT_CODE);
        } catch (InvalidValueException e) {
            System.err.println(e.getMessage());
            System.exit(InvalidValueException.EXIT_CODE);
        }
    }

    public Instruction getInstruction(int i) {
        return instructions.get(i);
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
        index = instructions.size();
        instructions.add(InstructionCreator.createInstruction(str));
    }


}
