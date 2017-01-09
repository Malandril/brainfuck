package mcga.brainfuck.processing;

import mcga.brainfuck.exceptions.InvalidInstructionException;

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
    public static final String ARG_STRING = "p";
    private List<String> instructions = new ArrayList<>();
    private List<String> functionInstructions = new ArrayList<>();
    private PrintStream outputStream;

    /**
     * Empty constructor
     */
    public ToC() {
        super();
        this.outputStream = outputStream;
    }

    /**
     * Constructor with the name of file
     *
     * @param fileName String containing the name of the file
     * @throws FileNotFoundException
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
     *
     * @return
     */
    // TODO: 09/01/2017 finish comments here
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
    public void parseFile() {
        super.parseFile();
        outputStream.println(initialize());
        for (String instruction : instructions) {
            outputStream.println(instruction);
        }
        outputStream.println(endOfFile());
    }


    /**
     *
     * @param function
     * @return
     */
    // TODO: 09/01/2017 finish comments here
    @Override
    public IDeclaration declareFunction(boolean function) {
        return new ToCFunctionDeclaration(function);
    }


    /**
     * Overrides the main class method so that for each instruction, its C syntax is added to the instructions List.
     * @param str string value of the argument to interpret
     * @throws InvalidInstructionException if the instruction is invalid
     */
    @Override
    public void execute(String str) throws InvalidInstructionException {
        try {
            instructions.add(getCSyntax(str));
        } catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(42);
        }
    }

    /**
     * Inner class
     */
    // TODO: 09/01/2017 finish comments here
    private class ToCFunctionDeclaration extends FunctionDeclaration {
        /**
         * Constructor of the ToCFunctionDeclaration class.
         * @param function
         */
        ToCFunctionDeclaration(boolean function) {
            super(function);
        }

        @Override
        public void action(String name, String code, String[] params) {
            super.action(name, code, params);
            StringJoiner sj = new StringJoiner(",");
            for (String param : params) {
                sj.add("unsigned char " + ARG_STRING + param);
            }
            List<String> tmp = instructions;
            instructions = functionInstructions;
            instructions.add("void " + name + "(" + sj.toString() + "){");
            String str = "int tmpPtr = ptr;\n" + "int tmpStart;\n" + "int tmpEnd;\n" + "int size = " + struct.size + ";\n" + "ptr = " + (MAX_SIZE - 1) + ";\n" + "while (tab[ptr] == 0) {\n" + "   ptr--;\n" + "}\n" + "ptr++;\n" + "if (" + MAX_SIZE + " - ptr < size) {\n" + "   fprintf(stderr, \"" + NOT_ENOUGH_MESSAGE + "\");\n" + "   exit(" + NOT_ENOUGH_CODE + ");\n" + "} else {\n" + "   tmpStart = start;\n" + "   tmpEnd = end;\n" + "   start = ptr;\n" + "   end = ptr + size - 1;\n" + "}";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < struct.params.length; i++) {
                sb.append("tab[ptr+").append(struct.params[i]).append("]=").append(ARG_STRING).append(params[i]).append(";\n");
            }
            instructions.add(str);
            instructions.add(sb.toString());
            readText(code);
            instructions.add("ptr = end;\n" + "while (ptr >= start) {\n" + "   tab[ptr--] = 0;\n" + "}\n" + "start = tmpStart;\n" + "end = tmpEnd;\n" + "ptr = tmpPtr;\n" + "}");
            instructions = tmp;
        }
    }
}

