package mcga.brainfuck.processing;

import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.exceptions.InvalidInstructionException;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

/**
 * Created by user on 16/11/2016.
 */
public class ParserTest {
    String current;
    Parser parser;

    @Before
    public void setUp() throws Exception {
        PrintStream printStream = new PrintStream("test.bf");
        for (InstructionCreator instructionCreator : InstructionCreator.values()) {
            printStream.println(instructionCreator.getIdentifier(0));
            printStream.println(instructionCreator.getIdentifier(1));
        }
        parser = new Parser() {
            @Override
            public void execute(String str) throws InvalidInstructionException {
                current = str;
            }
        };

    }

    @Test
    public void getShortSyntax() throws Exception {

    }

    @Test
    public void isLongSyntax() throws Exception {

    }

    @Test
    public void isMacroDeclaration() throws Exception {

    }

    @Test
    public void isComments() throws Exception {

    }

    @Test
    public void getLongSyntax() throws Exception {

    }

    @Test
    public void execute() throws Exception {

    }

    @Test
    public void parseFile() throws Exception {

    }

    @Test
    public void readBitmap() throws Exception {

    }

    @Test
    public void colorToHex() throws Exception {

    }

}