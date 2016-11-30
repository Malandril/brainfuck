package mcga.brainfuck;

import mcga.brainfuck.processing.Parser;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 30/11/2016.
 */
public class Macro implements Comparable<Macro>{
    String name;
    String value;
    int repetition;


    public Macro(String name,String value){
        this(name,value,1);
    }

    public Macro(String name, String value, int repetition) {
        this.name = name;
        this.value = value;
        this.repetition = repetition;
    }

    public String callMacro(String str){
        Pattern pat=Pattern.compile(name+Parser.MACRO_REGX);
        Matcher match = pat.matcher(str);
        if(match.find()){
            repetition=Integer.valueOf(match.group(1));
            str=match.replaceAll(name);
        }
        String tmp="";
        for (int i = 0; i < repetition; i++) {
            tmp+=value;
        }
        return str.replaceAll(name,tmp);
    }
    @Override
    public int compareTo(Macro o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass()==this.getClass()){
            return this.name.equals(((Macro)obj).name);
        }
        return false;
    }
}
