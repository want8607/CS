package datastructure.stack;

import datastructure.list.LinkedList;

public class InheritedStack<E> extends LinkedList<E> implements StackInterface<E> {
    public InheritedStack() {
        super();
    }
    @Override
    public void push(E newItem) {
        super.add(0,newItem);
    }

    @Override
    public E pop() {
        if (!isEmpty()){
            return super.remove(0);
        } else return null;
    }

    @Override
    public E top() {
        return super.get(0);
    }

    @Override
    public void popAll() {
        super.clear();
    }
}
