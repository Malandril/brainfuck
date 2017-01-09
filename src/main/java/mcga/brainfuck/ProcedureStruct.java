package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidParametersException;
import mcga.brainfuck.instructions.Function;
import mcga.brainfuck.instructions.Instruction;
import mcga.brainfuck.instructions.Procedure;

import java.util.ArrayList;
import java.util.List;

import static mcga.brainfuck.Brainfuck.getInterpreter;

/**
 * Created by user on 23/12/2016.
 */
public class ProcedureStruct {
    private List<Instruction> instructions = new ArrayList<>();
    private int[] declarationParams;
    private int size;
    private boolean function;
    
    public ProcedureStruct(String code, String[] params, boolean function) throws InvalidCodeException {
        getInterpreter().pushInstructions(instructions);
        size = getInterpreter().readProcedureText(code);
        getInterpreter().popInstructions();
        this.function = function;
        this.declarationParams = new int[params.length];
        try {
            for (int i = 0; i < params.length; i++) {
                this.declarationParams[i] = Integer.parseInt(params[i]);
            }
        } catch (NumberFormatException e) {
            System.err.println("Mauvais argument de fonction");
            System.exit(51);
        }
    }
    
    public Procedure createProcedure(String name, String[] params) throws InvalidParametersException {
        return new Procedure(name, instructions, size, declarationParams, params);
    }
    
    public Procedure createFunction(String name, String[] params) throws InvalidParametersException {
        return new Function(name, instructions, size, declarationParams, params);
    }
    
    public int getDeclarationParam(int i) {
        return declarationParams[i];
    }
    
    public int getDeclarationParmsSize() {
        return declarationParams.length;
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean isFunction() {
        return function;
    }
}
