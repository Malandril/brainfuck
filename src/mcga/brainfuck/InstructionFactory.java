package mcga.brainfuck;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 28/09/2016.
 */
public enum InstructionFactory {
    INCR("INCR", "+"),
    DECR("DECR", "-"),
    LEFT("LEFT", "<"),
    RIGHT("RIGHT", ">");

    List<String> names;


    InstructionFactory(String... names) {
        this.names = Arrays.asList(names);
    }

    static public InstructionFactory hasInstruction(String str) {
        return Arrays.stream(InstructionFactory.values()).filter(s -> s.names.contains(str)).findFirst().orElse(null);
    }

    static public void createInstruction(String s){
        InstructionFactory inst=hasInstruction(s);
        if(inst!=null) switch (inst) {
            case INCR:
                new Increment().interpret();
                break;
            case DECR:
                new Decrement().interpret();
                break;
            case LEFT:
                new Left().interpret();
                break;
            case RIGHT:
                new Right().interpret();
                break;
        }
        else{
            throw new IllegalArgumentException(s);
        }
    }

}
