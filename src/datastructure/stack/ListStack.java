package datastructure.stack;

import datastructure.list.LinkedList;
import datastructure.list.ListInterface;

public class ListStack<E> implements StackInterface<E> {
    private ListInterface<E> list;

    public ListStack() {
        list = new LinkedList<>();
    }

    @Override
    public void push(E newItem) {
        list.add(0, newItem);
    }

    @Override
    public E pop() {
        return list.remove(0);
    }

    @Override
    public E top() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void popAll() {
        list.clear();
    }
}
