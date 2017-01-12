package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InstructionException;
import mcga.brainfuck.exceptions.InvalidParametersException;

import java.util.List;

import static mcga.brainfuck.Brainfuck.getMemory;

/**
 * This class defines a function;
 * @author Team Make Coding Great Again
 */
public class Function extends Procedure {

    /**
     * Constructor of the Function class.
     * @param name name of the function
     * @param instructions instructions of the function
     * @param size size of the function
     * @param paramDeclaration parameters of the declaration of the function
     * @param params parameters of the function
     * @throws InvalidParametersException is a parameter is invalid
     */
    public Function(String name, List<Instruction> instructions, int size, int[] paramDeclaration, String[] params) throws InvalidParametersException {
        super(name, instructions, size, paramDeclaration, params);
    }

    /**
     * Frees the cells occupied by the function in the memory.
     * @throws InstructionException if an instruction can cause an issue during the execution
     */
    @Override
    public void free() throws InstructionException {
        int value = getMemory().getCurrentCellValue();
        super.free();
        getMemory().clearCurrentCell();
        getMemory().addCurrentCellValue(value);

    }
}
