package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InstructionException;
import mcga.brainfuck.exceptions.InvalidParametersException;

import java.util.List;

import static mcga.brainfuck.Brainfuck.getMemory;

/**
 * @author Team Make Coding Great Again
 */
public class Function extends Procedure {
    public Function(String name, List<Instruction> instructions, int size, int[] paramDeclaration, String[] params) throws InvalidParametersException {
        super(name, instructions, size, paramDeclaration, params);
    }

    @Override
    public void free() throws InstructionException {
        int value = getMemory().getCurrentCellValue();
        super.free();
        getMemory().clearCurrentCell();
        getMemory().addCurrentCellValue(value);

    }
}
