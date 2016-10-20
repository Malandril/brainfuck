package mcga.brainfuck;


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This enum links the arguments passed in parameter in the command line to the actions they correspond to.
 */
public enum Arguments {
    P("-p"),
    IN("-i"),
    OUT("-o"),
    REWRITE("--rewrite"),
    CHECK("--check");

    String expression;

    /**
     * Constructor of the enum.
     * @param expression parameter
     */
    Arguments(String expression) {
        this.expression = expression;
    }

    /**
     * Builds an Options object from the arguments.
     * @return Options object i.e. a list containing the arguments.
     */
    public static Options createOptions() {
        Options options = new Options();
        for (Arguments arguments : Arguments.values()) {
            if (arguments.expression.charAt(1) == '-') {
                options.addOption(Option.builder().longOpt(arguments.expression.substring(2)).build());
            } else {
                options.addOption(Option.builder().longOpt(arguments.expression.substring(1)).hasArg().build());
            }
        }
        return options;
    }
}
