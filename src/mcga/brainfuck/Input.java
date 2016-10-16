package mcga.brainfuck;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by user on 12/10/2016.
 */
public class Input extends Affichage {
    static InputStream stream;

    public Input() {
    }

    public void interpret(){
        input();
    }

    public void input() {
        try {
            int c=stream.read();
            Brainfuck.memoire.clearCurrentCell();
            Brainfuck.memoire.addCurrentCellValue(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
