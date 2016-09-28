package mcga.brainfuck;


/**
 * mlo by lol on 23/09/2016.
 */
public class Brainfuck {
    static Memory memoire=new Memory();
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        interpreter.readFile();
        System.out.println(memoire);
    }

}
