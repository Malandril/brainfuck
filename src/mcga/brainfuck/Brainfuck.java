package mcga.brainfuck;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mlo by lol on 23/09/2016.
 */
public class Brainfuck {
    private static final String fileArg = "-p";
    static Memory memoire = new Memory();
    static String inputStream;

    public static void main(String[] args) {
        String strArgs = Arrays.toString(args);
        Pattern pattern = Pattern.compile(".*" + fileArg + "[,\\s]*(.*)]");
        Matcher m = pattern.matcher(strArgs);
        Interpreter interpreter = null;
        if (m.matches()) {
            try {
                interpreter = new Interpreter(new FileInputStream(m.group(1)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            interpreter = new Interpreter();
        }
        interpreter.readFile();

        System.out.println(memoire);
    }

}
