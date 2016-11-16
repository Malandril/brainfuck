package mcga.brainfuck;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        File file=new File("test.bf");
        file.createNewFile();
        translate=new Translate("test.bf");
    }

    @Rule
    public ExpectedException expectedException=ExpectedException.none();

    @Test
    public void testFileNotFoundExceptionInstantiation() throws FileNotFoundException {
        expectedException.expect(FileNotFoundException.class);
        translate=new Translate("inexistantFile");
    }

    @Test
    public void testInstantiation(){
        assertNotNull(translate);
    }

    @Test
    public void testExecuteInvalidInstructionException() throws InvalidInstructionException {
        expectedException.expect(InvalidInstructionException.class);
        translate.execute("a");
    }

    @Test
    public void testWriteBitmap() throws Exception {
        translate.execute("+");
        translate.writeBitmap();
        File file = new File("translation.bmp");
        assertTrue(file.exists());
    }

}