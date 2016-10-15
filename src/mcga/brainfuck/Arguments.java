package mcga.brainfuck;


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Created by user on 06/10/2016.
 */
public enum Arguments {
    P("-p"),
    IN("-i"),
    OUT("-o"),
    REWRITE("--rewrite"),
    CHECK("--check");

    String expression;

    Arguments(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Arguments{" +
                "expression='" + expression + '\'' +
                '}';
    }

    public static Options createOptions(){
        Options options = new Options();
        for (Arguments arguments : Arguments.values()) {
            if (arguments.expression.charAt(1) == '-'){
                options.addOption( Option.builder().longOpt(arguments.expression.substring(2))
                        .build());
            }else{
                options.addOption( Option.builder().longOpt(arguments.expression.substring(1))
                        .hasArg()
                        .build());
            }
        }
        return options;
    }
}
