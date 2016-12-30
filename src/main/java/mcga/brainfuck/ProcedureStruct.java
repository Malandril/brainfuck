package mcga.brainfuck;

import mcga.brainfuck.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

import static mcga.brainfuck.Brainfuck.getInterpreter;

/**
 * Created by user on 23/12/2016.
 */
public class ProcedureStruct {
    public List<Instruction> instructions = new ArrayList<>();
    public int[] params;
    public int size;
    public boolean function;

    public ProcedureStruct(String code, String[] params, boolean function) {
        getInterpreter().pushInstructions(instructions);
        size = getInterpreter().readProcedureText(code);
        getInterpreter().popInstructions();
        this.function = function;
        this.params = new int[params.length];
        try {
            for (int i = 0; i < params.length; i++) {
                this.params[i] = Integer.parseInt(params[i]);
            }
        } catch (NumberFormatException e) {
            System.err.println("Mauvais argument de fonction");
            System.exit(56);
        }
    }
}
