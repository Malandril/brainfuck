package mcga.brainfuck;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 30/11/2016.
 */
public class MacroTest {

    Macro macro;
    final String testLine = "test";

    @Before
    public void setUp() throws Exception {
        macro = new Macro("macro", "++");
    }

    @Test
    public void callMacro() throws Exception {
        assertEquals(macro.value, macro.callMacro("macro"));
        assertEquals(testLine, macro.callMacro(testLine));
    }

    @Test
    public void compareTo() throws Exception {
        Macro macro2 = new Macro("macro", "+");
        assertEquals(0, macro.compareTo(macro2));
    }

    @Test
    public void equals() throws Exception {
        Macro macro2 = new Macro("macro", "+");
        assertEquals(macro, macro2);
        assertEquals(true, macro.equals(macro2));
    }



}