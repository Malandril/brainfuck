package mcga.brainfuck.processing;

import mcga.brainfuck.exceptions.InvalidInstructionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static mcga.brainfuck.InstructionCreator.getCSyntax;
import static mcga.brainfuck.Memory.*;

/**
 * @author Gaetan Vialon
 * @author Thomas Canava
 *         Created the 22/12/2016.
 */
public class ToC extends Parser {
    public static final String ARG_STRING = "p";
    private List<String> instructions = new ArrayList<>();
    private List<String> functionInstructions = new ArrayList<>();

    public ToC() {
        super();
    }

    /**
     * Constructor with the name of file
     *
     * @param fileName String containing the name of the file
     * @throws FileNotFoundException
     * @see Parser#Parser()
     */

    public ToC(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Constructor with a FileInputStream
     *
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public ToC(FileInputStream stream) {
        super(stream);
    }

    public String initialize() {
        StringBuilder init = new StringBuilder();
        init.append("#include <stdio.h> \n")
                .append("#include <stdlib.h> \n\n")
                .append("unsigned char tab[").append(MAX_SIZE).append("] = {};\n")
                .append("int ptr = 0;\n")
                .append("int start = 0;\n")
                .append("int end = ").append(MAX_SIZE-1).append(";\n")
                .append(maxminptr());

        for (String instruction : functionInstructions) {
            init.append(instruction).append("\n");
        }
        init.append("int main(void) {\n\n");
        return init.toString();
    }

    public String endOfFile() {
        return "return 1;\n}";
    }

    private String maxminptr() {
        String MaxMinPtr;
        MaxMinPtr = "void MaxMinPtr(int i){\n";
        MaxMinPtr += "if(i > end || i < start) exit(2);\n";
        MaxMinPtr += "}\n\n";
        return MaxMinPtr;
    }

    @Override
    public void parseFile() {
        super.parseFile();
        System.out.println(initialize());
        for (String instruction : instructions) {
            System.out.println(instruction);
        }
        System.out.println(endOfFile());
    }

    @Override
    public IDeclaration declareFunction(boolean function) {
        return new CFunctionDeclaration(function);
    }

    @Override
    public void execute(String str) throws InvalidInstructionException {
        try {
            instructions.add(getCSyntax(str));
        } catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(42);
        }
    }

    class CFunctionDeclaration extends Parser.FunctionDeclaration {
        CFunctionDeclaration(boolean function) {
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
            String str = "int tmpPtr = ptr;\n" +
                    "int tmpStart;\n" +
                    "int tmpEnd;\n" +
                    "int size = " + struct.size + ";\n" +
                    "ptr = " + (MAX_SIZE - 1) + ";\n" +
                    "while (tab[ptr] == 0) {\n" +
                    "   ptr--;\n" +
                    "}\n" +
                    "ptr++;\n" +
                    "if (" + MAX_SIZE + " - ptr < size) {\n" +
                    "   fprintf(stderr, \"" + NOT_ENOUGH_MESSAGE + "\");\n" +
                    "   exit(" + NOT_ENOUGH_CODE + ");\n" +
                    "} else {\n" +
                    "   tmpStart = start;\n" +
                    "   tmpEnd = end;\n" +
                    "   start = ptr;\n" +
                    "   end = ptr + size - 1;\n" +
                    "}";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < struct.params.length; i++) {
                sb.append("tab[ptr+").append(struct.params[i]).append("]=").append(ARG_STRING).append(params[i]).append(";\n");
            }
            instructions.add(str);
            instructions.add(sb.toString());
            readText(code);
            instructions.add("ptr = end;\n" +
                    "while (ptr >= start) {\n" +
                    "   tab[ptr--] = 0;\n" +
                    "}\n" +
                    "start = tmpStart;\n" +
                    "end = tmpEnd;\n" +
                    "ptr = tmpPtr;\n" +
                    "}");
            instructions = tmp;
        }
    }
}

