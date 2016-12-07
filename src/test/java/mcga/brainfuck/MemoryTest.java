package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 13/11/2016.
 */
public class MemoryTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    Memory memory;

    @Before
    public void setUp() throws Exception {
        memory = new Memory();
    }

    @Test
    public void testInstantiation() throws Exception {
        assertNotNull(memory);
    }

    @Test
    public void testGetCurrentIndex() throws Exception {
        assertEquals(memory.getCurrentIndex(), 0);
    }

    @Test
    public void testGetCurrentCellValue() throws Exception {
        assertEquals(memory.getCurrentCellValue(), 0);
    }

    @Test
    public void testAddCurrentCellValue() throws Exception {
        memory.addCurrentCellValue(6);
        assertEquals(6, memory.getCurrentCellValue());
    }

    @Test
    public void testSuperiorValueExceptionAddCurrentCellValue() throws InvalidValueException {
        expectedException.expect(InvalidValueException.class);
        memory.addCurrentCellValue(256);
    }

    @Test
    public void testInferiorValueExceptionAddCurrentCellValue() throws InvalidValueException {
        expectedException.expect(InvalidValueException.class);
        memory.addCurrentCellValue(-1);
    }

    @Test
    public void testChangeCurrentIndex() throws Exception {
        memory.changeCurrentIndex(6);
        assertEquals(6, memory.getCurrentIndex());
    }


    @Test
    public void testExceptionLowChangeCurentIndex() throws MyIndexOutOfBoundsException {
        expectedException.expect(MyIndexOutOfBoundsException.class);
        memory.changeCurrentIndex(-1);
    }

    @Test
    public void testExceptionHightChangeCurrentIndex() throws MyIndexOutOfBoundsException {
        expectedException.expect(MyIndexOutOfBoundsException.class);
        memory.changeCurrentIndex(30000);
    }

    @Test
    public void TestToString() throws Exception {
        assertEquals(memory.toString(), "");
        memory.addCurrentCellValue(1);
        assertEquals("C0: 1", memory.toString());
    }

    @Test
    public void clearCurrentCell() throws Exception {
        memory.addCurrentCellValue(1);
        memory.clearCurrentCell();
        assertEquals(memory.getCurrentCellValue(), 0);
    }

}