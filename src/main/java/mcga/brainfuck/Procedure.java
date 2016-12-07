package mcga.brainfuck;

import mcga.brainfuck.instructions.Instruction;

/**
 * Created by user on 07/12/2016.
 */
public class Procedure implements Instruction{
    String name;
    String code;

    public Procedure(String name, String code){
        this.name = name;
        this.code = code;
    }

    public void interpret(){

    }
}
