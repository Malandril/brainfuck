package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;
import mcga.brainfuck.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

import static mcga.brainfuck.Brainfuck.getInterpreter;
import static mcga.brainfuck.Brainfuck.getMemory;
import static mcga.brainfuck.Memory.*;

/**
 * Created by user on 07/12/2016.
 */
public class Procedure implements Instruction {
    public List<Integer> paramsCells = new ArrayList<>();
    private int prevIndex;
    int startIndex;
    int endIndex;
    public int size = 1;
    private int[] paramDeclaration;
    private String name;
    private List<List<Instruction>> paramsInstructions = new ArrayList<>();
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
            ArrayList<Instruction> list = new ArrayList<>();
            getInterpreter().pushInstructions(list);
            getInterpreter().readProcedureText(param);
            getInterpreter().popInstructions();
            this.paramsInstructions.add(list);
        }
    }

    public void interpret() {
        prevIndex = getMemory().getCurrentIndex();
        for (int i = 0; i < paramsInstructions.size(); i++) {
            getInterpreter().interpretList(paramsInstructions.get(i));
            paramsCells.add(i, getMemory().getCurrentCellValue());
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