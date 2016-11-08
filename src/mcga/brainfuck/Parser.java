package mcga.brainfuck;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * This class contains a bunch of methods used to parse the file containing the Brainf*ck code
 * and execute the code.
 */
public abstract class Parser {

    static long PROG_SIZE=0;
    static double EXEC_TIME=0;
    static long EXEC_POS =0;
    static long DATA_MOVE=0;
    static long DATA_WRITE=0;
    static long DATA_READ=0;
    static long EXEC_MOVE=0;

    private InputStream stream;
    public static final int SQUARE_SIDE = 3;
    public static final String EMPTY_INSTRUCTION = "000000";

    /**
     * In case a file is specified in the launching command, this constructor is called.
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
     * Reads the file containing the Brainfuck code. This method is called in each subclass, with some
     * additions depending on the subclass.
     * @see Check#parseFile()
     * @see Interpreter#parseFile()
     */
    public void parseFile() {
        Scanner scanner = new Scanner(this.stream);
        String str;
        scanner.useDelimiter("\\s*");
        while (scanner.hasNext()) {
            str = scanner.next();
            if (InstructionFactory.isLongSyntax(str)) {
                str += scanner.nextLine();
            }
            try {
                execute(str);
            } catch (InvalidInstructionException e) {
                System.err.println(e.getMessage());
                System.exit(42);
            }
        }
    }

    /**
     * Reads the bitmap image containing the Brainfuck code. This method is called in each subclass, with some
     * additions depending on the subclass.
     * @see Check#readBitmap()
     * @see Interpreter#readBitmap()
     */
    public void readBitmap() {
        try {
            BufferedImage image = ImageIO.read(stream);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height % SQUARE_SIDE != 0 || width % SQUARE_SIDE != 0) {
                throw new InvalidBitmapException();
            }

            String prevColor;
            String hexColor = "";
            for (int i = 0 ; i < height ; i += SQUARE_SIDE) {
                for (int j = 0 ; j < width ; j += SQUARE_SIDE) {
                    prevColor = colorToHex(new Color(image.getRGB(j, i))); // hexadecimal code of the color of the upper left pixel of the square
                    for (int iSquare = 0 ; iSquare < SQUARE_SIDE ; iSquare++) {
                        for (int jSquare = 0 ; jSquare < SQUARE_SIDE ; jSquare++) {
                            Color imgColor = new Color(image.getRGB(jSquare + j, iSquare + i));
                            hexColor = colorToHex(imgColor);
                            if (! prevColor.equals(hexColor)) {
                                throw new InvalidBitmapException();
                            }
                        }
                    }
                    if (! hexColor.equals(EMPTY_INSTRUCTION)) {
                        execute(hexColor);
                    }
                }
            }
        } catch (InvalidInstructionException e) {
            e.printStackTrace();
            System.exit(42);
        } catch (InvalidBitmapException e) {
            e.printStackTrace();
            System.exit(14);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    /**
     * Converts a Color object to its hexadecimal value.
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
     * @param str string value of the argument to interpret
     * @throws InvalidInstructionException
     * @see Check#execute(String)
     * @see Interpreter#execute(String)
     * @see Rewrite#execute(String)
     * @see Translate#execute(String)
     */
    public abstract void execute(String str) throws InvalidInstructionException;

    public String printMetrics() {
        return "PROG_SIZE = " + PROG_SIZE + '\n' +
                "EXEC_TIME = " + EXEC_TIME + " s" + '\n' +
                "EXEC_POS = " + EXEC_MOVE + '\n' +
                "DATA_MOVE = " + DATA_MOVE + '\n' +
                "DATA_READ = " + DATA_READ + '\n' +
                "DATA_WRITE = " + DATA_WRITE + '\n';
    }

}
