package mcga.brainfuck.processing;

import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidInstructionException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.awt.image.BufferedImage.TYPE_INT_BGR;

/**
 * Class defining the actions to do when the user wants to translate his Brainf*ck code in a bitmap image.
 * @author Team Make Coding Great Again
 */
public class Translate extends Parser {
    private Deque<Color> colorFifo = new ArrayDeque<>();


    public Translate() {
        super();
    }

    /**
     * Constructor with the name of file
     * @param fileName String containing the name of the file
     * @throws FileNotFoundException
     * @see Parser#Parser()
     */

    public Translate(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Constructor with a FileInputStream
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public Translate(FileInputStream stream) {
        super(stream);
    }

    /**
     * For each instruction, adds to colorFifo the corresponding color.
     * @param str String corresponding to an instruction
     * @throws InvalidInstructionException
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) throws InvalidInstructionException {
        colorFifo.add(Color.decode(InstructionCreator.getBitmapColorIndex(str)));
    }

    /**
     * Overrides the method of the Parser class to create the bitmap image and draw each instruction.
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile() throws InvalidCodeException {
        super.parseFile();
        writeBitmap();
    }

    /**
     * Uses the color queue to color the blocks of pixels of a buffered image and writes the buffered image
     * into a bmp file
     */
    public void writeBitmap() {
        int cote = (int) Math.ceil(Math.sqrt(colorFifo.size())) * SQUARE_SIDE;
        BufferedImage image = new BufferedImage(cote, cote, TYPE_INT_BGR);
        for (int i = 0 ; i < cote ; i += SQUARE_SIDE) {
            for (int j = 0 ; j < cote && ! colorFifo.isEmpty() ; j += SQUARE_SIDE) {
                int colorInt = colorFifo.poll().getRGB();
                for (int iSquare = 0 ; iSquare < SQUARE_SIDE ; iSquare++) {
                    for (int jSquare = 0 ; jSquare < SQUARE_SIDE ; jSquare++) {
                        image.setRGB(j + jSquare, i + iSquare, colorInt);
                    }
                }
            }
        }
    }
}
