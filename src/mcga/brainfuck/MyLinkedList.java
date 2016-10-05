package mcga.brainfuck;

import java.util.LinkedList;

/**
 * This Class creates a specific linkedList for the Project. The max size of the LIST is 30000
 * @param <T>
 */
public class MyLinkedList<T> extends LinkedList<T> {
    int MAX_SIZE = 30000;

    /**
     * Inserts the specified element at the specified position in this list
     * @param t
     * @return true if the value has add
     */
    @Override
    public boolean add(T t) {
        if (this.size() < MAX_SIZE) {
            return super.add(t);
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    /**
     * Returns the element at the specified position in this list
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index >= 0) {
            return super.get(index);
        } else {
            throw new MyIndexOutOfBoundsException(Integer.toString(index));
        }
    }
}