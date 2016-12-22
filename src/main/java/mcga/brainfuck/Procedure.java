package mcga.brainfuck;

import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;
import mcga.brainfuck.instructions.Instruction;
import mcga.brainfuck.instructions.Right;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mcga.brainfuck.Brainfuck.*;

/**
 * Created by user on 07/12/2016.
 */
public class Procedure implements Instruction {
    public String name;
    public int prevIndex;
    public int startIndex;
    public int endIndex;
    private List<Instruction> instructionsProcedure=new ArrayList<>();
    public int size = 0;

    public Procedure(String name, String code){
        this.name = name;
        getInterpreter().pushInstructions(instructionsProcedure);
        getInterpreter().readText(code);
        getInterpreter().popInstructions();
        calculateMemoryNeeded();
    }

    public void interpret(){
        getMemory().malloc(this);
        getInterpreter().interpretList(instructionsProcedure);
        getMemory().free(this);
    }

    public void calculateMemoryNeeded(){
        for (Instruction instruction : instructionsProcedure) {
            if(instruction instanceof Right){
                size++;
            }
        }
    }
}