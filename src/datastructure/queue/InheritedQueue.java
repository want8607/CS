package datastructure.queue;

import datastructure.list.LinkedList;

public class InheritedQueue<E> extends LinkedList<E> implements QueueInterface<E>{

    public InheritedQueue() {
        super();
    }

    @Override
    public void enqueue(E newItem) {
        super.append(newItem);
    }

    @Override
    public E dequeue() {
        return super.remove(0);
    }

    @Override
    public E front() {
        return super.get(0);
    }

    @Override
    public void dequeueAll() {
        super.clear();
    }
}
