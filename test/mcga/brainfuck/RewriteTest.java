package mcga.brainfuck;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by user on 14/11/2016.
 */
public class RewriteTest {
    Rewrite rewrite;

    @Before
    public void setUp() throws Exception {
        rewrite=new Rewrite("test.bf");
    }

    @Test
    public void testFileNotFoundExceptionInstantiation(){
        try{
            rewrite=new Rewrite("inexistantFile");
        }catch(FileNotFoundException exceptedException){}
    }

    @Test
    public void testInstantiation(){
        assertNotNull(rewrite);
    }

    @Test
    public void testRewriteINCR() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("INCR");
        assertEquals("+",outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteDECR() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("DECR");
        assertEquals("-",outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteLEFT() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("LEFT");
        assertEquals("<",outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteRIGHT() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("RIGHT");
        assertEquals(">",outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteIN() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("IN");
        assertEquals(",",outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteOUT() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("OUT");
        assertEquals(".",outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteJUMP() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("JUMP");
        assertEquals("[",outContent.toString());
        System.setOut(oldOut);
    }

    @Test
    public void testRewriteBACK() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        rewrite.execute("BACK");
        assertEquals("]",outContent.toString());
        System.setOut(oldOut);
    }

}