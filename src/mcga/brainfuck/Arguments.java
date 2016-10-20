package mcga.brainfuck;


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This enum links the arguments passed in parameter in the command line to the actions they correspond to.
 */
public enum Arguments {
    P("p",true),
    INPUT("i",true),
    OUTPUT("o",true),
    REWRITE("rewrite",false),
    CHECK("check",false),
    TRANSLATE("translate",false);


    String expression;
    boolean hasArgs;

    /**
     * Constructor of the enum.
     * @param expression parameter
     */

    Arguments(String expression,boolean hasArgs) {
        this.expression = expression;
        this.hasArgs=hasArgs;
    }

    /**
     * Builds an Options object from the arguments.
     * @return Options object i.e. a list containing the arguments.
     */
    public static Options createOptions() {
        Options options = new Options();
        for (Arguments argument : Arguments.values()) {
            if (!argument.hasArgs) {
                options.addOption(Option.builder().longOpt(argument.expression).build());
            } else {
                options.addOption(Option.builder().longOpt(argument.expression).hasArg().build());
            }
        }
        return options;
    }

    @Override
    public String toString() {
        return "Arguments{" +
                "expression='" + expression + '\'' +
                '}';
    }
}