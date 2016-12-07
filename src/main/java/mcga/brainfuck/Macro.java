package mcga.brainfuck;

import mcga.brainfuck.processing.Parser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 30/11/2016.
 */
public class Macro implements Comparable<Macro> {
    String name;
    String regName;
    String value;
    int repetition;
    private Set<Macro> subMacros = new TreeSet<>(Comparator.reverseOrder());
    private List<String> values;

    public Macro(String name, String value) {
        this(name, value, 1);
    }

    public Macro(String name, String value, int repetition) {
        this.name = name;
        this.regName = Pattern.quote(name);
        this.value = value;
        this.repetition = repetition;
    }

    public Macro(String name, String value, String[] params) {
        this.name = name;
        this.regName = Pattern.quote(name);
        this.value = "";
        this.repetition = 1;
        StringJoiner stringJoiner = new StringJoiner("|");
        for (String param : params) {
            stringJoiner.add(param);
        }
        values = new ArrayList<>(Arrays.asList(value.split(stringJoiner.toString())));
        if (values.get(0).equals("")) {
            values.remove(0);
        }
    }

    public String callMacro(String str) {
        Pattern pat = Pattern.compile(regName + Parser.MACRO_INT_PARAM);
        Matcher match = pat.matcher(str);
        StringBuilder tmp = new StringBuilder();
        if (match.find()) {
            String[] split = match.group(1).split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < Integer.valueOf(split[i]); j++) {
                    tmp.append(values.get(i));
                }
            }
            str = match.replaceAll(name);
        }
        tmp.append(value);
        return str.replaceAll(regName, tmp.toString());
    }

    @Override
    public int compareTo(Macro o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return this.name.equals(((Macro) obj).name);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Macro{" +
                "name='" + name + '\'' +
                ", regName='" + regName + '\'' +
                ", value='" + value + '\'' +
                ", repetition=" + repetition +
                ", subMacros=" + subMacros +
                '}';
    }
}
