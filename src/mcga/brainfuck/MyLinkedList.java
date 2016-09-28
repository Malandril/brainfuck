package mcga.brainfuck;

import java.util.LinkedList;

/**
 * Created by user on 26/09/2016.
 */
public class MyLinkedList<T> extends LinkedList<T> {
    int MAX_SIZE = 30000;

    @Override
    public boolean add(T t) throws IndexOutOfBoundsException {
        if (this.size() < MAX_SIZE) {
            return super.add(t);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, T element) {
        if (this.size() < MAX_SIZE) {
            super.add(index, element);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}