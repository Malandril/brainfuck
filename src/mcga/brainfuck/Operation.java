package mcga.brainfuck;
import  java.util.List;
import  java.util.ArrayList;
/**
 * Created by user on 26/09/2016.
 */
public class Operation extends brainfuck {
    //List<Integer> memory= new ArrayList<Integer>();
    private int dataPointer = 0; // pointer to the current index of file
    private int memoryPointer = 0; // pointer to the current index of memory


    public Operation(){
        memory.add(0);
    }

    public void incrementation(){
        if(memory.get(memoryPointer)==255){}
        else {
            memory.add(memoryPointer, (int) (memory.get(memoryPointer) + 1));
        }
        System.out.println(memory.get(memoryPointer));// DEBUG
    }

    public void decrementation(){
        if(memory.get(memoryPointer)!=0){
            memory.add(memoryPointer, (int) (memory.get(memoryPointer) - 1));
        }
        System.out.println(memory.get(memoryPointer));
    }

    public void right(){
        if(memoryPointer == 29999){}
        else {
            memoryPointer++;
        }
        System.out.println(memoryPointer);
    }

    public void left(){
        if(memoryPointer!=0){
            memoryPointer--;
        }
        System.out.println(memoryPointer);
    }
}
