package mcga.brainfuck;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by user on 12/10/2016.
 */
public class Interpreter extends Parser {
    public static final String fileSuffix = "bmp";
    public static final int SQUARE_SIDE = 3;
    public static final String EMPTY_INSTRUCTION = "000000";
    public static List <Instruction> executionList;
    public static List <List <Instruction>> executionLists = new ArrayList <>();
    public static Stack <Instruction> executionStack = new Stack <>();
    public static boolean inLoop = false;
    public static int count = 0;
    public static int countJump = 0;
    public static int countBack = 0;
    private String fileName;

    public Interpreter() {
        super();
        this.fileName = "";
    }

    public Interpreter(InputStream stream, String fileName) {
        this.stream = stream;
        this.fileName = fileName;
    }

    @Override
    public void parseFile() {
        if (! this.fileName.endsWith(fileSuffix)) {
            super.parseFile();
        } else {
            readBitmap();
        }
    }

    @Override
    public void execute(String str) throws InvalidInstructionException {
        try {
            Instruction instruction = InstructionFactory.getInstruction(str);
            executionList = new ArrayList <>();
            if (instruction.getClass().equals(Jump.class)) {
                count++;
                inLoop = true;
            } else if (instruction.getClass().equals(Back.class)) {
                count--;
                if (count == 0) {
                    inLoop = false;
                }
            }
            if (inLoop) {
                executionList.add(instruction);
                executionLists.add(executionList);
                System.out.println(executionList);
                //executeList(executionLists);
            }
            instruction.interpret();
        } catch (InvalidValueException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }
    }

    public void executeList(List <List <Instruction>> list) throws InvalidInstructionException {
        for (List <Instruction> list1 : list) {
            for (Instruction instruction : list1) {
                int counter = 0;
                executionList = new ArrayList <>();
                if (instruction.getClass().equals(Jump.class)) {
                    counter++;
                    inLoop = true;
                } else if (instruction.getClass().equals(Back.class)) {
                    counter--;
                    if (counter == 0) {
                        inLoop = false;
                    }
                }
                if (inLoop) {
                    list.add(list1);
                }

            }
            System.out.println(list);
            System.out.println(list1);
        }
    }

    public void readBitmap() {
        try {
            BufferedImage image = ImageIO.read(stream);
            int height = image.getHeight();
            int width = image.getWidth();
            if (height % SQUARE_SIDE != 0 || width % SQUARE_SIDE != 0) {
                throw new InvalidBitmapException();
            }
            String prevColor = "";
            String hexColor = "";
            for (int i = 0 ; i < height ; i += SQUARE_SIDE) {
                for (int j = 0 ; j < width ; j += SQUARE_SIDE) {
                    for (int iSquare = 0 ; iSquare < SQUARE_SIDE ; iSquare++) {
                        for (int jSquare = 0 ; jSquare < SQUARE_SIDE ; jSquare++) {
                            hexColor = "";
                            Color imgColor = new Color(image.getRGB(jSquare + j, iSquare + i));
                            hexColor += String.format("%02X", imgColor.getRed());
                            hexColor += String.format("%02X", imgColor.getGreen());
                            hexColor += String.format("%02X", imgColor.getBlue());
                            if (! prevColor.equals(hexColor) && iSquare != 0 && jSquare != 0) {
                                throw new InvalidBitmapException();
                            }
                            prevColor = hexColor;

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


}
