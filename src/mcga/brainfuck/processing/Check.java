package mcga.brainfuck.processing;

import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidInstructionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class defining the actions to do when the user wants to check if the Brainf*ck code is correctly written.
 *
 * @author Team Make Coding Great Again
 */
public class Check extends Parser {
    private long count = 0;

    /**
     * Constructor with the name of the file
     *
     * @param fileName file name of the file to check
     * @throws FileNotFoundException
     */
    public Check(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Constructor with a FileInputStream
     *
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public Check(FileInputStream stream) {
        super(stream);
    }

    /**
     * Overrides the main class method so that after its execution and the number of brackets counted,
     * it determines depending on count's value if the code is correctly written.
     *
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile() {
        super.parseFile();
        checkCount();

    }

    /**
     * Overrides the main class method so that after its execution and the number of pixels corresponding to a
     * jump or back action counted, it determines depending on count's value if the code is correctly written.
     *
     * @see Parser#readBitmap()
     */
    @Override
    public void readBitmap() {
        super.readBitmap();
        checkCount();
    }

    /**
     * This method overrides Parser#execute called in Parser#parseFile so that it only counts the number of opening
     * and closing brackets.
     *
     * @param str String corresponding to an instruction
     * @throws InvalidInstructionException
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) throws InvalidInstructionException {
        InstructionCreator instr = InstructionCreator.hasInstruction(str);
        switch (instr) {
            case JUMP:
                count++;
                break;
            case BACK:
                count--;
                break;
        }
        if (count < 0) {
            try {
                throw new InvalidCodeException();
            } catch (InvalidCodeException e) {
                System.err.println(e.getMessage());
                System.exit(e.EXIT_CODE);
            }
        }
    }

    /**
     * Checks the value of the count to determine if the code is well written or not.
     * If the count is different from zero, the program exits with the error code 4.
     */
    public void checkCount(){
        try {
            if (this.count != 0) {
                throw new InvalidCodeException();
            }
        } catch (InvalidCodeException e) {
            System.err.println(e.getMessage());
            System.exit(e.EXIT_CODE);
        }
    }
}