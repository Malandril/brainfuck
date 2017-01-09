package mcga.brainfuck;

import mcga.brainfuck.exceptions.BrainfuckIndexOutOfBoundsException;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.instructions.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by user on 15/11/2016.
 */
public class InstructionTest {
    private final String testFileName = "test.bf";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    Instruction instruction;

    @Before
    public void setUp() throws Exception {
        Brainfuck.setMemory(new Memory());
    }

    @Test
    public void testIncr() throws Exception {
        instruction = new Increment();
        assertNotNull(instruction);
        instruction.interpret();
        assertEquals(Brainfuck.getMemory().getCurrentCellValue(), 1);
    }

    @Test
    public void testExceptionDecr() throws Exception {
        instruction = new Decrement();
        expectedException.expect(InvalidValueException.class);
        instruction.interpret();
    }

    @Test
    public void testDecr() throws Exception {
        instruction = new Decrement();
        assertNotNull(instruction);
        new Increment().interpret();
        new Increment().interpret();
        instruction.interpret();
        assertEquals(Brainfuck.getMemory().getCurrentCellValue(), 1);
    }

    @Test
    public void testExceptionLeft() throws Exception {
        instruction = new Left();
        expectedException.expect(BrainfuckIndexOutOfBoundsException.class);
        instruction.interpret();
    }

    @Test
    public void testLeft() throws Exception {
        instruction = new Left();
        assertNotNull(instruction);
        new Right().interpret();
        new Right().interpret();
        instruction.interpret();
        assertEquals(Brainfuck.getMemory().getCurrentIndex(), 1);
    }

    @Test
    public void testRight() throws Exception {
        instruction = new Right();
        assertNotNull(instruction);
        instruction.interpret();
        assertEquals(Brainfuck.getMemory().getCurrentIndex(), 1);
    }

    @Test
    public void testIn() throws Exception {
        PrintStream printStream = new PrintStream(testFileName);
        printStream.println("a");
        InputStream inputStream = new FileInputStream(testFileName);
        Input.stream = inputStream;
        instruction = new Input();
        assertNotNull(instruction);
        instruction.interpret();
        assertEquals(97, Brainfuck.getMemory().getCurrentCellValue());
    }

    @Test
    @Ignore
    public void testOut() throws Exception {
        PrintStream printStream = new PrintStream(testFileName);
        System.setOut(printStream);
        instruction = new Output();
        assertNotNull(instruction);
        instruction.interpret();
        InputStream inputStream = new FileInputStream(testFileName);
        assertEquals(0, inputStream.read());
    }

    @Ignore
    @Test
    public void testLoop() throws Exception {
         Jump jump=new Jump();

    }
}