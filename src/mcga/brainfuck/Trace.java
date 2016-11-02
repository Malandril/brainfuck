package mcga.brainfuck;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by user on 02/11/2016.
 */
public class Trace extends Parser {

    private InputStream stream;
    private FileOutputStream fileOutputStream;

    public Trace(InputStream stream) {
        super(stream);
    }

    @Override
    public void parseFile() {
        super.parseFile();
    }

    @Override
    public void execute(String str) throws InvalidInstructionException {
        System.out.println("lol");
    }
}
