package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.processing.Parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thomas on 30/11/2016.
 * salut
 */
public class Macro implements Comparable<Macro> {
    public String name;
    public String value;
    private String regName;
    private String[] params;
    private ArrayList<String[]> values = new ArrayList<>();

    public Macro(String name, String value) {
        this(name, value, new String[0]);
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

    public String callMacro(String str, String paramsStr) {
        StringBuilder tmp = new StringBuilder();
        if (paramsStr != null) {
            String[] split = paramsStr.split(Parser.PROC_PARAM_SEP);
            if(split.length!=params.length){
                throw new InvalidCodeException("nombre de parametres incorrect");
            }
            for (String[] string : values) {
                for (int j = 0; j < split.length; j++) {
                    if (params[j].equals(string[0])) {
                        for (int k = 0; k < Integer.valueOf(split[j]); k++) {
                            tmp.append(string[1]);
                        }
                    }
                }
            }
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
        return obj.getClass() == this.getClass() && this.name.equals(((Macro) obj).name);
    }

    @Override
    public String toString() {
        return "Macro{" +
                "name='" + name + '\'' +
                ", regName='" + regName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
