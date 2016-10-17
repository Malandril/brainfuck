package mcga.brainfuck;


import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static mcga.brainfuck.Arguments.createOptions;

/**
 * made By Everyone on 23/09/2016.
 */
public class Brainfuck {

    static Memory memoire = new Memory();

    public static void main(String[] args) {
        readArguments(args);
        System.out.println();
        System.out.println(memoire);
    }

    public static void readArguments(String[] args) {
        Output.stream = System.out;
        Input.stream = System.in;
        Options options = createOptions();
        CommandLineParser cParser = new DefaultParser();
        FileInputStream file = null;
        try {
            Parser parser = new Interpreter();
            CommandLine line = cParser.parse(options, args);
            if (line.hasOption("p")) {
                file = new FileInputStream(line.getOptionValue("p"));
                if (line.hasOption("rewrite")) {
                    parser = new Rewrite(file);
                } else if (line.hasOption("check")) {
                    parser = new Check(file);
                } else {
                    parser = new Interpreter(file,line.getOptionValue("p"));
                }
            }
            if (line.hasOption("i")) {
                Input.stream = new FileInputStream(line.getOptionValue("i"));
            }
            if (line.hasOption("o")) {
                Output.stream = new PrintStream(line.getOptionValue("o"));
            }
            parser.parseFile();
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Error : " + exp.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
