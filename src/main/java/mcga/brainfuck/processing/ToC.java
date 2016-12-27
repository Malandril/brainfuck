package mcga.brainfuck.processing;

import mcga.brainfuck.exceptions.InvalidInstructionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Gaetan Vialon
 *         Created the 22/12/2016.
 */
public class ToC extends Parser {

    public ToC() {
        super();
    }

    /**
     * Constructor with the name of file
     * @param fileName String containing the name of the file
     * @throws FileNotFoundException
     * @see Parser#Parser()
     */

    public ToC(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Constructor with a FileInputStream
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public ToC(FileInputStream stream){
        super(stream);
    }


    @Override
    public void execute(String str) throws InvalidInstructionException {
        String strConverted;
        try {
            strConverted = Parser.getCSyntax(str);
            System.err.println(strConverted);
        } catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(42);
        }
    }

    public static String initialize(){
        String Init;
        Init = "#include <stdio.h> \n";
        Init += "#include <stdlib.h> \n\n";
        Init += maxminptr();
        Init += "int main(void) {\n\n";
        Init += "unsigned char tab[30000]={};\n";
        Init += "int ptr=0;\n";

        return Init;
    }

    public static String endOfFile(){
        return "return 1;\n}";
    }

    private static String maxminptr(){
        String MaxMinPtr;
        MaxMinPtr = "void MaxMinPtr(int ptr){\n";
        MaxMinPtr += "if(ptr >= 30000 || ptr < 0) exit(2);\n";
        MaxMinPtr += "}\n\n";
        return MaxMinPtr;
    }
}
