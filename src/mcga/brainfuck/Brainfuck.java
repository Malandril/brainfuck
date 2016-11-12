package mcga.brainfuck;


import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
    static List<Parser> parsers = new ArrayList<>();

    /**
     * Main method, which executes the readArguments method and displaiys the values of the memory's cells.
     *
     * @param args DIfferent perameters accepted
     */
    public static void main(String[] args) {
        double startTime = System.nanoTime();

        readArguments(args);
        System.out.println();

        System.out.println();

        double endTime = System.nanoTime();
        System.out.println(memory);
        Metrics.EXEC_TIME = (endTime - startTime) * Math.pow(10, -9);
        System.out.println(Metrics.printMetrics());
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
            CommandLine line = commandParser.parse(options, args);
            if (line.hasOption(P.expression)) {
                file = new FileInputStream(line.getOptionValue(P.expression));
                if (line.hasOption(REWRITE.expression)) {
                    parsers.add(new Rewrite(file));
                }
                if (line.hasOption(CHECK.expression)) {
                    parsers.add(new Check(file));
                }
                if (line.hasOption(TRANSLATE.expression)) {
                    parsers.add(new Translate(file));
                }
                if (line.hasOption(TRACE.expression)) {
                    int index = line.getOptionValue(P.expression).lastIndexOf(".");
                    String bfFile = line.getOptionValue(P.expression);
                    if (index > 0) {
                        bfFile = bfFile.substring(0, index);
                    }
                    String logFile = bfFile + ".log";
                    parsers.add(new Trace(file, new PrintStream(logFile)));
                }
                if (parsers.isEmpty()) {
                    parsers.add(new Interpreter(file));
                }
            }
            if (line.hasOption(INPUT.expression)) {
                Input.stream = new FileInputStream(line.getOptionValue(INPUT.expression));
            }
            if (line.hasOption(OUTPUT.expression)) {
                Output.stream = new PrintStream(line.getOptionValue(OUTPUT.expression));
            }
            if (parsers.isEmpty()) {
                parsers.add(new Interpreter());
            }
            for (Parser parser : parsers) {
                if (line.hasOption(P.expression) && line.getOptionValue(P.expression).endsWith(FILE_SUFFIX)) {
                    parser.readBitmap();
                } else {
                    parser.parseFile();
                }
            }
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Error : " + exp.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(3);
        }
    }
}