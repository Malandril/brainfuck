package mcga.brainfuck;


import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static mcga.brainfuck.Arguments.*;

/**
 * Main class of the project.
 * Contains the main method.
 *
 * @author Team Make Coding Great Again
 */
public class Brainfuck {

    public static final String FILE_SUFFIX = "bmp";
    static Memory memory = new Memory();

    /**
     * Main method, which executes the readArguments method and displays the values of the memory's cells.
     *
     * @param args DIfferent perameters accepted
     */
    public static void main(String[] args) {
        readArguments(args);
        System.out.println();
        System.out.println(memory);
    }

    /**
     * Reads the provided arguments, determines the actions they correspond to and executes them.
     *
     * @param args
     */

    public static void readArguments(String[] args) {
        Output.stream = System.out;
        Input.stream = System.in;
        Options options = createOptions();
        CommandLineParser commandParser = new DefaultParser();
        FileInputStream file;
        try {
            Parser parser = new Interpreter();
            CommandLine line = commandParser.parse(options, args);
            if (line.hasOption(P.expression)) {
                file = new FileInputStream(line.getOptionValue(P.expression));
                if (line.hasOption(REWRITE.expression)) {
                    parser = new Rewrite(file);
                } else if (line.hasOption(CHECK.expression)) {
                    parser = new Check(file);
                } else if (line.hasOption(TRANSLATE.expression)) {
                    parser = new Translate(file);
                } else {
                    parser = new Interpreter(file);
                }
            }
            if (line.hasOption(INPUT.expression)) {
                Input.stream = new FileInputStream(line.getOptionValue(INPUT.expression));
            }
            if (line.hasOption(OUTPUT.expression)) {
                Output.stream = new PrintStream(line.getOptionValue(OUTPUT.expression));
            }
            if (line.hasOption(P.expression) && line.getOptionValue(P.expression).endsWith(FILE_SUFFIX)) {
                parser.readBitmap();
            } else {
                parser.parseFile();
            }
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Error : " + exp.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(3);
        }
    }
}