package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.MyException;

import java.util.List;

import static mcga.brainfuck.Brainfuck.getMemory;

/**
 * @author Team Make Coding Great Again
 */
public class Function extends Procedure {
    public Function(List<Instruction> instructions, int size, int[] paramDeclaration, String[] params) throws MyException {
        super(instructions, size, paramDeclaration, params);
    }

    @Override
    public void free() throws MyException {
        int value = getMemory().getCurrentCellValue();
        super.free();
        getMemory().clearCurrentCell();
        getMemory().addCurrentCellValue(value);

    }
}
