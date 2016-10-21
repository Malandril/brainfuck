package mcga.brainfuck;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import static java.awt.image.BufferedImage.TYPE_INT_BGR;

/**
 * Class defining the actions to do when the user wants to translate his Brainf*ck code in a bitmap image.
 * Because this class reads a file, it extends Parser.
 */
public class Translate extends Parser {
    public static final String FILE_FORMAT = "bmp";
    Queue<Color> colorFifo = new LinkedList<>();


    /**
     * Constructor of the class.
     *
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public Translate(InputStream stream) {
        super(stream);
    }

    /**
     * For each instruction, adds to colorFifo the corresponding color.
     *
     * @param str String corresponding to an instruction
     * @throws InvalidInstructionException
     * @see Parser#execute(String)
     */
    public void execute(String str) throws InvalidInstructionException {
        colorFifo.add(Color.decode(InstructionFactory.getBitmapColorIndex(str)));
    }

    /**
     * Creates the bitmap image and draws each instruction.
     *
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile() {
        super.parseFile();
        writeBitmap();
    }

    /**
     * Writes the color queue into a buffered image and writes the buffered image into a bmp file
     */
    public void writeBitmap(){
        int cote = (int) Math.ceil(Math.sqrt(colorFifo.size())) * SQUARE_SIDE;
        BufferedImage image = new BufferedImage(cote, cote, TYPE_INT_BGR);
        for (int i = 0; i < cote; i += SQUARE_SIDE) {
            for (int j = 0; j < cote && !colorFifo.isEmpty(); j += SQUARE_SIDE) {
                int colorInt = colorFifo.poll().getRGB();
                for (int iSquare = 0; iSquare < SQUARE_SIDE; iSquare++) {
                    for (int jSquare = 0; jSquare < SQUARE_SIDE; jSquare++) {
                        image.setRGB(j + jSquare, i + iSquare, colorInt);
                    }
                }
            }
        }
        File file = new File("translation." + FILE_FORMAT);
        try {
            ImageIO.write(image, FILE_FORMAT, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}