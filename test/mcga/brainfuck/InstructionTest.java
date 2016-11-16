package mcga.brainfuck;

import org.junit.Before;
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
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    Instruction instruction;

    @Before
    public void setUp() throws Exception {
        Brainfuck.memory = new Memory();
    }

    @Test
    public void testIncr() throws Exception {
        instruction = new Increment();
        assertNotNull(instruction);
        instruction.interpret();
        assertEquals(Brainfuck.memory.getCurrentCellValue(), 1);
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
        assertEquals(Brainfuck.memory.getCurrentCellValue(), 1);
    }

    @Test
    public void testExceptionLeft() throws Exception {
        instruction = new Left();
        expectedException.expect(IndexOutOfBoundsException.class);
        instruction.interpret();
    }

    @Test
    public void testLeft() throws Exception {
        instruction = new Left();
        assertNotNull(instruction);
        new Right().interpret();
        new Right().interpret();
        instruction.interpret();
        assertEquals(Brainfuck.memory.getCurrentIndex(), 1);
    }

    @Test
    public void testRight() throws Exception {
        instruction = new Right();
        assertNotNull(instruction);
        instruction.interpret();
        assertEquals(Brainfuck.memory.getCurrentIndex(), 1);
    }

    @Test
    public void testIn() throws Exception {
        PrintStream printStream = new PrintStream("test.bf");
        printStream.println("a");
        InputStream inputStream = new FileInputStream("test.bf");
        Input.stream = inputStream;
        instruction = new Input();
        assertNotNull(instruction);
        instruction.interpret();
        assertEquals(97, Brainfuck.memory.getCurrentCellValue());
    }

    @Test
    public void testOut() throws Exception {
        PrintStream printStream = new PrintStream("test.bf");
        Output.stream = printStream;
        instruction = new Output();
        assertNotNull(instruction);
        instruction.interpret();
        InputStream inputStream = new FileInputStream("test.bf");
        assertEquals(0,inputStream.read());
    }
}