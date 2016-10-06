package mcga.brainfuck;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static mcga.brainfuck.Arguments.*;

/**
 * mlo by lol on 23/09/2016.
 */
public class Brainfuck {

    private static final String fileArg = "-p";
    static Memory memoire = new Memory();

    public static void main(String[] args) {
        readArguments(args);
        System.out.println(memoire);
    }

    public static void readArguments(String[] args) {
        boolean rewriteTest=false;
        Interpreter interpreter = new Interpreter();
        for (int i = 0 ; i < args.length ; i++) {
            String arg = args[i];
            if (arg.equals(P.expression)) {
                try {
                    interpreter = new Interpreter(new FileInputStream(args[i + 1]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (arg.equals(REWRITE.expression)) {
                rewriteTest=true;
            }
        }
        if(rewriteTest){
            interpreter.rewriteFile();
        }
        else {
            interpreter.readFile();
        }
    }
}
