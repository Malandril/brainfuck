package mcga.brainfuck;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by user on 12/10/2016.
 */
public class Input extends Affichage {
    protected InputStream stream;

    public Input() {
        this.stream =System.in;
    }

    public void interpret(){
        input();
    }

    public void input() {
        DataInputStream dataInputStream=new DataInputStream(stream);
        try {
            int c=dataInputStream.read();
            System.out.println(c);
            Brainfuck.memoire.clearCurrentCell();
            Brainfuck.memoire.addCurrentCellValue(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
