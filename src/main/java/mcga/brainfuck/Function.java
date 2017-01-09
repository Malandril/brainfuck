package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;
import mcga.brainfuck.instructions.Instruction;
import mcga.brainfuck.instructions.Procedure;

import java.util.List;

import static mcga.brainfuck.Brainfuck.getMemory;

/**
 * Created by user on 29/12/2016.
 */
public class Function extends Procedure {
    public Function(String name, List<Instruction> instructions, int size, int[] paramDeclaration, String[] params) {
        super(name, instructions, size, paramDeclaration, params);
    }

    @Override
    public void free() throws MyIndexOutOfBoundsException, InvalidValueException {
        int value = getMemory().getCurrentCellValue();
        super.free();
        getMemory().clearCurrentCell();
        getMemory().addCurrentCellValue(value);

    }
}
