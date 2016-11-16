package mcga.brainfuck.processing;

import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.instructions.Instruction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends Parser. It rewrites the methods from the superclass to interpret the parsed actions.
 */

public class Interpreter extends Parser {

    //Trace trace = new Trace();

    public int ignoredUntilIndex = 0;
    private int index = 0;
    /**
     * Default constructor of the class.
     *
     * @see Parser#Parser()
     */
    private List<Instruction> instructions = new ArrayList<>();

    public Interpreter() {
        super();
    }

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


    @Override
    public void parseFile() {
        super.parseFile();
        interpretList(0, instructions.size());
    }

    public void interpretList(int start, int end) {
        for (int i = start; i < end; i++) {
            interpretation(i);
        }
    }

    public void interpretation(int i) {
        try {
            if (i >= ignoredUntilIndex) {
                ignoredUntilIndex = 0;
                instructions.get(i).interpret();
                Metrics.setExecPos(i + 1);
                Metrics.setExecMove(Metrics.getExecMove() + 1);
            }
        } catch (InvalidValueException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Instruction getInstruction(int i){
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
        index=instructions.size();
        instructions.add(InstructionCreator.createInstruction(str));
    }


}
