package mcga.brainfuck;

import java.util.Arrays;
import java.util.List;

/**
 * The Instructions enum lists the possible instructions and their different possible implementations.
 * This allows to easily launch a same action (e.g. incrementation) with a long or short instruction.
 */
public enum Instructions {
    INCR("INCR", "+"),
    DECR("DECR", "-"),
    LEFT("LEFT", "<"),
    RIGHT("RIGHT", ">");

    List<String> names;

    Instructions(String... names) {
        this.names = Arrays.asList(names);
    }

    /**
     * Checks if str corresponds to an instruction declared in the enum
     * @param str
     * @return
     */
    static public Instructions hasInstruction(String str){
        return Arrays.stream(Instructions.values()).filter(s->{return s.names.contains(str);}).findFirst().orElse(null);
    }

}
