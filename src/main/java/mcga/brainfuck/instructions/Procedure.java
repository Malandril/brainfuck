package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

import static mcga.brainfuck.Brainfuck.getInterpreter;
import static mcga.brainfuck.Brainfuck.getMemory;
import static mcga.brainfuck.InstructionCreator.LEFT;
import static mcga.brainfuck.InstructionCreator.RIGHT;
import static mcga.brainfuck.Memory.*;

/**
 * Created by user on 07/12/2016.
 */
public class Procedure implements Instruction {
    public List<Integer> paramsCells = new ArrayList<>();
    public int startIndex;
    public int endIndex;
    public int size = 1;
    private int prevIndex;
    private int[] paramDeclaration;
    private String name;
    private List<Integer> paramsCall = new ArrayList<>();
    private List<Instruction> instructions = new ArrayList<>();

    public Procedure(String name, List<Instruction> instructions, int size, int[] paramDeclaration, String[] params) {
        if (params.length != paramDeclaration.length) {
            throw new InvalidCodeException("nombre de parametres invalide");
        }
        this.instructions = instructions;
        this.name = name;
        this.paramDeclaration = paramDeclaration;
        this.size = size;
        for (String param : params) {
            int address = 0;
            for (int i = 0; i < param.length(); i++) {
                String s1 = param.substring(i, i + 1);
                if (RIGHT.isIdentifier(s1)) {
                    address++;
                } else if (LEFT.isIdentifier(s1)) {
                    address--;
                } else {
                    throw new InvalidCodeException("Invalid parameter");
                }
            }
            this.paramsCall.add(address);
        }
    }

    public void interpret() {
        prevIndex = getMemory().getCurrentIndex();
        for (int i = 0; i < paramsCall.size(); i++) {
            paramsCells.add(i, getMemory().getCurrentCellValue() + paramsCall.get(i));
        }
        try {
            malloc();
            getInterpreter().interpretList(instructions);
            free();
        } catch (MyIndexOutOfBoundsException | InvalidValueException e) {
            System.err.println(e.getMessage());
            System.exit(MyIndexOutOfBoundsException.EXIT_CODE);
        }
    }

    private void malloc() throws MyIndexOutOfBoundsException, InvalidValueException {
        int i = MAX_SIZE - 1;
        getMemory().setCurrentIndex(i);
        if (getMemory().isProcedureStackEmpty()) {
            while (i >= 0 && getMemory().getCurrentCellValue() == 0) {
                getMemory().setCurrentIndex(--i);
            }
        } else {
            i = getMemory().peekProcedure().endIndex;
        }
        i++;
        if (MAX_SIZE - (i) < size) {
            System.err.println(NOT_ENOUGH_MESSAGE);
            System.exit(NOT_ENOUGH_CODE);
        } else {
            getMemory().setCurrentIndex(i);
            getMemory().pushProcedure(this);
            startIndex = i;
            endIndex = startIndex + size - 1;
            for (int k = 0; k < paramDeclaration.length; k++) {
                int index = startIndex + paramDeclaration[k];
                getMemory().setCurrentIndex(index);
                getMemory().addCurrentCellValue(paramsCells.get(k));
            }
        }
        getMemory().setCurrentIndex(startIndex);
    }

    protected void free() throws MyIndexOutOfBoundsException, InvalidValueException {
        int i = endIndex;
        while (i >= startIndex) {
            getMemory().setCurrentIndex(i--);
            getMemory().clearCurrentCell();
        }
        getMemory().popProcedure();
        getMemory().setCurrentIndex(prevIndex);

    }
}