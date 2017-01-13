package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.instructions.Increment;
import mcga.brainfuck.instructions.Instruction;
import mcga.brainfuck.instructions.Output;
import mcga.brainfuck.instructions.Right;
import mcga.brainfuck.processing.Interpreter;
import mcga.brainfuck.processing.Parser;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30/11/2016.
 */
public class MetricsTest {
    private final String fileName = "./testFile.txt";
    Memory memory;
    Interpreter interpreter;
    List<Instruction> instructions;

    @Before
    public void setUp() throws Exception {
        memory = new Memory();
        PrintStream ps = new PrintStream(fileName);
        ps.println("+>+.");
        ps.close();
        interpreter=new Interpreter(fileName);
    }

    @Test
    public void metrics() throws Exception {
      //  setListInstructions();
        interpreter.parseFile();
/*        interpreter.interpretList(0, 1);
        assertEquals(1, Metrics.getDataWrite());
        assertEquals(1, Metrics.getExecMove());
        assertEquals(1, Metrics.getExecStep());
        interpreter.interpretList(1, 2);
        assertEquals(1, Metrics.getDataMove());
        assertEquals(2, Metrics.getExecMove());
        assertEquals(2, Metrics.getExecStep());
        interpreter.interpretList(2, 3);
        assertEquals(1, Metrics.getDataRead());
        assertEquals(3, Metrics.getExecMove());
        assertEquals(3, Metrics.getExecStep());

        assertEquals(3, Metrics.getProgSize());
*/
    }



    public void setListInstructions(){
        instructions = new ArrayList <>();
        instructions.add(new Increment());
        instructions.add(new Right());
        instructions.add(new Output());
    }

    class MyParser extends Parser{

        @Override
        public void execute(String str) throws InvalidInstructionException {

        }
    }
}