package mcga.brainfuck;

import mcga.brainfuck.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

import static mcga.brainfuck.Brainfuck.popInterpreter;
import static mcga.brainfuck.Brainfuck.pushInterpreter;

/**
 * Created by user on 23/12/2016.
 */
public class ProcedureStruct {
    public List<Instruction> instructions = new ArrayList<>();
    public int[] params;
    public int size;

    public ProcedureStruct(String code, String[] params) {
        ProcedureInterpreter interpreter = new ProcedureInterpreter();
        pushInterpreter(interpreter);
        interpreter.pushInstructions(instructions);
        size = interpreter.readProcedureText(code);
        interpreter.popInstructions();
        popInterpreter();
        this.params=new int[params.length];
        for (int i = 0; i < params.length; i++) {
            this.params[i]=Integer.parseInt(params[i]);
        }
    }
}
