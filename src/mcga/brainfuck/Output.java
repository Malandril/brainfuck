package mcga.brainfuck;

import java.io.PrintStream;

/**
 * Created by user on 12/10/2016.
 */
public class Output extends Affichage {
    static PrintStream stream;
    public Output(){
    }

    public void interpret(){
        output();
    }

    public void output() {
        stream.print((char) Brainfuck.memoire.getCurrentCellValue());
    }
}
