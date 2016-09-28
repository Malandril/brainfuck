package mcga.brainfuck;

import java.util.LinkedList;

/**
 * Created by user on 26/09/2016.
 */
public class MyLinkedList<Integer> extends LinkedList<Integer>{
    int MAX_SIZE=30000;

    @Override
    public boolean add(Integer t) throws IndexOutOfBoundsException{
        if(this.size()<MAX_SIZE) {
            return super.add(t);
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

}