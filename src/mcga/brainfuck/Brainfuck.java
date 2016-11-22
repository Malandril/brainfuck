package mcga.brainfuck;


import mcga.brainfuck.instructions.Input;
import mcga.brainfuck.processing.*;
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

    private static final String FILE_SUFFIX = "bmp";
    private static Memory memory = new Memory();
    private static List<mcga.brainfuck.processing.Parser> parsers = new ArrayList<>();

    public static Memory getMemory() {
        return memory;
    }

    public static void setMemory(Memory memory) {
        Brainfuck.memory = memory;
    }

    public static Interpreter getInterpreter() {
        return (Interpreter) parsers.get(0);
    }

    /**
     * Main method, which executes the readArguments method and displays the values of the memory's cells.
     *
     * @param args Different parameters accepted
     */
    public static void main(String[] args) {
        double startTime = System.nanoTime();
        readArguments(args);
        System.out.println();
        double endTime = System.nanoTime();
        System.out.println(memory);
        Metrics.setExecTime((endTime - startTime) * Math.pow(10, -6));
        System.out.println(Metrics.printMetrics());
    }

    /**
     * Reads the provided arguments, determines the actions they correspond to and executes them.
     *
     * @param args
     */

    public static void readArguments(String[] args) {
        Input.stream = System.in;
        Options options = createOptions();
        CommandLineParser commandParser = new DefaultParser();
        try {
            CommandLine line = commandParser.parse(options, args);
            String pValue = line.getOptionValue(P.expression);
            if (line.hasOption(P.expression)) {
                boolean hasTrace = line.hasOption(TRACE.expression);
                boolean hasRewrite = line.hasOption(REWRITE.expression);
                boolean hasCheck = line.hasOption(CHECK.expression);
                boolean hasTranslate = line.hasOption(TRANSLATE.expression);
                if (hasCheck || hasRewrite || hasTranslate) {
                    if (hasRewrite) {
                        parsers.add(new Rewrite(pValue));
                    }
                    if (hasCheck) {
                        parsers.add(new Check(pValue));
                    }
                    if (hasTranslate) {
                        parsers.add(new Translate(pValue));
                    }

                } else if (hasTrace) {
                    int index = pValue.lastIndexOf(".");
                    String bfFile = pValue;
                    if (index > 0) {
                        bfFile = bfFile.substring(0, index);
                    }
                    String logFile = bfFile + ".log";
                    System.setErr(new PrintStream(logFile));
                    parsers.add(new Trace(pValue));
                }
                if (parsers.isEmpty()) {
                    parsers.add(new Interpreter(pValue));
                }
            }
            if (line.hasOption(INPUT.expression)) {
                Input.stream = new FileInputStream(line.getOptionValue(INPUT.expression));
            }
            if (line.hasOption(OUTPUT.expression)) {
                System.setOut(new PrintStream(line.getOptionValue(OUTPUT.expression)));
            }
            if (parsers.isEmpty()) {
                parsers.add(new Interpreter());
            }
            for (mcga.brainfuck.processing.Parser parser : parsers) {
                if (line.hasOption(P.expression) && pValue.endsWith(FILE_SUFFIX)) {
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