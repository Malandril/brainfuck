package mcga.brainfuck;

import java.io.InputStream;

/**
 * Created by Thomas on 12/10/2016.
 */
public class Check extends Parser {
    private long compte = 0;

    public Check(InputStream stream) {
        super(stream);
    }

    public Check() {
        super();
    }

    @Override
    public void parseFile() {
        super.parseFile();
        if (this.compte != 0) {
            System.err.println("Code non valide");
            System.exit(4);
        }
        else {
            System.out.println("Code Valide");
        }
    }

    @Override
    public void execute(String str) {
        InstructionFactory instr = InstructionFactory.hasInstruction(str);
        if (instr != null) {
            switch (instr) {
                case JUMP:
                    compte++;
                    break;
                case BACK:
                    compte--;
                    break;
            }
        }
    }
}
