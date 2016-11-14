package mcga.brainfuck;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by user on 14/11/2016.
 */
public class TranslateTest {
    Translate translate;

    @Before
    public void setUp() throws Exception {
        translate=new Translate("test.bf");
    }

    @Test
    public void testFileNotFoundExceptionInstantiation(){
        try{
            translate=new Translate("inexistantFile");
        }catch(FileNotFoundException exceptedException){}
    }

    @Test
    public void testInstantiation(){
        assertNotNull(translate);
    }

    @Test
    public void testExecuteInvalidInstructionException(){
        try{
            translate.execute("a");
        }catch(InvalidInstructionException expectedException){}
    }

    @Test
    public void testWriteBitmap() throws Exception {
        translate.execute("+");
        translate.writeBitmap();
        File file = new File("translation.bmp");
        assertTrue(file.exists());
    }

}