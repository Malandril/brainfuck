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
 * @author Team Make Coding Great Again
 *         Created the 22/12/2016.
 */
public class ToC extends Parser {
    private static final String ARG_STRING = "p";
    private List<String> instructions = new ArrayList<>();
    private List<String> functionInstructions = new ArrayList<>();
    private PrintStream outputStream;
    
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
    
    
    private String initialize() {
        StringBuilder init = new StringBuilder();
        init.append("#include <stdio.h> \n").append("#include <stdlib.h> \n\n").append("unsigned char tab[").append(MAX_SIZE).append("] = {};\n").append("int ptr = 0;\n").append("int start = 0;\n").append("int end = ").append(MAX_SIZE - 1).append(";\n").append(maxminptr());
        
        for (String instruction : functionInstructions) {
            init.append(instruction).append("\n");
        }
        init.append("int main(void) {\n\n");
        return init.toString();
    }
    
    private String endOfFile() {
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
    public void parseFile() throws InvalidCodeException {
        super.parseFile();
        outputStream.println(initialize());
        for (String instruction : instructions) {
            outputStream.println(instruction);
        }
        outputStream.println(endOfFile());
    }
    
    @Override
    public IDeclaration declareFunction(boolean function) {
        return new ToCFunctionDeclaration(function);
    }
    
    @Override
    public void execute(String str) throws InvalidCodeException {
            instructions.add(getCSyntax(str));
    }
    
    private class ToCFunctionDeclaration extends FunctionDeclaration {
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
            instructions.add("void " + name + "(" + sj.toString() + "){");
            String str = "int tmpPtr = ptr;\n" + "int tmpStart;\n" + "int tmpEnd;\n" + "int size = " + struct.getSize() + ";\n" + "ptr = " + (MAX_SIZE - 1) + ";\n" + "while (tab[ptr] == 0) {\n" + "   ptr--;\n" + "}\n" + "ptr++;\n" + "if (" + MAX_SIZE + " - ptr < size) {\n" + "   fprintf(stderr, \"" + NOT_ENOUGH_MESSAGE + "\");\n" + "   exit(" + NOT_ENOUGH_CODE + ");\n" + "} else {\n" + "   tmpStart = start;\n" + "   tmpEnd = end;\n" + "   start = ptr;\n" + "   end = ptr + size - 1;\n" + "}";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < struct.getDeclarationParmsSize(); i++) {
                sb.append("tab[ptr+").append(struct.getDeclarationParam(i)).append("]=").append(ARG_STRING).append(params[i]).append(";\n");
            }
            instructions.add(str);
            instructions.add(sb.toString());
            readText(code);
            instructions.add("ptr = end;\n" + "while (ptr >= start) {\n" + "   tab[ptr--] = 0;\n" + "}\n" + "start = tmpStart;\n" + "end = tmpEnd;\n" + "ptr = tmpPtr;\n" + "}");
            instructions = tmp;
        }
    }
}

