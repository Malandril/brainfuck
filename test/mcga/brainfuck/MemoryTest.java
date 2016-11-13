package mcga.brainfuck;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 13/11/2016.
 */
public class MemoryTest {
    Memory memory;

    @Before
    public void setUp() throws Exception {
        memory=new Memory();
    }

    @Test
    public void testInstantiation() throws Exception{
        assertNotNull(memory);
    }

    @Test
    public void testGetCurrentIndex() throws Exception {
        assertEquals(memory.getCurrentIndex(),0);
    }

    @Test
    public void testAddCurrentCellValue() throws Exception {
        int tmp = memory.getCurrentCellValue();
        memory.addCurrentCellValue(6);
        assertEquals(tmp+6,memory.getCurrentCellValue());
    }

    @Test
    public void testChangeCurrentIndex() throws Exception {
        int tmp = memory.getCurrentIndex();
        memory.changeCurrentIndex(6);
        assertEquals(tmp+6,memory.getCurrentIndex());
    }

    @Test
    public void TestToString() throws Exception {
        assertEquals(memory.toString(),"");
    }

    @Test
    public void testGetCurrentCellValue() throws Exception {
        assertEquals(memory.getCurrentCellValue(),0);
    }

    @Test
    public void clearCurrentCell() throws Exception {
        memory.clearCurrentCell();
        assertEquals(memory.getCurrentCellValue(),0);
    }

}