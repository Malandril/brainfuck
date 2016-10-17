package mcga.brainfuck;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 12/10/2016.
 */
public class Input extends Affichage {
    static InputStream stream;

    public Input() {
    }

    public void interpret() throws InvalidValueException {
        input();
    }

    public void input() throws InvalidValueException {
        try {
            int c = stream.read();
            Brainfuck.memoire.clearCurrentCell();
            Brainfuck.memoire.addCurrentCellValue(c);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }
}
