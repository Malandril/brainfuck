package mcga.brainfuck;

import mcga.brainfuck.processing.Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 30/11/2016.
 */
public class Macro implements Comparable<Macro> {
    String name;
    String regName;
    String value;
    private String[] params;
    private Set<Macro> subMacros = new TreeSet<>(Comparator.reverseOrder());
    private ArrayList<String[]> values = new ArrayList<>();

    public Macro(String name, String value) {
        this(name, value, 1);
    }

    public Macro(String name, String value, int repetition) {
        this.name = name;
        this.regName = Pattern.quote(name);
    }

    public Macro(String name, String value, String[] params) {
        this.name = name;
        this.regName = Pattern.quote(name);
        this.value = value;
        this.params = params;
        Pattern pat = Pattern.compile("(\\w*)\\((.+?)\\)");
        Matcher matcher = pat.matcher(value);
        while (matcher.find()) {
            values.add(new String[]{matcher.group(1), matcher.group(2)});
        }
    }

    public String callMacro(String str) {
        Pattern pat = Pattern.compile(regName + Parser.MACRO_INT_PARAM);
        Matcher match = pat.matcher(str);
        StringBuilder tmp = new StringBuilder();
        if (match.find()) {
            String[] split = match.group(1).split(",");
            for (int i = 0; i < values.size(); i++) {
                for (int j = 0; j < split.length; j++) {
                    if (params[j].equals(values.get(i)[0])) {
                        for (int k = 0; k < Integer.valueOf(split[j]); k++) {
                            tmp.append(values.get(i)[1]);
                        }
                    }
                }
            }
            str = match.replaceAll(name);
        } else {
            tmp.append(value);
        }
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
                ", subMacros=" + subMacros +
                '}';
    }
}
