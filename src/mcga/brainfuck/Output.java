package mcga.brainfuck;

import java.io.PrintStream;

/**
 * Created by user on 12/10/2016.
 */
public class Output extends Affichage {
    public static PrintStream stream;

    public Output() {
        this.stream = System.out;
    }

    public void interpret() {
        output();
    }

    public void output() {
        stream.print((char) Brainfuck.memoire.getCurrentCellValue());
    }
}
