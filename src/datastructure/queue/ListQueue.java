package datastructure.queue;

import datastructure.list.ArrayList;
import datastructure.list.ListInterface;

public class ListQueue<E> implements QueueInterface<E> {
    private ListInterface<E> list;

    public ListQueue() {
        list = new ArrayList<>();
    }

    @Override
    public void enqueue(E newItem) {
        list.append(newItem);
    }

    @Override
    public E dequeue() {
        return list.remove(0);
    }

    @Override
    public E front() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void dequeueAll() {
        list.clear();
    }
}
