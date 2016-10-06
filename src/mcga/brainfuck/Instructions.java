package mcga.brainfuck;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 28/09/2016.
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

    static public Instructions hasInstruction(String str) {
        return Arrays.stream(Instructions.values()).filter(s -> s.names.contains(str)).findFirst().orElse(null);
    }

}
