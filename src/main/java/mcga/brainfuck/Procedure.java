package mcga.brainfuck;

import mcga.brainfuck.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

import static mcga.brainfuck.Brainfuck.getMemory;
import static mcga.brainfuck.Brainfuck.peekInterpreter;

/**
 * Created by user on 07/12/2016.
 */
public class Procedure implements Instruction {
    public Procedure prevProcedure;
    int prevIndex;
    int startIndex;
    int endIndex;
    int size = 0;
    int[] paramDeclaration;
    private String name;
    private List<Instruction> instructions = new ArrayList<>();

    public Procedure(String name, List<Instruction> instructions,int size, int[] paramDeclaration,String params) {
        this.instructions = instructions;
        this.name = name;
        this.paramDeclaration = paramDeclaration;
        this.size=size;
    }

    public void interpret() {
        getMemory().malloc(this);
        peekInterpreter().interpretList(instructions);
        getMemory().free(this);
        System.out.println(getMemory().maxVal);
    }

}