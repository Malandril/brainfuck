package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.instructions.Increment;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 04/01/2017.
 */
public class InstructionCreatorTest {

    @Test
    public void getBitmapColorIndex() throws Exception {
        assertEquals("#ffffff", InstructionCreator.getBitmapColorIndex("INCR"));
    }

    @Test(expected = InvalidInstructionException.class)
    public void getBitmapColorIndexInvalid() throws Exception {
        InstructionCreator.getBitmapColorIndex("TEST");
    }

    @Test(expected = InvalidInstructionException.class)
    public void getBitmapColorIndexInvalidNull() throws Exception {
        InstructionCreator.getBitmapColorIndex(null);
    }

    @Test
    public void getInstruction() throws Exception {
        assertEquals(InstructionCreator.INCR, InstructionCreator.getInstruction("INCR"));
        assertEquals(null, InstructionCreator.getInstruction("TEST"));
    }

    @Test
    public void createInstruction() throws Exception {
        assertEquals(Increment.class, InstructionCreator.createInstruction("INCR").getClass());
    }

    @Test(expected = InvalidInstructionException.class)
    public void createInstructionInvalid() throws Exception {
        InstructionCreator.createInstruction("TEST");
    }

}