package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InstructionException;
import mcga.brainfuck.exceptions.InvalidParametersException;

import java.util.ArrayList;
import java.util.List;

import static mcga.brainfuck.Brainfuck.getInterpreter;
import static mcga.brainfuck.Brainfuck.getMemory;
import static mcga.brainfuck.InstructionCreator.LEFT;
import static mcga.brainfuck.InstructionCreator.RIGHT;
import static mcga.brainfuck.Memory.*;

/**
 * This method defines a procedure.
 */
public class Procedure implements Instruction {

    private List<Integer> paramsCells = new ArrayList<>();
    private int startIndex;
    private int endIndex;
    private int size = 1;
    private int prevIndex;
    private int[] paramDeclaration;
    private String name;
    private List<Integer> paramsCall = new ArrayList<>();
    private List<Instruction> instructions = new ArrayList<>();

    /**
     * Constructor of the Procedure class.
     * @param name name of the procedure
     * @param instructions instructions of the procedure
     * @param size size of the procedure
     * @param paramDeclaration parameters of the declaration of the procedure
     * @param params parameters of the procedure
     * @throws InvalidParametersException if a parameter is invalid
     */
    public Procedure(String name, List<Instruction> instructions, int size, int[] paramDeclaration, String[] params) throws InvalidParametersException {
        this.name = name;
        if (params.length != paramDeclaration.length) {
            throw new InvalidParametersException(name);
        }
        this.instructions = instructions;
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
                    throw new InvalidParametersException(name);
                }
            }
            if (address < 0 || address > MAX_SIZE) {
                throw new InvalidParametersException(name);
            }
            this.paramsCall.add(address);
        }
    }

    /**
     * Interprets the procedure.
     * @throws InstructionException if an instruction can cause an issue during the execution
     */
    @Override
    public void interpret() throws InstructionException {
        prevIndex = getMemory().getCurrentIndex();
        for (int i = 0; i < paramsCall.size(); i++) {
            getMemory().changeCurrentIndex(paramsCall.get(i));
            paramsCells.add(i, getMemory().getCurrentCellValue());
        }
        memoryAllocation();
        getInterpreter().interpretList(instructions);
        free();
    }

    /**
     * Checks if there is enough space in the memory to declare a procedure.
     * @throws InstructionException if an instruction can cause an issue during the execution
     */
    private void memoryAllocation() throws InstructionException {
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
            getMemory().setCurrentIndex(startIndex);
        }
        getMemory().setCurrentIndex(startIndex);
    }

    /**
     * Frees the cells occupied by the procedure in the memory.
     * @throws InstructionException if an instruction can cause an issue during the execution
     */
    protected void free() throws InstructionException {
        int i = endIndex;
        while (i >= startIndex) {
            getMemory().setCurrentIndex(i--);
            getMemory().clearCurrentCell();
        }
        getMemory().popProcedure();
        getMemory().setCurrentIndex(prevIndex);
    }
    
    public int getStartIndex() {
        return startIndex;
    }
    
    public int getEndIndex() {
        return endIndex;
    }
    
    public int getSize() {
        return size;
    }
}