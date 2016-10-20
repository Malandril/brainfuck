package mcga.brainfuck;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_BGR;

/**
 * Class defining the actions to do when the user wants to translate his Brainf*ck code in a bitmap image.
 * Because this class reads a file, it extends Parser.
 */
public class Translate extends Parser {
    List<Color> colorList=new ArrayList<>();
    public static final String FILE_FORMAT="bmp";

    /**
     * Default constructor.
     * @see Parser#Parser()
     */
    public Translate(){
        super();
    }

    /**
     * Constructor of the class.
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public Translate(InputStream stream){
        super(stream);
    }

    /**
     * For each instruction, adds to colorList the corresponding color.
     * @param str String corresponding to an instruction
     * @throws InvalidInstructionException
     * @see Parser#execute(String)
     */
    public void execute(String str) throws InvalidInstructionException {
        colorList.add(Color.decode(InstructionFactory.getBitmapColorIndex(str)));
    }

    /**
     * Creates the bitmap image and draws each instruction.
     * @see Parser#parseFile()
     */
    @Override
    public void parseFile(){
        super.parseFile();
        int cote=(int) Math.ceil(Math.sqrt(colorList.size()))*SQUARE_SIDE;
        BufferedImage image=new BufferedImage(cote,cote,TYPE_INT_BGR);
        int indexList=0;
        for (int i = 0; i < cote; i += SQUARE_SIDE) {
            for (int j = 0; j < cote && indexList<colorList.size(); j += SQUARE_SIDE) {
                for (int iSquare = 0; iSquare < SQUARE_SIDE; iSquare++) {
                    for (int jSquare = 0; jSquare < SQUARE_SIDE; jSquare++) {
                        image.setRGB(j+jSquare,i+iSquare,colorList.get(indexList).getRGB());
                    }
                }
                indexList++;
            }
        }
        File file = new File("translation."+FILE_FORMAT);
        try {
            ImageIO.write(image,FILE_FORMAT,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
