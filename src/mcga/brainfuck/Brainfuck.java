package mcga.brainfuck;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * mlo by lol on 23/09/2016.
 */
public class Brainfuck {
    private static final String fileArg = "-p";
    static Memory memoire = new Memory();
    static String inputStream;

    public static void main(String[] args) {
        Interpreter interpreter = null;
        int i;
        for (i = 0; i < args.length && !args[i].equals(fileArg); i++) {
        }
        if (i < args.length) {
            try {
                interpreter = new Interpreter(new FileInputStream(args[i + 1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            interpreter = new Interpreter();

        }

        interpreter.readFile();

        System.out.println(memoire);
    }

/**
 * ne sert à rien lol
 *
 * @param args
 * @return
 */
/*    static String recoverVales(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(fileArg)) {
                return args[i + 1];
            }
        }
        return "";
    }*/

}
