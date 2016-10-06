package mcga.brainfuck;


import java.util.Arrays;

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

    Arguments(String lol) {
        this.expression = lol;
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
}
