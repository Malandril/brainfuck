package mcga.brainfuck.processing;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 14/11/2016.
 */
public class RewriteTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    Rewrite rewrite;

    @Before
    public void setUp() throws Exception {
        File file = new File("test.bf");
        file.createNewFile();
        rewrite = new Rewrite("test.bf");
    }

    @Test
    public void testFileNotFoundExceptionInstantiation() throws FileNotFoundException {
        expectedException.expect(FileNotFoundException.class);
        rewrite = new Rewrite("inexistantFile");
    }

    @Test
    public void testInstantiation() {
        assertNotNull(rewrite);
    }

    @Test
    public void testRewriteINCR() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("INCR");
        assertEquals("+", outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteDECR() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("DECR");
        assertEquals("-", outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteLEFT() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("LEFT");
        assertEquals("<", outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteRIGHT() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("RIGHT");
        assertEquals(">", outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteIN() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("IN");
        assertEquals(",", outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteOUT() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("OUT");
        assertEquals(".", outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteJUMP() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("JUMP");
        assertEquals("[", outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteBACK() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("BACK");
        assertEquals("]", outContent.toString());
        System.setOut(oldOut);
    }

}