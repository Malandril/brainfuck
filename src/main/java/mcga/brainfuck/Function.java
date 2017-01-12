package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;
import mcga.brainfuck.instructions.Instruction;
import mcga.brainfuck.instructions.Procedure;

import java.util.List;

import static mcga.brainfuck.Brainfuck.getMemory;

/**
 * This class represents a function.
 */
public class Function extends Procedure {

    /**
     * Constructor of the Function class
     * @param name name of the function
     * @param instructions instructions of the function
     * @param size size of the function
     * @param paramDeclaration values of the parameters in the declaration of the function
     * @param params array containing the parameters of the function
     */
    public Function(String name, List<Instruction> instructions, int size, int[] paramDeclaration, String[] params) {
        super(name, instructions, size, paramDeclaration, params);
    }

    /**
     * Frees the cells occupied by the function in the memory
     * @throws MyIndexOutOfBoundsException
     * @throws InvalidValueException
     */
    @Override
    public void free() throws MyIndexOutOfBoundsException, InvalidValueException {
        int value = getMemory().getCurrentCellValue();
        super.free();
        getMemory().clearCurrentCell();
        getMemory().addCurrentCellValue(value);

    }
}
