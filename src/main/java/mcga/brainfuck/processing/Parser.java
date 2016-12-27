package mcga.brainfuck.processing;

import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.Macro;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.ProcedureStruct;
import mcga.brainfuck.exceptions.InvalidBitmapException;
import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidInstructionException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static mcga.brainfuck.InstructionCreator.SHORT_SYNTAX_INDEX;
import static mcga.brainfuck.InstructionCreator.hasInstruction;

/**
 * This class contains a bunch of methods used to parse the file containing the Brainf*ck code
 * and execute the code.
 *
 * @author Team Make Coding Great Again
 */
public abstract class Parser {
    public static final String PROCEDURE = "@";
    public static final String MACRO_PARAM_SEP = ",";
    public static final String PROC_PARAM_SEP = ";";
    static final int SQUARE_SIDE = 3;
    private static final String COM = "#";
    private static final String EMPTY_INSTRUCTION = "000000";
    public static Map<String, ProcedureStruct> procedures = new HashMap<>();
    private List<Macro> macroSet = new ArrayList<>();
    private InputStream stream;
    private String fileName;


    public Parser(String fileName) throws FileNotFoundException {
        this(new FileInputStream(fileName));
        this.fileName = fileName;

    }

    /**
     * In case a file is specified in the launching command, this constructor is called.
     *
     * @param stream Stream from file
     */
    public Parser(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Default constructor.
     * By default, the input stream is System.in.
     */
    public Parser() {
        this(System.in);
    }

    /**
     * Searches for the short syntax representation corresponding to a long syntax representation.
     *
     * @param longStr String corresponding to the long syntax of an instruction.
     * @return String corresponding to the short representation of the instruction.
     * @throws InvalidInstructionException
     */
    public static String getShortSyntax(String longStr) throws InvalidInstructionException {
        InstructionCreator creator = hasInstruction(longStr);
        if (creator != null) {
            return creator.getIdentifier(SHORT_SYNTAX_INDEX);
        } else {
            throw new InvalidInstructionException();
        }
    }

    /**
     * Tests the first character to determine if the String is made of several short syntax instructions
     * or a single long syntax instruction.
     *
     * @param str String of command
     * @return true if it is a long syntax, false otherwise
     */
    public static boolean isLetter(String str) {
        char firstChar = str.charAt(0);
        return Character.isLetter(firstChar);
    }

    /**
     * Tests if the String is a macro declaration beginning with a '$'
     *
     * @param str String to test
     * @return true if it is a macro declaration, false otherwise
     */
    public static boolean isMacroDeclaration(String str) {
        return str.startsWith(Macro.MACRO);
    }

    /**
     * Tests if the String is the start of a comment
     *
     * @param str String to test
     * @return true if it is a '#', false otherwise
     * @throws InvalidInstructionException
     */
    public static boolean isComments(String str) throws InvalidInstructionException {
        return str.equals(COM);
    }

    public static boolean isProcedureDeclaration(String str) {
        return str.equals(PROCEDURE);
    }

    /**
     * Reads the file containing the Brainf*ck code. This method is called in each subclass, with some
     * additions depending on the subclass.
     *
     * @see Check#parseFile()
     * @see Interpreter#parseFile()
     */
    public void parseFile() {
        if (fileName != null && fileName.endsWith(".bmp")) {
            readBitmap();
        } else {
            readText();
        }

    }

    /**
     * Replaces the macros by their command values
     *
     * @param str String to modify
     * @return String after the modification of the macros
     * @throws InvalidInstructionException thrown if tries to parse an invalid Instruction
     */
    public String getCorrectSyntax(String str) throws InvalidInstructionException {
        String s = str.replaceAll("\\s*#.*", "");
        for (Macro macro : macroSet) {
            s = macro.callMacro(s);
        }
        return s;
    }

    /**
     * Reads the bitmap image containing the Brainfuck code. This method is called in each subclass, with some
     * additions depending on the subclass.
     *
     * @see Check#readBitmap()
     * @see Interpreter#readBitmap()
     */
    private void readBitmap() {
        try {
            Metrics.setProgSize(0);
            BufferedImage image = ImageIO.read(stream);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height % SQUARE_SIDE != 0 || width % SQUARE_SIDE != 0) {
                throw new InvalidBitmapException();
            }

            String prevColor;
            String hexColor = "";
            for (int i = 0; i < height; i += SQUARE_SIDE) {
                for (int j = 0; j < width; j += SQUARE_SIDE) {
                    prevColor = colorToHex(new Color(image.getRGB(j, i))); // hexadecimal code of the color of the upper left pixel of the square
                    for (int iSquare = 0; iSquare < SQUARE_SIDE; iSquare++) {
                        for (int jSquare = 0; jSquare < SQUARE_SIDE; jSquare++) {
                            Color imgColor = new Color(image.getRGB(jSquare + j, iSquare + i));
                            hexColor = colorToHex(imgColor);
                            if (!prevColor.equals(hexColor)) {
                                throw new InvalidBitmapException();
                            }
                        }
                    }
                    if (!hexColor.equals(EMPTY_INSTRUCTION)) {
                        execute(hexColor);
                        Metrics.incrProgSize();
                    }
                }
            }
        } catch (InvalidInstructionException e) {
            e.printStackTrace();
            System.exit(InvalidInstructionException.EXIT_CODE);
        } catch (InvalidBitmapException e) {
            e.printStackTrace();
            System.exit(InvalidBitmapException.EXIT_CODE);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    private void readText() {
        Scanner scanner = new Scanner(this.stream);
        scanFile(scanner);
    }

    public void readText(String str) {
        Scanner scanner = new Scanner(str);
        scanFile(scanner);
    }

    private void scanFile(Scanner scanner) {
        Metrics.setProgSize(0);
        String str;
        String macroLine[];
        String macroName;
        String macroValue;
        String procedureTab[];
        String procedureName;
        String procedureCode;
        scanner.useDelimiter("\\s*");
        try {
            while (scanner.hasNext()) {
                str = scanner.next();
                if (isLetter(str)) {
                    str += scanner.nextLine();
                    str = getCorrectSyntax(str);
                    if (hasInstruction(str) == null && (procedures.get(str)) == null) {
                        str = str.replaceAll("\\s*", "");
                        for (int i = 0; i < str.length(); i++) {
                            String in = str.substring(i, i + 1);
                            Metrics.incrProgSize();
                            execute(in);
                        }
                    } else {
                        Metrics.incrProgSize();
                        execute(str);
                    }
                } else if (isComments(str)) {
                    scanner.nextLine();
                } else if (isMacroDeclaration(str)) {
                    macroDelcaration(scanner);
                } else if (isProcedureDeclaration(str)) {
                    procedureDeclaration(scanner);
                } else {
                    Metrics.incrProgSize();
                    execute(str);
                }
            }
        } catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(InvalidInstructionException.EXIT_CODE);
        }
    }

    private void macroDelcaration(Scanner scanner) throws InvalidInstructionException {
        String[] macroLine;
        String macroValue;
        String macroName;
        macroLine = scanner.nextLine().split(Macro.MACRO_SEP);
        Pattern pat = Pattern.compile(Macro.MACRO_REGX);
        Matcher matcher = pat.matcher(macroLine[0]);
        macroValue = getCorrectSyntax(macroLine[1]);
        String params[] = {};
        if (matcher.find()) {
            params = matcher.group(2).split(MACRO_PARAM_SEP);
            macroName = matcher.group(1);
        } else {
            macroName = macroLine[0];
        }
        Macro macro = new Macro(macroName, macroValue, params);
        if (!macroSet.contains(macro)) {
            macroSet.add(macro);
        } else {
            throw new InvalidCodeException("Macro déjà définie " + macroName);
        }
    }

    private void procedureDeclaration(Scanner scanner) throws InvalidInstructionException {
        String[] procedureTab;
        String procedureCode;
        String procedureName;
        procedureTab = scanner.nextLine().split("=");
        Pattern pat = Pattern.compile(Macro.MACRO_REGX);
        Matcher matcher = pat.matcher(procedureTab[0]);
        procedureCode = getCorrectSyntax(procedureTab[1]);
        String params[] = {};
        if (matcher.find()) {
            params = matcher.group(2).split(PROC_PARAM_SEP);
            procedureName = matcher.group(1);
        } else {
            procedureName = procedureTab[0];
        }
        if (!procedures.containsKey(procedureName)) {
            procedures.put(procedureName, new ProcedureStruct(procedureCode, params));
        } else {
            throw new InvalidCodeException("Procédure déjà définie " + procedureName);
        }
    }

    /**
     * Converts a Color object to its hexadecimal value.
     *
     * @param color Color to convert.
     * @return String corresponding hexadecimal value
     */
    private String colorToHex(Color color) {
        String hexColor = "";
        hexColor += String.format("%02X", color.getRed());
        hexColor += String.format("%02X", color.getGreen());
        hexColor += String.format("%02X", color.getBlue());
        return hexColor;
    }


    /**
     * This method is overriden in all subclasses.
     *
     * @param str string value of the argument to interpret
     * @throws InvalidInstructionException
     * @see Check#execute(String)
     * @see Interpreter#execute(String)
     * @see Rewrite#execute(String)
     * @see Translate#execute(String)
     */
    public abstract void execute(String str) throws InvalidInstructionException;

}
