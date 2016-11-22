package mcga.brainfuck.processing;

import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidBitmapException;
import mcga.brainfuck.exceptions.InvalidInstructionException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class contains a bunch of methods used to parse the file containing the Brainf*ck code
 * and execute the code.
 */
public abstract class Parser {
    public static final String MACRO = "$";
    static final int SQUARE_SIDE = 3;
    private static final String COM = "#";
    private static final String EMPTY_INSTRUCTION = "000000";
    Map<String, String> macroMap = new HashMap<>();
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
        return InstructionCreator.hasInstruction(longStr).getIdentifier(InstructionCreator.SHORT_SYNTAX_INDEX);
    }

    /**
     * Tests the first character to determine if the String is made of several short syntax instructions
     * or a single long syntax instruction.
     *
     * @param str
     * @return
     */
    public static boolean isLongSyntax(String str) {
        char firstChar = str.charAt(0);
        return Character.isLetter(firstChar);
    }

    public static boolean isMacroDeclaration(String str) {
        return str.startsWith(MACRO);
    }

    public static boolean isComments(String str) throws InvalidInstructionException {
        return str.equals(COM);
    }

    /**
     * Reads the file containing the Brainfuck code. This method is called in each subclass, with some
     * additions depending on the subclass.
     *
     * @see Check#parseFile()
     * @see Interpreter#parseFile()
     */
    public void parseFile() {
        Metrics.setProgSize(0);
        Scanner scanner = new Scanner(this.stream);
        String str;
        String macro[];
        scanner.useDelimiter("\\s*");
        try {
            while (scanner.hasNext()) {
                str = scanner.next();
                if (isLongSyntax(str)) {
                    str += scanner.nextLine();
                    str = getLongSyntax(str);
                    if (InstructionCreator.hasInstruction(str) == null) {
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
                    macro = scanner.nextLine().split("=");
                    macroMap.put(macro[0], getLongSyntax(macro[1]));
                } else {
                    Metrics.incrProgSize();
                    execute(str);
                }
            }
        } catch (InvalidInstructionException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param str
     * @return
     * @throws InvalidInstructionException
     */
    public String getLongSyntax(String str) throws InvalidInstructionException {
        String s = str.replaceAll("\\s*|\\s*#.*", "");
        for (Map.Entry<String, String> entry : macroMap.entrySet()) {
            s = s.replaceAll(entry.getKey(), entry.getValue());
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
    public void readBitmap() {
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

    /**
     * Converts a Color object to its hexadecimal value.
     *
     * @param color Color to convert.
     * @return String corresponding hexadecimal value
     */
    public String colorToHex(Color color) {
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
