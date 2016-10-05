package mcga.brainfuck;

import java.util.LinkedList;


public class MyLinkedList<T> extends LinkedList<T> {
    int MAX_SIZE = 30000;

    @Override
    public boolean add(T t) {
        if (this.size() < MAX_SIZE) {
            return super.add(t);
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0) {
            return super.get(index);
        } else {
            throw new MyIndexOutOfBoundsException(Integer.toString(index));
        }
    }
}