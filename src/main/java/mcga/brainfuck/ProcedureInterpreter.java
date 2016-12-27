package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.processing.Interpreter;

/**
 * Created by user on 27/12/2016.
 */
public class ProcedureInterpreter extends Interpreter {
    private int size = 0;

    public ProcedureInterpreter() {
    }

    @Override
    public void execute(String str) throws InvalidInstructionException {
        super.execute(str);
        if (str.equals(">") || str.equals("Right")) {
            size++;
        }
    }

    public int readProcedureText(String str) {
        size = 0;
        super.readText(str);
        return size;
    }
}
