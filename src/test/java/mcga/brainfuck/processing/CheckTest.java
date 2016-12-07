package mcga.brainfuck.processing;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static mcga.brainfuck.InstructionCreator.*;
import static org.junit.Assert.*;

/**
 * Created by user on 30/11/2016.
 */
public class CheckTest {

    Check check;

    @Before
    public void setUp() throws Exception {
        check = new Check();

    }

    @Test
    public void execute() throws Exception {
        check.execute(JUMP.toString());
        assertEquals(1, check.getCount());
        check.execute(BACK.toString());
        assertEquals(0, check.getCount());
        check.execute(INCR.toString());
        assertEquals(0, check.getCount());
        check.execute(DECR.toString());
        assertEquals(0, check.getCount());
        check.execute(RIGHT.toString());
        assertEquals(0, check.getCount());
        check.execute(LEFT.toString());
        assertEquals(0, check.getCount());
        check.execute(IN.toString());
        assertEquals(0, check.getCount());
        check.execute(OUT.toString());
        assertEquals(0, check.getCount());
    }

}