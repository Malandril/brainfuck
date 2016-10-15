package mcga.brainfuck;


import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

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
        Options options=createOptions();
        CommandLineParser cParser = new DefaultParser();
        FileInputStream file = null;
        try {
            PrintStream originalOut=System.out;
            InputStream originalIn=System.in;
            Parser parser = new Interpreter();
            CommandLine line = cParser.parse( options, args );
            if(line.hasOption("p")){
                try {
                    file = new FileInputStream(line.getOptionValue("p"));
                    if(line.hasOption("rewrite")) {
                        parser = new Rewrite(file);
                    }else if(line.hasOption("check")){
                        parser= new Check(file);
                    }else{
                        parser = new Interpreter(file);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if( line.hasOption("i")){
                try {
                    FileInputStream in=new FileInputStream(line.getOptionValue("i"));
                    System.setIn(in);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if( line.hasOption("o")){
                try {
                    PrintStream out = new PrintStream(line.getOptionValue("o"));
                    System.setOut(out);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            parser.parseFile();
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        catch( ParseException exp ) {
            System.err.println( "Parsing failed.  Error : " + exp.getMessage() );
        }
    }
}
