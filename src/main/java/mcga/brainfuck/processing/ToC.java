package mcga.brainfuck.processing;

import mcga.brainfuck.exceptions.InvalidCodeException;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static mcga.brainfuck.InstructionCreator.getCSyntax;
import static mcga.brainfuck.Memory.*;

/**
 * Class defining the actions to do when the user wants to translate his code from Brainf*ck to C language.
 * @author Team Make Coding Great Again
 *         Created the 22/12/2016.
 */
public class ToC extends Parser {
    private static final String ARG_STRING = "p";
    private List<String> instructions = new ArrayList<>();
    private List<String> functionInstructions = new ArrayList<>();
    private PrintStream outputStream;

    /**
     * Empty constructor
     */
    public ToC() {}

    public ToC(PrintStream outputStream) {
        super();
        this.outputStream = outputStream;
    }
    
    /**
     * Constructor with the name of file
     *
     * @param fileName String containing the name of the file
     * @throws FileNotFoundException if the input file isn't found
     * @see Parser#Parser()
     */
    
    public ToC(String fileName, PrintStream outputStream) throws FileNotFoundException {
        super(fileName);
        this.outputStream = outputStream;
    }


    /**
     * Builds the string corresponding to the heading of the generated file.
     * It contains the inclusion of stdio.h and stdlib.h, as well as the declaration of the variables
     * and the prototype of the main method.
     * @return String corresponding to the heading of the file.
     */
    public String initialize() {
        StringBuilder init = new StringBuilder();
        init.append("#include <stdio.h> \n").append("#include <stdlib.h> \n\n").append("unsigned char tab[").append(MAX_SIZE).append("] = {};\n").append("int ptr = 0;\n").append("int start = 0;\n").append("int end = ").append(MAX_SIZE - 1).append(";\n").append(maxminptr());
        
        for (String instruction : functionInstructions) {
            init.append(instruction).append("\n");
        }
        init.append("int main(void) {\n\n");
        return init.toString();
    }


    /**
     * Returns the String corresponding to the end of the main method.
     * @return String corresponding to the end of the main method.
     */
    public String endOfFile() {
        return "return 1;\n}";
    }


    /**
     * Generates the code in C language which checks if the value of the pointer exceeds the limits of the memory.
     * @return Code corresponding to the function
     */
    private String maxminptr() {
        String MaxMinPtr;
        MaxMinPtr = "void MaxMinPtr(int i){\n";
        MaxMinPtr += "if(i > end || i < start) exit(2);\n";
        MaxMinPtr += "}\n\n";
        return MaxMinPtr;
    }


    /**
     * Overrides the main class method so that it prints all the parts of the generated file.
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile() throws InvalidCodeException {
        super.parseFile();
        outputStream.println(initialize());
        for (String instruction : instructions) {
            outputStream.println(instruction);
        }
        outputStream.println(endOfFile());
    }


    /**
     * Declares a function.
     * @param function true is the function is a function, false if the function is a procedure.
     * @return a new {@link ToCFunctionDeclaration ToCFunctionDeclaration} object
     */
    @Override
    public IDeclaration declareFunction(boolean function) {
        return new ToCFunctionDeclaration(function);
    }


    /**
     * This method overrides {@link Parser#execute(String) execute} called in {@link Parser#parseFile() parseFile}
     * so that for each instruction, its C syntax is added to the instructions List.
     * @param str string value of the argument to interpret
     * @throws InvalidCodeException if the instruction is invalid
     */
    @Override
    public void execute(String str) throws InvalidCodeException {
        instructions.add(getCSyntax(str));
    }

    /**
     * Represents a function declaration in C language
     */
    private class ToCFunctionDeclaration extends FunctionDeclaration {

        /**
         * Constructor of the ToCFunctionDeclaration class.
         * @param function true is the function is a function, false if the function is a procedure.
         */
        ToCFunctionDeclaration(boolean function) {
            super(function);
        }

        @Override
        public void action(String name, String code, String[] params) throws InvalidCodeException {
            super.action(name, code, params);
            StringJoiner sj = new StringJoiner(",");
            for (String param : params) {
                sj.add("unsigned char " + ARG_STRING + param);
            }
            List<String> tmp = instructions;
            instructions = functionInstructions;
            if (!function) {
                instructions.add("void " + name + "(" + sj.toString() + "){");
            } else {
                instructions.add("unsigned char " + name + "(" + sj.toString() + "){");
            }
            String str = "int tmpPtr = ptr;\n" + "int tmpStart;\n" + "int tmpEnd;\n" + "int size = " + struct.getSize() + ";\n" + "ptr = " + (MAX_SIZE - 1) + ";\n" + "while (tab[ptr] == 0) {\n" + "   ptr--;\n" + "}\n" + "ptr++;\n" + "if (" + MAX_SIZE + " - ptr < size) {\n" + "   fprintf(stderr, \"" + NOT_ENOUGH_MESSAGE + "\");\n" + "   exit(" + NOT_ENOUGH_CODE + ");\n" + "} else {\n" + "   tmpStart = start;\n" + "   tmpEnd = end;\n" + "   start = ptr;\n" + "   end = ptr + size - 1;\n" + "}";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < struct.getDeclarationParmsSize(); i++) {
                sb.append("tab[ptr+").append(struct.getDeclarationParam(i)).append("]=").append(ARG_STRING).append(params[i]).append(";\n");
            }
            instructions.add(str);
            instructions.add(sb.toString());
            readText(code);
            if (function) {
                instructions.add("int res=tab[ptr];");
            }
            instructions.add("ptr = end;\n" + "while (ptr >= start) {\n" + "   tab[ptr--] = 0;\n" + "}\n" + "start = tmpStart;\n" + "end = tmpEnd;\n" + "ptr = tmpPtr;\n");
            if (function) {
                instructions.add("return res;");
            }
            instructions.add("}");
            instructions = tmp;
        }
    }
}

