package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.instructions.*;
import mcga.brainfuck.processing.Parser;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This enum links the keywords in the Brainf*ck code to the instruction they correspond to.
 *
 * @author Team Make Coding Great Again
 */
public enum InstructionCreator {
    INCR("INCR", "+", "FFFFFF", "tab[ptr]++;"),
    DECR("DECR", "-", "4B0082", "tab[ptr]--;"),
    LEFT("LEFT", "<", "9400D3", "ptr--;\n MaxMinPtr(ptr);"),
    RIGHT("RIGHT", ">", "0000FF", "ptr++;\n MaxMinPtr(ptr);"),
    IN("IN", ",", "FFFF00", "tab[ptr]=(unsigned char) getchar();"),
    OUT("OUT", ".", "00FF00", "putchar(tab[ptr]);"),
    JUMP("JUMP", "[", "FF7F00", "while(tab[ptr]){"),
    BACK("BACK", "]", "FF0000", "}");


    public static final int SHORT_SYNTAX_INDEX = 1;
    public static final int BITMAP_COLOR_INDEX = 2;
    public static final int C_SYNTAX_INDEX = 3;
    public static Pattern patternProc = Pattern.compile(Parser.CALL_PATTERN);

    private List<String> identifiers;

    /**
     * Constructor of the enum
     *
     * @param names List<String> containing the possible syntax for each instruction.
     */
    InstructionCreator(String... names) {
        this.identifiers = Arrays.asList(names);
    }

    //
//    /**
//     * Searches for the short syntax representation corresponding to a long syntax representation.
//     *
//     * @param longStr String corresponding to the long syntax of an instruction.
//     * @return String corresponding to the short representation of the instruction.
//     * @throws InvalidInstructionException
//     */
//    public static String getShortSyntax(String longStr) throws InvalidInstructionException {
//        return getInstruction(longStr).identifiers.get(SHORT_SYNTAX_INDEX);
//    }
    public static String getCSyntax(String str) throws InvalidInstructionException {
        InstructionCreator inst = getInstruction(str);
        if (inst != null) {
            return inst.identifiers.get(C_SYNTAX_INDEX);
        } else {
            Matcher matcher = patternProc.matcher(str);
            if (matcher.find()) {
                ProcedureStruct struct = Parser.getProcedure(matcher.group(1));
                if (struct != null) {
                    String paramGroup = matcher.group(2);
                    String params = "";
                    StringJoiner sj = new StringJoiner(",");
                    if (paramGroup != null) {
//                        params = paramGroup.replaceAll(";", ",");
                        String[] split = paramGroup.split(Parser.PROC_PARAM_SEP);
                        for (String s : split) {
                            int address = 0;
                            for (int i = 0; i < s.length(); i++) {
                                String s1 = s.substring(i, i + 1);
                                if (RIGHT.isIdentifier(s1)) {
                                    address++;
                                } else if (LEFT.isIdentifier(s1)) {
                                    address--;
                                }
                            }
                            sj.add("tab[ptr+" + address + "]");

                        }
                    }
                    if (struct.function) {
                        return "tab[ptr]=" + matcher.group(1) + "(" + sj.toString() + ");";
                    } else {
                        return matcher.group(1) + "(" + sj.toString() + ");";
                    }
                }
            }
        }
        throw new InvalidInstructionException(str);
    }

    /**
     * Searches for the bitmap color index corresponding to a long or short syntax representation.
     *
     * @param syntax String corresponding to the short or long syntax of an instruction.
     * @return Bitmap color index corresponding to the short representation of the instruction.
     * @throws InvalidInstructionException
     */
    public static String getBitmapColorIndex(String syntax) throws InvalidInstructionException {
        InstructionCreator inst = getInstruction(syntax);
        if (inst != null) {
            return "#" + inst.identifiers.get(BITMAP_COLOR_INDEX);
        }
        throw new InvalidInstructionException(syntax);
    }

    /**
     * Finds the instruction (if it exists) corresponding to the String in parameter.
     *
     * @param str String to check.
     * @return Instruction corresponding
     * @throws InvalidInstructionException
     */
    public static InstructionCreator getInstruction(String str) {
        for (InstructionCreator instructionCreator : InstructionCreator.values()) {
            if (instructionCreator.identifiers.contains(str)) {
                return instructionCreator;
            }
        }
        return null;

    }

    /**
     * Creates an Instruction object and, for each possible instruction, creates the object corresponding.
     *
     * @param s String to try to convert to an instruction.
     * @return Instruction corresponding to the String in parameter.
     * @throws InvalidInstructionException
     */
    public static Instruction createInstruction(String s) throws InvalidInstructionException {
        InstructionCreator inst = getInstruction(s);
        Instruction instruction;
        if (inst != null) {
            switch (inst) {
                case INCR:
                    instruction = new Increment();
                    break;
                case DECR:
                    instruction = new Decrement();
                    break;
                case LEFT:
                    instruction = new Left();
                    break;
                case RIGHT:
                    instruction = new Right();
                    break;
                case JUMP:
                    instruction = new Jump();
                    break;
                case BACK:
                    instruction = new Back();
                    break;
                case IN:
                    instruction = new Input();
                    break;
                case OUT:
                    instruction = new Output();
                    break;
                default:
                    throw new InvalidInstructionException(s);
            }
            return instruction;
        } else {
            Matcher matcher = patternProc.matcher(s);
            if (matcher.find()) {
                ProcedureStruct struct = Parser.getProcedure(matcher.group(1));
                if (struct != null) {
                    String paramGroup = matcher.group(2);
                    String[] params = new String[0];
                    if (paramGroup != null) {
                        params = paramGroup.split(";");
                    }
                    if (struct.function) {
                        return new Function(s, struct.instructions, struct.size, struct.params, params);
                    } else {
                        return new Procedure(s, struct.instructions, struct.size, struct.params, params);
                    }
                }
            }
        }
        throw new InvalidInstructionException(s);
    }

    public String getIdentifier(int i) {
        return this.identifiers.get(i);
    }

    public boolean isIdentifier(String str) {
        return this.identifiers.contains(str);
    }
}
